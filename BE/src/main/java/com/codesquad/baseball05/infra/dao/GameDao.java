package com.codesquad.baseball05.infra.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
    public void terminateGame(Long gameId) {
        disconnectTeamWithUser();
        disconnectMatchWithUser();
    }

    private void disconnectTeamWithUser() {
        String sql = "UPDATE matches m INNER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = 1";

        this.jdbcTemplate.execute(sql);
    }

    private void disconnectMatchWithUser() {
        String sql = "UPDATE matches m " +
                "SET m.a_user_id = null, b_user_id = null " +
                "WHERE m.id = 1";

        this.jdbcTemplate.execute(sql);
    }
}
