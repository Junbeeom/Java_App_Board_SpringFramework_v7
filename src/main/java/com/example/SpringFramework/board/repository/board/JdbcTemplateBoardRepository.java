package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.BoardCreate;
import com.example.SpringFramework.board.domain.board.BoardList;
import com.example.SpringFramework.board.domain.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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

//    @Override
//    public void create(Board board) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("board_no");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("title", board.getTitle());
//        parameters.put("content", board.getContent());
//        parameters.put("name", board.getName());
//        parameters.put("isDeleted", "0");
//        parameters.put("createdTs", Timestamp.valueOf(LocalDateTime.now()));
//
//
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        board.setBoard_no(key.longValue());
//        return board;
//    }

    @Override
    // 게시글 조회 및 검색
    public List<Board> findAll(Criteria params) {
        String sql = "SELECT * FROM board WHERE is_deleted = 0 LIMIT ? OFFSET ? ORDER BY board_no DESC";

        String searchType = params.getSearchType();
        String searchValue = "%" + params.getSearchValue() + "%";

        System.out.println(searchType + "#################");
        System.out.println(searchValue);

//        if ("tittle".equals(searchType)) {
//            sql += " and tittle like ?";
//            System.out.println("tittle에서 sql문 실행 ");
//            return jdbcTemplate.query(sql, boardRowMapper(), searchKeyword, params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
//        } else if ("content".equals(searchType)) {
//            sql += " and content like ? order by board_no desc limit ?, ?";
//            System.out.println("content에서 sql문 실행 ");
//            return jdbcTemplate.query(sql,boardRowMapper(),searchKeyword, params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
//        } else if ("name".equals(searchType)) {
//            sql += " and name like ? order by board_no desc limit ?, ?";
//            System.out.println("name에서 sql문 실행 ");
//            return jdbcTemplate.query(sql,boardRowMapper(),searchKeyword, params.getPaginationInfo().getFirstRecordIndex(), params.getRecordsPerPage());
//        } else {
//            sql += " order by board_no desc limit ?, ?";
//            System.out.println("else에서 sql문 실행 ");
//        }

        return jdbcTemplate.query(sql, boardRowMapper(), params.getCurrentPage(), params.getLimit());
    }

        @Override
        public Optional<Board> findByNo (Long boardNo){
            List<Board> result = jdbcTemplate.query("SELECT * FROM board WHERE board_no = ?", boardRowMapper(), boardNo);
            return result.stream().findAny();
        }

    @Override
    // 게시글 전체 개수
    public int totalCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM board WHERE is_deleted = 0", Integer.class);
    }

//    @Override
//    public Board update(Long boardId, Board updateParam) {
//        String sql = "update board set title=?, content=?, name=?, updated_ts = CURRENT_TIMESTAMP where board_no = ?";
//        jdbcTemplate.update(sql,
//                updateParam.getTittle(),
//                updateParam.getContent(),
//                updateParam.getName(),
//                boardId);
//        return updateParam;
//    }

//    @Override
//    public Board delete(Long boardId) {
//        String sql = "update board set is_deleted = 1, deleted_ts = CURRENT_TIMESTAMP where board_no = ?";
//        jdbcTemplate.update(sql, boardId);
//
//        return null;
//    }
//
    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setBoardNo(rs.getLong("board_no"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setName(rs.getString("name"));
            board.setCreatedTs(rs.getString("created_ts"));
            board.setUpdatedTs(rs.getString("updated_ts"));
            board.setDeletedTs(rs.getString("deleted_ts"));

            return board;
        };
    }
}

