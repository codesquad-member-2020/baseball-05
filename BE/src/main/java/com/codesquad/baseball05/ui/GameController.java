package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.GameService;
import com.codesquad.baseball05.domain.game.dto.AllTablesDTO;
import com.codesquad.baseball05.infra.dao.GameDAO;
import com.codesquad.baseball05.infra.dao.SelectDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RequestMapping("/games")
@RequiredArgsConstructor

@RestController
public class GameController {
    private final GameService gameService;

    private final GameDAO gameDAO;

    private final SelectDAO selectDao;

    @PostMapping()
    public ResponseEntity<ResponseBodyWrapper> selectTeam(@RequestBody Map<String, Object> requestBody) {
        String userId = "hihi";
        if (requestBody.get("userId") != null) {
            userId = (String) requestBody.get("userId");
        }

        // cookie 안의 유저가 맞는 유저인지 체크 필요
        // 동일한 쿠키값으로 다른 팀을 선택하면 게임중인 매치가 초기화 되지 않음 -> 종료하고 넘어가야 함

        //로그인한 user의 정보가 이미 user 테이블에 저장되어있는 상황
        //해당 팀 선점 -> 선점가능하면 선점 : success, 선점 불가능 : fail
        String resultOfSelect = "fail";
        if (gameService.selectTeam(userId, (String) requestBody.get("teamName"))) {
            resultOfSelect = "success";
        }
        return ResponseEntity.ok(ResponseBodyWrapper.ok(resultOfSelect));
    }

    @PatchMapping("")
    public ResponseEntity<HttpStatus> terminateGame(@RequestBody Map<String, Long> gameIdMap) {
        gameDAO.terminateGame(gameIdMap.get("gameId"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Object ready(@RequestParam Long matchesId) {
        AllTablesDTO allTablesDTO = selectDao.makeUserMatchesDTO(matchesId);
        return gameDAO.ready();
    }

    @PostMapping("/rounds")
    public Object pitch(@RequestParam Long matchesId) throws SQLException {
        return gameDAO.pitch(selectDao, matchesId);
    }
}
