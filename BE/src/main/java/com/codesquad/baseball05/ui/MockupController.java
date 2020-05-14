package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.domain.*;
import com.codesquad.baseball05.domain.dto.BatterDTO;
import com.codesquad.baseball05.domain.dto.CurrentPlayersDTO;
import com.codesquad.baseball05.domain.dto.MatchTeamDTO;
import com.codesquad.baseball05.domain.dto.PitcherDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/mock")
@RestController
public class MockupController {

    @GetMapping("/matches")
    public Object matches() {
        List<Matches> tables = new ArrayList<>();

        Matches firstMatch = new Matches(
                1L,
                false,
                new MatchTeamDTO("Marvel", "Ever"),
                new MatchTeamDTO("Captain", "Jason")
        );

        Matches secondMatch = new Matches(
                1L,
                true,
                new MatchTeamDTO("Twins", null),
                new MatchTeamDTO("Tigers", "Solar")
        );

        Matches thirdMatch = new Matches(
                1L,
                true,
                new MatchTeamDTO("Rockets", null),
                new MatchTeamDTO("Dodgers", "Huey")
        );

        tables.add(firstMatch);
        tables.add(secondMatch);
        tables.add(thirdMatch);

        return tables;
    }

    //selectable 을 true에서 false로 변경
    //게임 객체 생성하므로 POST
    //다른 사람이 하고 있는 걸 선택하였을 경우 BAD REQUEST
    @PostMapping("/games")
    public Object selectTeam(@RequestBody Map<String, String> teamName) {
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    //게임 끝났을 떄 selectable 을 변경함
    //EX. URL로 게임 강제 종료
    @PatchMapping("/games")
    public Object end(@RequestBody Object gameId) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    //현황판(처음 시작 화면)
    @GetMapping("/games/rounds")
    public Object ready(@RequestParam Long matchesId) {
        Team homeTeam = new Team("Captain", 1, true );
        Team awayTeam = new Team("Marvel", 5, false);

        PitcherDTO pitcher = new PitcherDTO("최동원", 39);
        BatterDTO batter2 = new BatterDTO("이용대", 2, 1);

        CurrentPlayersDTO players = new CurrentPlayersDTO(pitcher, batter2);

        List<Plate> plates = new ArrayList<>();
        BatterDTO batter1 = new BatterDTO("김광진", 1, 0);
        //상황판(스트라이크가 몇인지 볼이 몇인지는 마지막 rounds를 참고)
        //아웃은 plate의 out을 참고
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round("스트라이크", 1, 0));
        rounds.add(new Round("볼", 1, 1));
        rounds.add(new Round("볼", 1, 2));
        rounds.add(new Round("볼", 1, 3));
        rounds.add(new Round("스트라이크", 2, 3));
        rounds.add(new Round("안타!", 0, 0));

        Plate plate = new Plate(6, 1, batter1, rounds);
        plates.add(plate);

        rounds = new ArrayList<>();
        rounds.add(new Round("스트라이크", 1, 0));
        rounds.add(new Round("볼", 1, 1));
        rounds.add(new Round("스트라이크", 2, 1));

        plate = new Plate(7, 1, batter2, rounds);
        plates.add(plate);

        Inning inning = new Inning(2, "초");

        GameStatusDTO gameStatus = new GameStatusDTO(true, homeTeam, awayTeam, players, inning, plates);
        return new ResponseEntity<>(gameStatus, HttpStatus.OK);
    }

    //현황판(피치 후)
    @PostMapping("/games/rounds")
    public Object pitch(@RequestParam Long matchesId) {
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @GetMapping("/scores")
    public Object viewScoreBoard() {
        List<ScoreBoardDto> matchScore = new ArrayList<>();
        ScoreBoardDto tigersScoreBoardDto = new ScoreBoardDto("Tigers");
        tigersScoreBoardDto.score(1);
        tigersScoreBoardDto.score(0);
        tigersScoreBoardDto.score(2);
        ScoreBoardDto twinsScoreBoardDto = new ScoreBoardDto("twins");
        twinsScoreBoardDto.score(0);
        twinsScoreBoardDto.score(3);
        matchScore.add(tigersScoreBoardDto);
        matchScore.add(twinsScoreBoardDto);
        return matchScore;
    }

    @GetMapping("/rosters")
    public Object viewRosters() {
        RoundRecordDto rr1 = new RoundRecordDto("김광진", 1, 1, 0, (long) 1.000);
        RoundRecordDto rr2 = new RoundRecordDto("이동규", 1, 0, 1, (long) 1.000);
        RoundRecordDto rr3 = new RoundRecordDto("김진수", 1, 0, 1, (long) 1.000);
        RoundRecordDto rr4 = new RoundRecordDto("박영권", 1, 1, 0, (long) 1.000);
        RoundRecordDto rr5 = new RoundRecordDto("추진수", 1, 1, 0, (long) 1.000);
        RoundRecordDto rr6 = new RoundRecordDto("이용대", 1, 0, 1, (long) 1.000);
        RoundRecordDto rr7 = new RoundRecordDto("류현진", 1, 0, 1, (long) 1.000);
        RoundRecordDto rr8 = new RoundRecordDto("최동수", 1, 0, 1, (long) 1.000);
        RoundRecordDto rr9 = new RoundRecordDto("한양범", 1, 1, 0, (long) 1.000);

        List<RoundRecordDto> roundRecordDtos = new ArrayList<>();
        roundRecordDtos.add(rr1);
        roundRecordDtos.add(rr2);
        roundRecordDtos.add(rr3);
        roundRecordDtos.add(rr4);
        roundRecordDtos.add(rr5);
        roundRecordDtos.add(rr6);
        roundRecordDtos.add(rr7);
        roundRecordDtos.add(rr8);
        roundRecordDtos.add(rr9);

        RosterBoard rosterBoard1 = new RosterBoard("Captain");
        rosterBoard1.setRoundRecordDtos(roundRecordDtos);
        rosterBoard1.setCurrentPlayer("류현진");
        rosterBoard1.setIsOffense(false);
        Map<String, Integer> totals = new HashMap<>();
        totals.put("mounts", 9);
        totals.put("hits", 4);
        totals.put("outs", 5);
        rosterBoard1.setTotals(totals);

        RosterBoard rosterBoard2 = new RosterBoard("Marvel");
        rosterBoard2.setRoundRecordDtos(roundRecordDtos);
        rosterBoard2.setCurrentPlayer("최동수");
        rosterBoard2.setIsOffense(true);
        rosterBoard2.setTotals(totals);

        RosterBoard[] rosterBoards = new RosterBoard[2];
        rosterBoards[0] = rosterBoard1;
        rosterBoards[1] = rosterBoard2;
        return rosterBoards;
    }
}
