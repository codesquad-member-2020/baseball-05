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
        List<ScoreBoard> matchScore = new ArrayList<>();
        ScoreBoard tigersScoreBoard = new ScoreBoard("Tigers");
        tigersScoreBoard.score(1);
        tigersScoreBoard.score(0);
        tigersScoreBoard.score(2);
        ScoreBoard twinsScoreBoard = new ScoreBoard("twins");
        twinsScoreBoard.score(0);
        twinsScoreBoard.score(3);
        matchScore.add(tigersScoreBoard);
        matchScore.add(twinsScoreBoard);
        return matchScore;
    }

    @GetMapping("/rosters")
    public Object viewRosters() {
        RoundRecord rr1 = new RoundRecord("김광진", 1, 1, 0, (long) 1.000);
        RoundRecord rr2 = new RoundRecord("이동규", 1, 0, 1, (long) 1.000);
        RoundRecord rr3 = new RoundRecord("김진수", 1, 0, 1, (long) 1.000);
        RoundRecord rr4 = new RoundRecord("박영권", 1, 1, 0, (long) 1.000);
        RoundRecord rr5 = new RoundRecord("추진수", 1, 1, 0, (long) 1.000);
        RoundRecord rr6 = new RoundRecord("이용대", 1, 0, 1, (long) 1.000);
        RoundRecord rr7 = new RoundRecord("류현진", 1, 0, 1, (long) 1.000);
        RoundRecord rr8 = new RoundRecord("최동수", 1, 0, 1, (long) 1.000);
        RoundRecord rr9 = new RoundRecord("한양범", 1, 1, 0, (long) 1.000);

        List<RoundRecord> roundRecords = new ArrayList<>();
        roundRecords.add(rr1);
        roundRecords.add(rr2);
        roundRecords.add(rr3);
        roundRecords.add(rr4);
        roundRecords.add(rr5);
        roundRecords.add(rr6);
        roundRecords.add(rr7);
        roundRecords.add(rr8);
        roundRecords.add(rr9);

        RosterBoard rosterBoard1 = new RosterBoard("Captain");
        rosterBoard1.setRoundRecords(roundRecords);
        rosterBoard1.setCurrentPlayer("류현진");
        rosterBoard1.setIsOffense(false);
        Map<String, Integer> totals = new HashMap<>();
        totals.put("mounts", 9);
        totals.put("hits", 4);
        totals.put("outs", 5);
        rosterBoard1.setTotals(totals);

        RosterBoard rosterBoard2 = new RosterBoard("Marvel");
        rosterBoard2.setRoundRecords(roundRecords);
        rosterBoard2.setCurrentPlayer("최동수");
        rosterBoard2.setIsOffense(true);
        rosterBoard2.setTotals(totals);

        RosterBoard[] rosterBoards = new RosterBoard[2];
        rosterBoards[0] = rosterBoard1;
        rosterBoards[1] = rosterBoard2;
        return rosterBoards;
    }
}
