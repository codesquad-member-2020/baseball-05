package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.*;
import com.codesquad.baseball05.domain.game.entity.Half;
import com.codesquad.baseball05.domain.game.entity.Inning;
import com.codesquad.baseball05.domain.game.entity.Plate;
import com.codesquad.baseball05.domain.game.entity.Round;
import com.codesquad.baseball05.domain.team.entity.Player;
import com.codesquad.baseball05.domain.team.entity.Record;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GameDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final JdbcTemplate jdbcTemplate;

    public GameDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

    public void terminateGame(Long matchesId) {
        disconnectTeamWithUser(matchesId);
        disconnectMatchWithUser(matchesId);
    }

    public PitchResultDTO ready(SelectDAO selectDao, Long matchesId) throws SQLException {
        AllTablesDTO allTablesDTO = selectDao.makeUserMatchesDTO(matchesId);
        allTablesDTO.setHomeAndOffense();

        GameTeamDTO homeTeam = makeTeamDTO(allTablesDTO, true);
        GameTeamDTO awayTeam = makeTeamDTO(allTablesDTO, false);

        CurrentPlayerDTO players = new CurrentPlayerDTO(makePitcherDTO(allTablesDTO), makeBatterDTO(allTablesDTO, 0));

        InningDTO inning = makeInningDTO(allTablesDTO);

        List<PlateDTO> plates = makePlateDTOList(allTablesDTO);

        return new PitchResultDTO(isTopHalf(allTablesDTO), homeTeam, awayTeam, players, inning, plates);
    }

    private GameTeamDTO makeTeamDTO(AllTablesDTO allTablesDTO, boolean isFindHome) {
        boolean isUserAHome = allTablesDTO.getUserA().isHome();

        if (isFindHome) {
            return isUserAHome ? makeGameTeamDTO(allTablesDTO, allTablesDTO.getUserA()) : makeGameTeamDTO(allTablesDTO, allTablesDTO.getUserB());
        } else {
            return isUserAHome ? makeGameTeamDTO(allTablesDTO, allTablesDTO.getUserB()) : makeGameTeamDTO(allTablesDTO, allTablesDTO.getUserA());
        }
    }

    private List<RoundDTO> makeRoundDTOList(AllTablesDTO allTablesDTO, String playerName) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);
        Half half = nowInning.isFirstHalf() ? nowInning.getFirstHalf() : nowInning.getSecondHalf();

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
        Half half = nowInning.isFirstHalf() ? nowInning.getFirstHalf() : nowInning.getSecondHalf();

        List<Plate> plates = half.getPlates();

        int playerIndex = 1;

        List<PlateDTO> plateDTOS = new ArrayList<>();

        for (Plate plate : plates) {
            if (plateDTOS.size() > 3) {
                break;
            }

            Plate nowPlate = plates.get(plates.size() - playerIndex);
            GameBatterDTO batter = makeBatterDTO(allTablesDTO, playerIndex - 1);
            PlateDTO plateDTO = new PlateDTO(nowPlate.getId(), nowPlate.getStrike(), nowPlate.getBall(), half.getOuts(), batter, makeRoundDTOList(allTablesDTO, batter.getName()));
            plateDTOS.add(plateDTO);
            playerIndex++;
        }
        return plateDTOS;
    }

    private InningDTO makeInningDTO(AllTablesDTO allTablesDTO) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        String half = nowInning.isFirstHalf() ? "초" : "말";
        return new InningDTO(nowInning.getId(), half);
    }

    private GameBatterDTO makeBatterDTO(AllTablesDTO allTablesDTO, int lastDiffPlayerIndex) {

        boolean userAOffense = allTablesDTO.getUserA().isOffense();

        List<Player> players = userAOffense ? allTablesDTO.getUserA().getTeam().getPlayers() : allTablesDTO.getUserB().getTeam().getPlayers();

        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        int playerIndex = nowInning.isFirstHalf() ? nowInning.getFirstHalf().getLastBatPlayer() : nowInning.getSecondHalf().getLastBatPlayer();
        Player player = players.get(playerIndex - lastDiffPlayerIndex);

        List<Record> records = findAllRecords(player.getId());
        Record record = records.get(records.size() - 1);

        return new GameBatterDTO(player.getName(), record.getMounts(), record.getHit());
    }

    private GamePitcherDTO makePitcherDTO(AllTablesDTO allTablesDTO) {
        List<Player> players = allTablesDTO.getUserA().isOffense() ? allTablesDTO.getUserB().getTeam().getPlayers() : allTablesDTO.getUserA().getTeam().getPlayers();

        Player player = players.stream().filter(Player::getIsPitcher).findFirst().orElseThrow(() -> new RuntimeException("투수가 없습니다!"));

        List<Record> records = findAllRecords(player.getId());

        return new GamePitcherDTO(player.getName(), records.get(records.size() - 1).getPitch());
    }

    private GameTeamDTO makeGameTeamDTO(AllTablesDTO allTablesDTO, UserTeamDTO userTeamDTO) {
        String teamName = userTeamDTO.getTeam().getName();

        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);

        int score = nowInning.isFirstHalf() ? nowInning.getFirstHalf().getPoint() : nowInning.getSecondHalf().getPoint();

        return new GameTeamDTO(teamName, score, userTeamDTO.isOffense());
    }

    private boolean isTopHalf(AllTablesDTO allTablesDTO) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);
        return nowInning.isFirstHalf();
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
                "INNER JOIN user u ON m.user_a = u.id OR m.user_b = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, matchesId);
    }

    private int disconnectMatchWithUser(Long matchesId) {
        String sql = "UPDATE matches m " +
                "SET m.user_a = null, user_b = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, matchesId);
    }
}
