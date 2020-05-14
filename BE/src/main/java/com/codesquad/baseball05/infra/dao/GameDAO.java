package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.*;
import com.codesquad.baseball05.domain.game.entity.Half;
import com.codesquad.baseball05.domain.game.entity.Inning;
import com.codesquad.baseball05.domain.game.entity.Plate;
import com.codesquad.baseball05.domain.game.entity.Round;
import com.codesquad.baseball05.domain.matches.entity.Matches;
import com.codesquad.baseball05.domain.team.entity.Player;
import com.codesquad.baseball05.domain.team.entity.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class GameDAO {

    private final JdbcTemplate jdbcTemplate;

    public GameDAO(DataSource dataSource) {
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
    public void terminateGame(Long matchesId) {
        disconnectTeamWithUser(matchesId);
        disconnectMatchWithUser(matchesId);
    }

    public Object ready() {
        return null;
    }

    public Object pitch(SelectDAO selectDao, Long matchesId) throws SQLException {
        AllTablesDTO allTablesDTO = setHomeAndOffense(selectDao.makeUserMatchesDTO(matchesId));

        GameTeamDTO homeTeam = makeTeamDTO(allTablesDTO, true);
        GameTeamDTO awayTeam = makeTeamDTO(allTablesDTO, false);

        CurrentPlayerDTO players = new CurrentPlayerDTO(makePitcherDTO(allTablesDTO), makeBatterDTO(allTablesDTO, 0));

        InningDTO inning = makeInningDTO(allTablesDTO);

        List<PlateDTO> plates = makePlateDTOList(allTablesDTO);

        return new PitchResultDTO(isTopHalf(allTablesDTO), homeTeam, awayTeam, players, inning, plates);
    }

    private List<RoundDTO> makeRoundDTOList(AllTablesDTO allTablesDTO, String playerName) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);
        Half half = nowInning.getIsFirstHalf() ? nowInning.getFirstHalf() : nowInning.getSecondHalf();

        List<Plate> plates = half.getPlates();
        Plate nowPlate = plates.get(plates.size() - 1);

        List<Round> rounds = nowPlate.getRounds().stream().filter(r -> r.getPlayerName().equals(playerName)).collect(Collectors.toList());

        List<RoundDTO> roundDTOS = new ArrayList<>();

        for (Round round : rounds) {
            RoundDTO roundDTO = new RoundDTO(round.getHitOrOut(), round.getStrike(), round.getBall());
            roundDTOS.add(roundDTO);
        }

        return roundDTOS;
    }

    private List<PlateDTO> makePlateDTOList(AllTablesDTO allTablesDTO) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);
        Half half = nowInning.getIsFirstHalf() ? nowInning.getFirstHalf() : nowInning.getSecondHalf();

        List<Plate> plates = half.getPlates();

        int playerIndex = 1;

        List<PlateDTO> plateDTOS = new ArrayList<>();

        for (Plate plate : plates) {
            if (plateDTOS.size() > 3) {
                break;
            }
            Plate nowPlate = plates.get(plates.size() - playerIndex);
            GameBatterDTO batter = makeBatterDTO(allTablesDTO, playerIndex - 1);
            PlateDTO plateDTO = new PlateDTO(nowPlate.getId(), half.getOuts(), batter, makeRoundDTOList(allTablesDTO, batter.getName()));
            plateDTOS.add(plateDTO);
            playerIndex++;
        }
        return plateDTOS;
    }

    private InningDTO makeInningDTO(AllTablesDTO allTablesDTO) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        String half = nowInning.getIsFirstHalf() ? "초" : "말";
        return new InningDTO(nowInning.getId(), half);
    }

    private GameBatterDTO makeBatterDTO(AllTablesDTO allTablesDTO, int lastDiffPlayerIndex) {

        boolean userAOffense = allTablesDTO.getUserA().isOffense();

        List<Player> players = userAOffense ? allTablesDTO.getUserA().getTeam().getPlayers() : allTablesDTO.getUserB().getTeam().getPlayers();

        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        int playerIndex = nowInning.getIsFirstHalf() ? nowInning.getFirstHalf().getLastBatPlayer() : nowInning.getSecondHalf().getLastBatPlayer();
        Player player = players.get(playerIndex - lastDiffPlayerIndex);

        List<Record> records = findAllRecords(player.getId());
        Record record = records.get(records.size() - 1);

        return new GameBatterDTO(player.getName(), record.getMounts(), record.getHit());
    }

    private GamePitcherDTO makePitcherDTO(AllTablesDTO verifiedHomeTeam) {
        List<Player> players = verifiedHomeTeam.getUserA().isOffense() ? verifiedHomeTeam.getUserA().getTeam().getPlayers() : verifiedHomeTeam.getUserB().getTeam().getPlayers();

        Player player = players.stream().filter(Player::getIsPitcher).findFirst().orElseThrow(() -> new RuntimeException("투수가 없습니다!"));

        List<Record> records = findAllRecords(player.getId());

        return new GamePitcherDTO(player.getName(), records.get(records.size() - 1).getPitch());
    }

    private GameTeamDTO makeTeamDTO(AllTablesDTO allTablesDTO, boolean isFindHome) {
        boolean isUserAHome = allTablesDTO.getUserA().isHome();
        boolean isReturnA = isFindHome ^ isUserAHome;
        return isReturnA ? makeGameTeamDTO(allTablesDTO, allTablesDTO.getUserB()) : makeGameTeamDTO(allTablesDTO, allTablesDTO.getUserA());
    }

    private GameTeamDTO makeGameTeamDTO(AllTablesDTO allTablesDTO, UserTeamDTO userTeamDTO) {
        String teamName = userTeamDTO.getTeam().getName();

        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        int score = nowInning.getIsFirstHalf() ? nowInning.getFirstHalf().getPoint() : nowInning.getSecondHalf().getPoint();

        return new GameTeamDTO(teamName, score, userTeamDTO.isOffense());
    }

    private boolean isTopHalf(AllTablesDTO allTablesDTO) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);
        return nowInning.getIsFirstHalf();
    }


    private AllTablesDTO setHomeAndOffense(AllTablesDTO allTablesDTO) throws SQLException {
        Matches matches = allTablesDTO.getMatches();
        boolean isHome = allTablesDTO.getUserA().getTeam().equals(matches.getHomeTeam());
        boolean isTeamAOffense = isTopHalf(allTablesDTO) ^ isHome;

        if(isTeamAOffense) {
            allTablesDTO.getUserA().setOffense(true);
        } else {
            allTablesDTO.getUserB().setOffense(true);
        }

        allTablesDTO.getUserA().setHome(isHome);
        allTablesDTO.getUserB().setHome(!isHome);

        return allTablesDTO;
    }

    private List<Record> findAllRecords(Long playerId) {
        String sql = "SELECT r.id, r.pitch, r.mounts, r.hit, r.strike, r.ball, r.outs, r.batting_average " +
                "FROM record r " +
                "INNER JOIN player p ON r.player_id = p.id WHERE p.id = ?";

        RowMapper<Record> recordRowMapper = new RowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
                Record record = new Record();
                record.setId(rs.getLong("id"));
                record.setPitch(rs.getInt("pitch"));
                record.setMounts(rs.getInt("mounts"));
                record.setHit(rs.getInt("hit"));
                record.setStrike(rs.getInt("strike"));
                record.setBall(rs.getInt("ball"));
                record.setOuts(rs.getInt("outs"));
                record.setBattingAverage(rs.getDouble("batting_average"));
                return record;
            }
        };

        return this.jdbcTemplate.query(sql, new Object[]{playerId}, recordRowMapper);
    }

    private int disconnectTeamWithUser(Long matchesId) {
        String sql = "UPDATE matches m " +
                "INNER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, matchesId);
    }

    private int disconnectMatchWithUser(Long matchesId) {
        String sql = "UPDATE matches m " +
                "SET m.a_user_id = null, b_user_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, matchesId);
    }
}
