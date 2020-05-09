package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.entity.Match;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MatchDao {

    private final JdbcTemplate jdbcTemplate;

    public MatchDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Object listMatches() {
        String sql = "SELECT * FROM match_table";

        RowMapper<Match> matchTableRowMapper = new RowMapper<Match>() {
            @Override
            public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Match(
                        rs.getLong("id"),
                        rs.getString("home_team"),
                        rs.getString("away_team"),
                        rs.getBoolean("is_selected")
                );
            }
        };

        return this.jdbcTemplate.query(sql, matchTableRowMapper);
    }
}
