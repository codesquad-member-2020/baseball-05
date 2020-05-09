package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.dto.MatchesDTO;
import com.codesquad.baseball05.domain.entity.Matches;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MatchesDao {

    private final JdbcTemplate jdbcTemplate;

    public MatchesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private List<MatchesDTO> listMatches() {
        String sql = "SELECT * FROM matches";

        RowMapper<MatchesDTO> matchesRowMapper = new RowMapper<MatchesDTO>() {
            @Override
            public MatchesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                return null;
            }
        };

        return this.jdbcTemplate.query(sql, matchesRowMapper);
    }

    private boolean isPossibleEnterGame(Long id) {
        Matches matches = findMatchesById(id);
        return false;
    }

    private Matches findMatchesById(Long id) {
        String sql = "SELECT m.id, m.home_team, m.away_team FROM matches m WHERE id = ?";

        RowMapper<Matches> matchesRowMapper = new RowMapper<Matches>() {
            @Override
            public Matches mapRow(ResultSet rs, int rowNum) throws SQLException {
                Matches matches = new Matches();
                matches.setId(rs.getLong("id"));
                matches.setHomeTeam(rs.getString("home_team"));
                matches.setAwayTeam(rs.getString("away_team"));
                return matches;
            }
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, matchesRowMapper);
    }
}
