package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.RoundRecordDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TeamDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TeamDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Boolean isExistedTeam(String teamName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName);

        String isExistedTeamSql = "SELECT exists(SELECT id FROM team WHERE name = :teamName)";
        return namedParameterJdbcTemplate.queryForObject(isExistedTeamSql, namedParameters, Boolean.class);
    }

    public Long findByTeamName(String teamName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName);

        String findByTeamNameSql = "SELECT id FROM team WHERE name = :teamName";
        return namedParameterJdbcTemplate.queryForObject(findByTeamNameSql, namedParameters, Long.class);
    }

    public String findPitcherByTeamId(Long teamId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamId", teamId);

        String findPlayerByTeamIdSql = "SELECT name FROM player WHERE team_id = :teamId AND is_pitcher = TRUE;";
        return namedParameterJdbcTemplate.queryForObject(findPlayerByTeamIdSql, namedParameters, String.class);
    }

    //SELECT player.name, r.mounts, hit, outs FROM player JOIN record r ON player.id = r.player_id WHERE player.team_id = 1;
    public List<RoundRecordDTO> findPlayerRecord(Long teamId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamId", teamId);

        String findPlayerByTeamIdSql = "SELECT name, mounts, hit, outs FROM player JOIN record r ON player.id = r.player_id WHERE player.team_id = :teamId";
        List<RoundRecordDTO> roundRecordDTOList = (List<RoundRecordDTO>) namedParameterJdbcTemplate.query(findPlayerByTeamIdSql, namedParameters, getRoundRecordDTORowMapper());
        return roundRecordDTOList;
    }

    private RowMapper<RoundRecordDTO> getRoundRecordDTORowMapper() {
        return (rs, row) -> {
            RoundRecordDTO dto = new RoundRecordDTO();
            dto.setPlayer(rs.getString("name"));
            dto.setMounts(rs.getInt("mounts"));
            dto.setHits(rs.getInt("hit"));
            dto.setOuts(rs.getByte("outs"));
            return dto;
        };
    }

}
