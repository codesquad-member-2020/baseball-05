package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.AllTablesDTO;
import com.codesquad.baseball05.domain.game.dto.UserTeamDTO;
import com.codesquad.baseball05.domain.game.entity.*;
import com.codesquad.baseball05.domain.team.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class PitchDAO {

    private final JdbcTemplate jdbcTemplate;

    private int value;

    public PitchDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void pitch(SelectDAO selectDao, Long matchesId) throws SQLException {
        value = rollDice();

        AllTablesDTO allTablesDTO = selectDao.makeUserMatchesDTO(matchesId);
        allTablesDTO.setHomeAndOffense();

        UserTeamDTO homeTeam = null;
        UserTeamDTO awayTeam = null;

        if (allTablesDTO.getUserA().isHome()) {
            homeTeam = allTablesDTO.getUserA();
            awayTeam = allTablesDTO.getUserB();
        } else {
            homeTeam = allTablesDTO.getUserB();
            awayTeam = allTablesDTO.getUserA();
        }

        UserTeamDTO offenseTeam = null;
        UserTeamDTO defenseTeam = null;

        if (allTablesDTO.getUserA().isOffense()) {
            offenseTeam = allTablesDTO.getUserA();
            defenseTeam = allTablesDTO.getUserB();
        } else {
            offenseTeam = allTablesDTO.getUserB();
            defenseTeam = allTablesDTO.getUserA();
        }

        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        Half half = nowInning.getHalf().equals("top") ? nowInning.getFirstHalf() : nowInning.getSecondHalf();

        List<Plate> plates = half.getPlates();
        Plate nowPlate = plates.get(plates.size() - 1);

        List<Player> offensePlayers = offenseTeam.getTeam().getPlayers();

        Player player = offensePlayers.stream().filter(p -> p.getLineUp() == half.getLastBatPlayer()).findFirst().get();

        List<Round> rounds = nowPlate.getRounds();

        log.info("rounds : {}", rounds);

        Round nowRound = rounds.size() == 0 ? new Round(player.getName()) : rounds.get(rounds.size() - 1  );

        insertRound(offenseTeam, nowPlate, nowRound);

        updatePlate(half, nowPlate);

        updateHalf(half, nowPlate);

        updateInning(nowInning, half, allTablesDTO.getGame());
    }

    private void updateInning(Inning nowInning, Half half, Game game) {
        if (half.getOuts() == 3) {
            if (nowInning.getHalf().equals("top")) {
                this.jdbcTemplate.execute("INSERT INTO half (id, last_bat_player, total_plate, outs, hit, point) VALUES (null, 1, 0, 0, 0, 0)");
                this.jdbcTemplate.update("UPDATE inning i SET i.half = 'bottom', i.second_half_id = ? WHERE i.id = ?", half.getId() + 1, nowInning.getId());
            } else {
                this.jdbcTemplate.execute("INSERT INTO half (id, last_bat_player, total_plate, outs, hit, point) VALUES (null, 1, 0, 0, 0, 0)");
                this.jdbcTemplate.update("INSERT INTO inning (game_id, first_half_id, second_half_id, half) VALUES (?, null, null, 'top')", game.getId());
                this.jdbcTemplate.update("UPDATE inning i SET i.first_half_id = ? WHERE i.id = ?", half.getId() + 1, nowInning.getId() + 1);
            }
        }
    }

    private void updateHalf(Half half, Plate nowPlate) {
        String decision = decision();
        if (decision.equals("out")) {
            this.jdbcTemplate.update("UPDATE half h SET h.outs = h.outs + 1, h.last_bat_player = h.last_bat_player + 1 WHERE h.id = ?", half.getId());
        } else if (decision.equals("hit")) {
            this.jdbcTemplate.update("UPDATE half h SET h.hit = h.hit + 1, h.last_bat_player = h.last_bat_player + 1 WHERE h.id = ?", half.getId());
            if (half.getHit() >= 3) {
                this.jdbcTemplate.update("UPDATE half h SET h.point = h.point + 1 WHERE h.id = ?", half.getId());
            }
        } else if ((decision.equals("strike") && nowPlate.getStrike() == 2)) {
            this.jdbcTemplate.update("UPDATE half h SET h.outs = h.outs + 1, h.last_bat_player = h.last_bat_player + 1 WHERE h.id = ?", half.getId());
        } else if ((decision.equals("ball") && nowPlate.getBall() == 3)) {
            this.jdbcTemplate.update("UPDATE half h SET h.hit = h.hit + 1, h.last_bat_player = h.last_bat_player + 1 WHERE h.id = ?", half.getId());
        }
    }

    private void updatePlate(Half half, Plate nowPlate) {
        String decision = decision();

        String sql = null;

        Long id = null;

        if ((decision.equals("out")) ||
                (decision.equals("hit")) ||
                (decision.equals("strike") && nowPlate.getStrike() == 3) ||
                (decision.equals("ball") && nowPlate.getBall() == 4)) {
            sql = "INSERT INTO plate (half_id) VALUES (?)";
            id = half.getId();
        } else if (decision.equals("strike")) {
            sql = "UPDATE plate p SET p.strike = p.strike + 1 WHERE p.id = ?";
            id = nowPlate.getId();
        } else if (decision.equals("ball")) {
            sql = "UPDATE plate p SET p.ball = p.ball + 1 WHERE p.id = ?";
            id = nowPlate.getId();
        }

        this.jdbcTemplate.update(sql, id);
    }

    private void insertRound(UserTeamDTO offenseTeam, Plate nowPlate, Round nowRound) {
        Long plateId = nowPlate.getId();

        Player lastPlayer = getLastPlayer(offenseTeam, nowRound);

        String playerName = null;

        String decision = decision();

        log.info("nowRound : {}", nowRound);

        if (nowRound.getHitOrOut() == null && nowPlate.getStrike() == 2 && decision.equals("strike")) {
            playerName = getNextPlayer(offenseTeam, lastPlayer.getLineUp() + 1).getName();
            plateId += 1;
        } else if (nowRound.getHitOrOut() == null && nowPlate.getBall() == 3 && decision.equals("ball")) {
            playerName = getNextPlayer(offenseTeam, lastPlayer.getLineUp() + 1).getName();
            plateId += 1;
        } else if (nowRound.hitOrOut == null) {
            playerName = lastPlayer.getName();
        } else if ((nowRound.hitOrOut.equals("out") || nowRound.hitOrOut.equals("hit"))) {
            playerName = getNextPlayer(offenseTeam, lastPlayer.getLineUp() + 1).getName();
            plateId += 1;
        }
        int strike = 0;
        int ball = 0;

        String hit_or_out = null;

        if (decision.equals("hit") || decision.equals("out")) {
            hit_or_out = decision;
        } else {
            if (decision.equals("strike")) {
                strike++;
            } else {
                ball++;
            }
        }

        String sql = "INSERT INTO round (id, plate_id, player_name, strike, ball, hit_or_out) values (null, ?, ?, ?, ?, ?)";

        this.jdbcTemplate.update(sql, new Object[]{plateId, playerName, strike, ball, hit_or_out});
    }

    private Player getLastPlayer(UserTeamDTO offenseTeam, Round nowRound) {
        return offenseTeam.getTeam()
                .getPlayers()
                .stream()
                .filter(p -> p.getName().equals(nowRound.getPlayerName()))
                .findFirst()
                .get();
    }

    private Player getNextPlayer(UserTeamDTO offenseTeam, int lineUp) {
        log.info("here");
        if (lineUp == 10) {
            lineUp = 1;
        }

        int finalLineUp = lineUp;

        log.info("finalLineUp : {}", lineUp);

        return offenseTeam.getTeam()
                .getPlayers()
                .stream()
                .filter(p -> p.getLineUp() == finalLineUp)
                .findFirst()
                .get();
    }

    private int rollDice() {
        return (int) (Math.random() * 100);
    }

    private String decision() {
        if (this.value < 25) {
            return "strike";
        } else if (this.value < 50) {
            return "ball";
        } else if (this.value < 75) {
            return "hit";
        } else {
            return "out";
        }
    }
}
