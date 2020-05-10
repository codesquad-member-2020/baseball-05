package com.codesquad.baseball05.application;

import com.codesquad.baseball05.domain.dto.MatchDTO;
import com.codesquad.baseball05.domain.dto.ScoreBoardDTO;
import com.codesquad.baseball05.infra.GameDAO;
import com.codesquad.baseball05.infra.HalfDAO;
import com.codesquad.baseball05.infra.MatchDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private GameDAO gameDAO;
    private MatchDAO matchDAO;
    private HalfDAO halfDAO;

    public BoardService(GameDAO gameDAO, MatchDAO matchDAO, HalfDAO halfDAO) {
        this.gameDAO = gameDAO;
        this.matchDAO = matchDAO;
        this.halfDAO = halfDAO;
    }

    public List<ScoreBoardDTO> showScoreBoard(Long matchId) {
        // game이 시작되어 game테이블에 레코드가 들어가 있어야 함.
        // match 에서 id 값으로 home_team_name과 away_team_name 알아오기
        MatchDTO matchDTO = matchDAO.findById(matchId);
        String homeTeam = matchDTO.getHomeTeam();
        String awayTeam = matchDTO.getAwayTeam();

        // home_team_name ->
        List<ScoreBoardDTO> scoreBoardDTOS = new ArrayList<>();
        ScoreBoardDTO awayTeamScoreBoard = new ScoreBoardDTO(awayTeam); //초공격
        ScoreBoardDTO homeTeamScoreBoard = new ScoreBoardDTO(homeTeam); //말공격

        // match_id 에 해당하는 game의 id값을 조회
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
}
