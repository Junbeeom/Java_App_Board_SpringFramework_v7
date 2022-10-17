package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.paging.Criteria;
import com.example.SpringFramework.board.domain.board.paging.Criteria;
import com.example.SpringFramework.board.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Board create(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("board_no");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", board.getTittle());
        parameters.put("content", board.getContent());
        parameters.put("name", board.getName());
        parameters.put("is_deleted", "0");
        parameters.put("created_ts", Timestamp.valueOf(LocalDateTime.now()));


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setId(key.longValue());
        return board;
    }

    @Override
    public Optional<Board> findById(long id) {
        List<Board> reuslt = jdbcTemplate.query("select * from boardtest where board_no = ?", boardRowMapper(), id);
        return reuslt.stream().findAny();
    }

    @Override
    public Optional<Board> findByName(String name) {
        List<Board> reuslt = jdbcTemplate.query("select * from boardtest where name = ?", boardRowMapper(), name);
        return reuslt.stream().findAny();
    }

    @Override
    public List<Board> readAll() {
        return jdbcTemplate.query("select * from boardtest", boardRowMapper());
    }

    @Override
    public Board update(Long boardId, Board updateParam) {
        String sql = "update boardtest set title=?, content=?, name=?, updated_ts = CURRENT_TIMESTAMP where board_no = ?";
        jdbcTemplate.update(sql,
                updateParam.getTittle(),
                updateParam.getContent(),
                updateParam.getName(),
                boardId);
        return updateParam;
    }


    @Override
    public Board delete(Long boardId) {
        String sql = "update boardtest set is_deleted = 1, deleted_ts = CURRENT_TIMESTAMP where board_no = ?";
        jdbcTemplate.update(sql, boardId);

        return null;
    }

    @Override
    public List<Board> findAll(Board params) {
        String searchType = params.getSearchType();
        String searchKeyword = "%" + params.getSearchKeyword() + "%";


        System.out.println("searchType의 값은" +  searchType);
        System.out.println("searchKeyword의 값은" +  searchKeyword);

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(params);


        String sql = "select * from boardtest where is_deleted = 0 ";

        //동적쿼리
//        if(StringUtils.hasText(searchType) || StringUtils.hasText(searchKeyword)) {
//            sql += " where";
//        }

        boolean andFlag = false;
        if("tittle".equals(searchType)) {
            sql += " and title like ? order by board_no desc limit ?, ?";
            System.out.println("tittle에서 sql문 실행 ");
            return jdbcTemplate.query(sql,boardRowMapper(),searchKeyword, params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
        } else if ("content".equals(searchType)) {
            sql += " and content like ? order by board_no desc limit ?, ?";
            System.out.println("content에서 sql문 실행 ");
            return jdbcTemplate.query(sql,boardRowMapper(),searchKeyword, params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
        } else if ("name".equals(searchType)) {
            sql += " and name like ? order by board_no desc limit ?, ?";
            System.out.println("name에서 sql문 실행 ");
            return jdbcTemplate.query(sql,boardRowMapper(),searchKeyword, params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
        } else {
            sql += " order by board_no desc limit ?, ?";
            System.out.println("else에서 sql문 실행 ");
        }

        return jdbcTemplate.query(sql,boardRowMapper(), params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {

            Board board = new Board();
            board.setId(rs.getLong("board_no"));
            board.setTittle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setName(rs.getString("name"));
            board.setCreated_ts(rs.getString("created_ts"));
            board.setUpdated_ts(rs.getString("updated_ts"));
            board.setDeleted_ts(rs.getString("deleted_ts"));
            return board;
        };
    }


    @Override
    public int totalCount(Board params) {
        return jdbcTemplate.queryForObject("select count(*) from boardtest where is_deleted = 0", Integer.class);
    }
}
