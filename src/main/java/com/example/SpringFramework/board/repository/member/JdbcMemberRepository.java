package com.example.SpringFramework.board.repository.member;

import com.example.SpringFramework.board.domain.member.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member create(Member member) {
        String sql = "insert into member(id, pw, name, birthdate, sex, phone) values(?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getBirthdate());
            pstmt.setString(5, member.getSex());
            pstmt.setString(6, member.getPhone());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setUser_no(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            } return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(long user_no) {
        String sql = "select * from member where user_no = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user_no);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setUser_no(rs.getLong("user_no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setBirthdate(rs.getString("birthdate"));
                member.setSex(rs.getString("sex"));
                member.setPhone(rs.getString("phone"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setUser_no(rs.getLong("user_no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setBirthdate(rs.getString("birthdate"));
                member.setSex(rs.getString("sex"));
                member.setPhone(rs.getString("phone"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByLoginId(String id) {
        String sql = "select * from member where user_no = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setUser_no(rs.getLong("user_no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setBirthdate(rs.getString("birthdate"));
                member.setSex(rs.getString("sex"));
                member.setPhone(rs.getString("phone"));
                return Optional.of(member);

            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();

            while(rs.next()) {
                Member member = new Member();
                member.setUser_no(rs.getLong("user_no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setBirthdate(rs.getString("birthdate"));
                member.setSex(rs.getString("sex"));
                member.setPhone(rs.getString("phone"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
