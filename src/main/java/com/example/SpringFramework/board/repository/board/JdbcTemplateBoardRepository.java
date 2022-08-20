package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
        parameters.put("tittle", board.getTittle());
        parameters.put("content", board.getContent());
        parameters.put("name", board.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setId(key.longValue());
        return board;
    }

    @Override
    public Optional<Board> findById(long id) {
        List<Board> reuslt = jdbcTemplate.query("select * from board where board_no = ?", boardRowMapper(), id);
        return reuslt.stream().findAny();
    }

    @Override
    public Optional<Board> findByName(String name) {
        List<Board> reuslt = jdbcTemplate.query("select * from board where name = ?", boardRowMapper(), name);
        return reuslt.stream().findAny();
    }

    @Override
    public List<Board> readAll() {
        return jdbcTemplate.query("select * from board", boardRowMapper());
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {

            Board board = new Board();
            board.setId(rs.getLong("board_no"));
            board.setTittle(rs.getString("tittle"));
            board.setContent(rs.getString("content"));
            board.setName(rs.getString("name"));
            board.setCreated_ts(rs.getString("created_ts"));
            board.setUpdated_ts(rs.getString("updated_ts"));
            board.setDeleted_ts(rs.getString("deleted_ts"));
            return board;
        };
    }


    @Override
    public Board update(Long boardId, Board updateParam) {
        String sql = "update board set tittle=?, content=?, name=? where board_no=?";
        jdbcTemplate.update(sql,
                updateParam.getTittle(),
                updateParam.getContent(),
                updateParam.getName(),
                boardId);
        return updateParam;
    }


    @Override
    public Board delete(Long boardId, Board updateParam) {
        String sql = "update board set is_deleted = 1 where board_no = ?";
        jdbcTemplate.update(sql, updateParam.getDeleted_ts(), boardId);
        return updateParam;
    }
}
