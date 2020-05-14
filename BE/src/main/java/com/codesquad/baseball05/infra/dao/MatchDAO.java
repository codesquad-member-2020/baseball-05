package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.matches.dto.MatchDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MatchDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MatchDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public MatchDTO findByTeamName(String teamName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName);

        String findByTeamNameSql = "SELECT * FROM matches WHERE home_team=:teamName OR away_team=:teamName";
        MatchDTO matchDTO = namedParameterJdbcTemplate.queryForObject(findByTeamNameSql, namedParameters, (rs, rownum) -> {
            MatchDTO dto = new MatchDTO();
            dto.setId(rs.getLong("id"));
            dto.setUserA(rs.getLong("user_a"));
            dto.setUserB(rs.getLong("user_b"));
            dto.setHomeTeam(rs.getString("home_team"));
            dto.setAwayTeam(rs.getString("away_team"));
            return dto;
        });
        return matchDTO;
    }

    public MatchDTO findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        String findByTeamNameSql = "SELECT * FROM matches WHERE id = :id";
        MatchDTO matchDTO = namedParameterJdbcTemplate.queryForObject(findByTeamNameSql, namedParameters, (rs, rownum) -> {
            MatchDTO dto = new MatchDTO();
            dto.setId(rs.getLong("id"));
            dto.setUserA(rs.getLong("user_a"));
            dto.setUserB(rs.getLong("user_b"));
            dto.setHomeTeam(rs.getString("home_team"));
            dto.setAwayTeam(rs.getString("away_team"));
            return dto;
        });
        return matchDTO;
    }

    public void updateUser(MatchDTO matchDTO) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", matchDTO.getId())
                .addValue("userA", matchDTO.getUserA())
                .addValue("userB", matchDTO.getUserB());

        String updateUserSql = "UPDATE matches SET user_a = :userA, user_b = :userB WHERE id = :id";
        namedParameterJdbcTemplate.update(updateUserSql, namedParameters);
    }

    public void resetMatchById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        String resetMatchSql = "UPDATE matches SET user_a=NULL, user_b=NULL WHERE id = :id";
        namedParameterJdbcTemplate.update(resetMatchSql, namedParameters);
    }
}
