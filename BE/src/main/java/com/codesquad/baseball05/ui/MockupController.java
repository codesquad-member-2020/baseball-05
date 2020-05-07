package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.domain.*;
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

    //만약 기존 게임 정보가 있으면 선택했던 팀의 이름을 보내줌
    //없으면 팀 이름을 null로 리턴
    @GetMapping("/continues")
    public Object hasGameSaved() {
        Map<String, String> team = new HashMap<>();
        team.put("teamName", "tigers");
        return new ResponseEntity<Map>(team, HttpStatus.OK);
    }

    //게임 새로하기 이어하기 선택
    //게임 새로하기 선택시 DB 초기화
    //이어하기 선택시 DB 유지

    //Map 필드
    //새로운 게임인가
    @PutMapping("/continues")
    public Object setGame(@RequestBody Map<String, Object> newGame) {
//        Boolean isNewGame = (Boolean) newGame.get("isNewGame");
//        if(isNewGame) {
//            initialize();
//        }
        Map<String, String> redirectUrl = new HashMap<>();
        redirectUrl.put("url", "/mock/matches");
        return new ResponseEntity<Map>(redirectUrl, HttpStatus.OK);
    }

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

    //selectable 을 true에서 false로 변경
    //게임 객체 생성하므로 POST
    //다른 사람이 하고 있는 걸 선택하였을 경우 BAD REQUEST
    @PostMapping("/games")
    public Object selectTeam() {
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    //게임 끝났을 떄 selectable 을 변경함
    //EX. URL로 게임 강제 종료
    @PatchMapping("/games")
    public Object end(@RequestBody Object gameId) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    //현황판(처음 시작 화면)
    @GetMapping("/rounds")
    public Object ready(@RequestParam Boolean status) {
        Player homeTeamPitcher = new Player("최동원", 39, 0, true);
        Player awayTeamPitcher = new Player("류현진", 36, 0, true);
        Team homeTeam = new Team("Captain", 1, false, true, homeTeamPitcher);
        Team awayTeam = new Team("Marvel", 5, true, false, awayTeamPitcher);

        Player batter = new Player("김광진", 1, 0, false);

        //상황판(스트라이크가 몇인지 볼이 몇인지는 마지막 rounds를 참고)
        //아웃은 plate의 out을 참고
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round("스트라이크", 1, 0));
        rounds.add(new Round("볼", 1, 1));
        rounds.add(new Round("볼", 1, 2));
        rounds.add(new Round("볼", 1, 3));
        rounds.add(new Round("스트라이크", 2, 3));
        rounds.add(new Round("안타!", 0, 0));

        Plate plate = new Plate(6, 1, batter, rounds);

        List<Plate> plates = new ArrayList<>();

        plates.add(plate);

        batter = new Player("이용대", 2, 1, false);

        rounds = new ArrayList<>();
        rounds.add(new Round("스트라이크", 1, 0));
        rounds.add(new Round("볼", 1, 1));
        rounds.add(new Round("스트라이크", 2, 1));

        plate = new Plate(7, 1, batter, rounds);

        plates.add(plate);

        Inning inning = new Inning(2, "초", "수비", plates);
        GameStatusDTO gameStatus = new GameStatusDTO(homeTeam, awayTeam, inning);
        return new ResponseEntity<GameStatusDTO>(gameStatus, HttpStatus.OK);
    }

    //현황판(피치 후)
    @PostMapping("/rounds")
    public Object pitch() {
        Player homeTeamPitcher = new Player("최동원", 39, 0, true);
        Player awayTeamPitcher = new Player("류현진", 36, 0, true);
        Team homeTeam = new Team("Captain", 1, false, true, homeTeamPitcher);
        Team awayTeam = new Team("Marvel", 5, true, false, awayTeamPitcher);

        Player batter = new Player("김광진", 1, 0, false);

        Round round = new Round("스트라이크", 1, 0);

        Plate plate = new Plate(6, 1, batter, round);

        Inning inning = new Inning(2, "초", "수비", plate);
        GameStatusDTO gameStatus = new GameStatusDTO(homeTeam, awayTeam, inning);
        return new ResponseEntity<GameStatusDTO>(gameStatus, HttpStatus.OK);
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
        RosterBoard rosterBoard2 = new RosterBoard("Marvel");
        rosterBoard2.setRoundRecords(roundRecords);

        RosterBoard[] rosterBoards = new RosterBoard[2];
        rosterBoards[0] = rosterBoard1;
        rosterBoards[1] = rosterBoard2;
        return rosterBoards;
    }
}
