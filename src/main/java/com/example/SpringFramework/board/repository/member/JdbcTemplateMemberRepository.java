package com.example.SpringFramework.board.repository.member;

import com.example.SpringFramework.board.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member create(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("user_no");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());
        parameters.put("pw", member.getPw());
        parameters.put("name", member.getName());
        parameters.put("birthdate", member.getBirthdate());
        parameters.put("sex", member.getSex());
        parameters.put("phone", member.getPhone());
        parameters.put("created_ts", member.getCreatedTs());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setUser_no(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(long user_no) {
        List<Member> reuslt = jdbcTemplate.query("select * from member where user_no = ?", memberRowMapper(), user_no);
        return reuslt.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> reuslt = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return reuslt.stream().findAny();
    }

    @Override
    public Optional<Member> findByLoginId(String id) {
        List<Member> reuslt = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return reuslt.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member where user_no = ?", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {

            Member member = new Member();
            member.setUser_no(rs.getLong("user_no"));
            member.setId(rs.getString("id"));
            member.setPw(rs.getString("pw"));
            member.setName(rs.getString("name"));
            member.setBirthdate(rs.getString("birthdate"));
            member.setSex(rs.getString("sex"));
            member.setPhone(rs.getString("phone"));
            return member;
        };

    }
}