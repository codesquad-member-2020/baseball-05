package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.InningDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class GameDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GameDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long findByMatchId(Long matchId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("matchId", matchId);

        String findByMatchIdSql = "SELECT id FROM game WHERE match_id = :matchId";
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(findByMatchIdSql, namedParameters, Long.class)).orElseThrow(NoSuchElementException::new);
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

    public InningDTO findInningByGameId(Long gameId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("gameId", gameId);

        String findCurrentHalfSql = "SELECT inning.id, game_id, first_half_id, second_half_id, half FROM inning WHERE game_id = :gameId ORDER BY id DESC LIMIT 1";
        return namedParameterJdbcTemplate.queryForObject(findCurrentHalfSql, namedParameters, ((rs, rowNum) -> {
            InningDTO dto = new InningDTO();
            dto.setId(rs.getLong("inning.id"));
            dto.setGameId(rs.getLong("game_id"));
            dto.setFirstHalfId(rs.getLong("first_half_id"));
            dto.setSecondHalfId(rs.getLong("second_half_id"));
            dto.setHalf(rs.getString("half"));
            return dto;
        }));
    }

    public String findCurrentBatterNameByTeamId(Long halfId, Long teamId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamId", teamId)
                .addValue("halfId", halfId);

        String findCurrentBatterNameByTeamIdSql = "SELECT name FROM player JOIN half ON player.line_up = half.last_bat_player WHERE half.id = :halfId and player.team_id = :teamId;";
        return namedParameterJdbcTemplate.queryForObject(findCurrentBatterNameByTeamIdSql, namedParameters, String.class);
    }

    public void updateMatch(Long matchId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("matchId", matchId);

        String updateMatchSql = "INSERT INTO game (match_id) VALUE (:matchId);";
        namedParameterJdbcTemplate.update(updateMatchSql, namedParameters);
    }

    public Boolean existMatch(Long matchId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("matchId", matchId);

        String existMatchSql = "SELECT exists(SELECT id FROM game WHERE match_id = :matchId)";
        return namedParameterJdbcTemplate.queryForObject(existMatchSql, namedParameters, Boolean.class);
    }

    public void deleteMatch(Long matchId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("matchId", matchId);

        String deleteMatchSql = "DELETE FROM game WHERE match_id = :matchId";
        namedParameterJdbcTemplate.update(deleteMatchSql, namedParameters);
    }

}
