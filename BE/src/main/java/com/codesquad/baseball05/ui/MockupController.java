package com.codesquad.baseball05.ui;

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
        List<MatchTable> tables = new ArrayList<>();

        MatchTable firstMatch = new MatchTable(
                1L,
                "Marvel",
                "Captain",
                true
        );

        MatchTable secondMatch = new MatchTable(
                2L,
                "Tigers",
                "Twins",
                true
        );

        MatchTable thirdMatch = new MatchTable(
                3L,
                "Dodgers",
                "Rockets",
                false
        );

        tables.add(firstMatch);
        tables.add(secondMatch);
        tables.add(thirdMatch);

        return tables;
    }

    @PostMapping("/games")
    public Object selectTeam(@RequestBody Long gameId, String playerTeam) {
        return new ResponseEntity<Response>(new Response(true), HttpStatus.CREATED);
    }

    //게임 끝났을 떄 selectable 을 변경함
    @PutMapping("/games")
    public Object start() {
        return new ResponseEntity<Response>(new Response(true), HttpStatus.OK);
    }

    //게임 끝났을 떄 selectable 을 변경함
    //클래스를 만들어서 RequestBody로 넘겨야 함
    @PatchMapping("/games")
    public Object end(@RequestBody Object gameId) {
        return new ResponseEntity<Response>(new Response(true), HttpStatus.OK);
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
