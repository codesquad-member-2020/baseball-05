package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.entity.MatchTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MatchTableDao {

    private final JdbcTemplate jdbcTemplate;

    public MatchTableDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Object listMatches() {
        String sql = "SELECT * FROM match_table";

        RowMapper<MatchTable> matchTableRowMapper = new RowMapper<MatchTable>() {
            @Override
            public MatchTable mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MatchTable(
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
