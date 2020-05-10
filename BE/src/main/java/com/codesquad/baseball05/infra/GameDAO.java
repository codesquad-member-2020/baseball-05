package com.codesquad.baseball05.infra;

import com.codesquad.baseball05.domain.dto.HalfIdDTO;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GameDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long findByMatchId(Long matchId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("matchId", matchId);

        String findByMatchIdSql = "SELECT id FROM game WHERE match_id = :matchId";
        return namedParameterJdbcTemplate.queryForObject(findByMatchIdSql, namedParameters, Long.class);
    }

    public List<Long> findFirstHalfId(Long gameId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("gameId", gameId);

        String findHalfIdSql = "SELECT first_half_id FROM inning WHERE game_id = :gameId AND first_half_id IS NOT NULL";
        List<Long> halfIds = namedParameterJdbcTemplate.query(findHalfIdSql, namedParameters, (rs, rownum) -> {
            Long halfId = rs.getLong("first_half_id");
            return halfId;
        });
        return halfIds;
    }

    public List<Long> findSecondHalfId(Long gameId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("gameId", gameId);

        String findHalfIdSql = "SELECT second_half_id FROM inning WHERE game_id = :gameId AND second_half_id IS NOT NULL";
        List<Long> halfIds = namedParameterJdbcTemplate.query(findHalfIdSql, namedParameters, (rs, rownum) -> {
            Long halfId = rs.getLong("second_half_id");
            return halfId;
        });
        return halfIds;
    }
}
