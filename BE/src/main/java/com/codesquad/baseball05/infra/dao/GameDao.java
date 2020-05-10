package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.GameTeamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //MATCHES 테이블(클래스)
    //- aUserId -> null
    //- bUserId -> null
    //
    //USER 클래스
    //- tean_id -> null

    //하나의 쿼리로 하고 싶었지만 변경해야 하는 team_id는 2개인데 한 번에 2개 다 변경이 안됨
    //a_user_id, b_user_id, team_id 중 위에 있는 거 총 3개가 null 값이 되면
    //연결이 다 끊어져서 남은 team_id 1개가 null 값으로 설정 불가
    //그래서 2번 쿼리 돌림
    public void terminateGame(Long gameId) {
        disconnectTeamWithUser(gameId);
        disconnectMatchWithUser(gameId);
    }

    public Object ready() {
        return null;
    }

    public Object pitch() {
        String sql = "";

        RowMapper<GameTeamDTO> gameTeamDTORowMapper = new RowMapper<GameTeamDTO>() {
            @Override
            public GameTeamDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return null;
            }
        };

        return this.jdbcTemplate.queryForObject(sql, gameTeamDTORowMapper);
    }

    private void disconnectTeamWithUser(Long gameId) {
        String sql = "UPDATE matches m INNER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = " + gameId;

        this.jdbcTemplate.execute(sql);
    }

    private void disconnectMatchWithUser(Long gameId) {
        String sql = "UPDATE matches m " +
                "SET m.a_user_id = null, b_user_id = null " +
                "WHERE m.id = " + gameId;

        this.jdbcTemplate.execute(sql);
    }
}
