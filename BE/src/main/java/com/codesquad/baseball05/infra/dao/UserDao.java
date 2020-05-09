package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User findUserById(Long id) {
        String sql = "SELECT u.id, u.team_id, u.user_id, u.email FROM user u WHERE id = ?";

        RowMapper<User> userRowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setTeam_id(rs.getLong("team_id"));
                user.setUserId(rs.getString("user_id"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
    }
}
