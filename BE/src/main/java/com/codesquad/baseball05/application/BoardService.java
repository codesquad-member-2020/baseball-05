package com.codesquad.baseball05.application;

import com.codesquad.baseball05.domain.game.dto.InningDTO;
import com.codesquad.baseball05.domain.matches.dto.MatchDTO;
import com.codesquad.baseball05.domain.game.dto.RosterBoardDTO;
import com.codesquad.baseball05.domain.game.dto.ScoreBoardDTO;
import com.codesquad.baseball05.infra.dao.GameDAO;
import com.codesquad.baseball05.infra.dao.HalfDAO;
import com.codesquad.baseball05.infra.dao.MatchDAO;
import com.codesquad.baseball05.infra.dao.TeamDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private GameDAO gameDAO;
    private MatchDAO matchDAO;
    private HalfDAO halfDAO;
    private TeamDAO teamDAO;

    public BoardService(GameDAO gameDAO, MatchDAO matchDAO, HalfDAO halfDAO, TeamDAO teamDAO) {
        this.gameDAO = gameDAO;
        this.matchDAO = matchDAO;
        this.halfDAO = halfDAO;
        this.teamDAO = teamDAO;
    }

    public List<ScoreBoardDTO> showScoreBoard(Long matchId) {
        // game이 시작되어 game테이블에 레코드가 들어가 있어야 함.
        // match 에서 id 값으로 home_team_name과 away_team_name 알아오기
        MatchDTO matchDTO = matchDAO.findById(matchId);
        String homeTeam = matchDTO.getHomeTeam();
        String awayTeam = matchDTO.getAwayTeam();

        List<ScoreBoardDTO> scoreBoardDTOS = new ArrayList<>();
        ScoreBoardDTO awayTeamScoreBoard = new ScoreBoardDTO(awayTeam); //초공격
        ScoreBoardDTO homeTeamScoreBoard = new ScoreBoardDTO(homeTeam); //말공격

        // match_id에 해당하는 game의 id값을 조회
        Long gameId = gameDAO.findByMatchId(matchId);

        // 조회해온 gaem의 id 값으로 inning 테이블의 id list 를 조회
        List<Long> awayTeamHalfId = gameDAO.findFirstHalfId(gameId);
        List<Long> homeTeamHalfId = gameDAO.findSecondHalfId(gameId);

        // inning id 목록 만큼 반복문으로 해당 inning의 first, second half의 point를 조회
        List<Integer> awayTeamScores = new ArrayList<>();
        for (Long halfId : awayTeamHalfId) {
            awayTeamScores.add(halfDAO.getPointById(halfId));
        }

        List<Integer> homeTeamScores = new ArrayList<>();
        for (Long halfId : homeTeamHalfId) {
            homeTeamScores.add(halfDAO.getPointById(halfId));
        }

        awayTeamScoreBoard.setInningScore(awayTeamScores);
        homeTeamScoreBoard.setInningScore(homeTeamScores);

        // total score
        awayTeamScoreBoard.setTotalScore(awayTeamScores.stream().mapToInt(n -> n).sum());
        homeTeamScoreBoard.setTotalScore(homeTeamScores.stream().mapToInt(n -> n).sum());

        scoreBoardDTOS.add(awayTeamScoreBoard);
        scoreBoardDTOS.add(homeTeamScoreBoard);
        return scoreBoardDTOS;
    }

    public List<RosterBoardDTO> showRosterBoard(Long matchId) {
        List<RosterBoardDTO> rosterBoardDTOS = new ArrayList<>();
        RosterBoardDTO homeTeamRosterobardDTO;
        RosterBoardDTO awayTeamRosterobardDTO;

        // matchId로 경기 중인 두 팀의 이름, gameID 를 가져와야 함
        MatchDTO matchDTO = matchDAO.findById(matchId);

        // 현재 공격팀을 알기 위해서 마지막 inning의 `Half`가 `top (원정팀 공격)`인지 `bottom (홈팀 공격)`인지로 가져와야 함
        String homeTeamName = matchDTO.getHomeTeam();
        String awayTeamName = matchDTO.getAwayTeam();
        Long homeTeamId = teamDAO.findByTeamName(homeTeamName);
        Long awayTeamId = teamDAO.findByTeamName(awayTeamName);
        homeTeamRosterobardDTO = new RosterBoardDTO(homeTeamName);
        awayTeamRosterobardDTO = new RosterBoardDTO(awayTeamName);

        // 공vs수 조회
        Long gameId = gameDAO.findByMatchId(matchId);
        InningDTO currentInning = gameDAO.findInningByGameId(gameId);
        String topOrBottom = currentInning.getHalf();

        // 수비팀 → player의 is_pitcher 가 true인 선수정보
        // 공격팀 → half의 last_bat_player 정보
        // 팀에 소속된 선수들의 player_id 로 record 테이블의 mounts, hist, outs 조회
        if (topOrBottom.equals("top")) {
            awayTeamRosterobardDTO.setIsOffense(true);
            homeTeamRosterobardDTO.setCurrentPlayer(teamDAO.findPitcherByTeamId(homeTeamId));
            // homeTeam 타자 정보 추가
            // 현재 inning의 half 정보를 조회해와서 last_bat_player의 선수 이름 조회
            awayTeamRosterobardDTO.setCurrentPlayer(gameDAO.findCurrentBatterNameByTeamId(currentInning.getFirstHalfId(),awayTeamId));
        }

        if (topOrBottom.equals("bottom")) {
            homeTeamRosterobardDTO.setIsOffense(true);
            awayTeamRosterobardDTO.setCurrentPlayer(teamDAO.findPitcherByTeamId(awayTeamId));
            // awayTeam 타자 정보 추가
            homeTeamRosterobardDTO.setCurrentPlayer(gameDAO.findCurrentBatterNameByTeamId(currentInning.getSecondHalfId(),awayTeamId));
        }

        homeTeamRosterobardDTO.setRoundRecordDTOList(teamDAO.findPlayerRecord(homeTeamId));
        awayTeamRosterobardDTO.setRoundRecordDTOList(teamDAO.findPlayerRecord(awayTeamId));

        rosterBoardDTOS.add(homeTeamRosterobardDTO);
        rosterBoardDTOS.add(awayTeamRosterobardDTO);
        return rosterBoardDTOS;
    }
}
