package com.codesquad.baseball05.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    }
}
