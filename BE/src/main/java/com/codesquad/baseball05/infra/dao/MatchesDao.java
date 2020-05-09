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

    public List<MatchesDTO> listMatches() {
        List<Matches> matchesList = findAll();
        for (Matches match : matchesList) {
            isPossibleEnterGame(match);
        }
        return null;
    }

    private boolean isPossibleEnterGame(Matches match) {
        return false;
    }

    private List<Matches> findAll() {
        String sql = "SELECT m.id, m.home_team, m.away_team FROM matches m";

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

        return this.jdbcTemplate.query(sql, matchesRowMapper);
    }
}
