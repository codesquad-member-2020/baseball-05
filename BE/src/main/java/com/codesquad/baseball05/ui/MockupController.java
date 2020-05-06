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

    @PostMapping("/games")
    public Object selectTeam() {
        return new ResponseEntity<Response>(new Response(true), HttpStatus.CREATED);
    }

    //게임 끝났을 떄 selectable 을 변경함
    @PatchMapping("/games")
    public Object end(@RequestBody Object gameId) {
        return new ResponseEntity<Response>(new Response(true), HttpStatus.OK);
    }

    //현황판(처음 시작 화면)
    @GetMapping("/rounds")
    public Object ready(@RequestParam Boolean status) {
        return new ResponseEntity<Response>(new Response(true), HttpStatus.OK);
    }

    //현황판(피치 후)
    @PostMapping("/rounds")
    public Object pitch() {

        return new ResponseEntity<Response>(new Response(true), HttpStatus.OK);
    }
}
