package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.AllTablesDTO;
import com.codesquad.baseball05.domain.game.dto.UserTeamDTO;
import com.codesquad.baseball05.domain.game.entity.Half;
import com.codesquad.baseball05.domain.game.entity.Inning;
import com.codesquad.baseball05.domain.game.entity.Plate;
import com.codesquad.baseball05.domain.game.entity.Round;
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

    public PitchDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void pitch(SelectDAO selectDao, Long matchesId) throws SQLException {
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

        List<Round> rounds = nowPlate.getRounds();
        Round nowRound = rounds.get(rounds.size() - 1);

        insertRound(offenseTeam, nowPlate, nowRound);
    }

    private void updatePlate() {

    }

    private void insertRound(UserTeamDTO offenseTeam, Plate nowPlate, Round nowRound) {
        Long plateId = nowPlate.getId();
        Player lastPlayer = getLastPlayer(offenseTeam, nowRound);
        String playerName = nowRound.getHitOrOut() == null ? lastPlayer.getName() : getNextPlayer(offenseTeam, lastPlayer.getLineUp() + 1).getName();
        int strike = 0;
        int ball = 0;
        String hit_or_out = null;

        String decision = decision();

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

        this.jdbcTemplate.update(sql, plateId, playerName, strike, ball, hit_or_out);
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
        if(lineUp == 10) {
            lineUp = 1;
        }

        int finalLineUp = lineUp;

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
        int value = rollDice();

        log.info("value : {}", value);

        if (value < 25) {
            return "strike";
        } else if (value < 50) {
            return "ball";
        } else if (value < 75) {
            return "hit";
        } else {
            return "out";
        }
    }
}
