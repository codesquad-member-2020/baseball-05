package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.team.entity.Player;
import com.codesquad.baseball05.domain.team.entity.Record;
import com.codesquad.baseball05.domain.team.entity.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeamDAO {

    private final JdbcTemplate jdbcTemplate;

    public TeamDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Team findTeamById(Long id) {
        String sql = "SELECT t.id, t.name FROM team t WHERE id = ?";

        RowMapper<Team> teamRowMapper = new RowMapper<Team>() {
            @Override
            public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
                Team team = new Team();
                team.setId(rs.getLong("id"));
                team.setName(rs.getString("name"));
                team.setPlayers(findAllPlayers());
                return team;
            }
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, teamRowMapper);
    }

    private List<Player> findAllPlayers() {
        String sql = "SELECT p.id, p.name, p.batting_average, p.is_pitcher FROM player p";

        RowMapper<Player> playerRowMapper = new RowMapper<Player>() {
            @Override
            public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
                Player player = new Player();
                player.setId(rs.getLong("id"));
                player.setName(rs.getString("name"));
                player.setBattingAverage(rs.getDouble("batting_average"));
                player.setIsPitcher(rs.getBoolean("is_pitcher"));
                player.setRecords(findAllRecords());
                return player;
            }
        };

        return this.jdbcTemplate.query(sql, playerRowMapper);
    }

    private List<Record> findAllRecords() {
        String sql = "SELECT r.id, r.mounts, r.hit, r.strike, r.ball, r.outs, r.batting_average FROM record r";

        RowMapper<Record> recordRowMapper = new RowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
                Record record = new Record();
                record.setId(rs.getLong("id"));
                record.setMounts(rs.getInt("mounts"));
                record.setHit(rs.getInt("hit"));
                record.setStrike(rs.getInt("strike"));
                record.setBall(rs.getInt("ball"));
                record.setOuts(rs.getInt("outs"));
                record.setBattingAverage(rs.getDouble("batting_average"));
                return record;
            }
        };

        return this.jdbcTemplate.query(sql, recordRowMapper);
    }
}
