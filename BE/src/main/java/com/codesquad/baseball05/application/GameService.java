package com.codesquad.baseball05.application;

import com.codesquad.baseball05.domain.matches.dto.MatchDTO;
import com.codesquad.baseball05.infra.dao.GameDAO;
import com.codesquad.baseball05.infra.dao.TeamDAO;
import com.codesquad.baseball05.infra.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    private final UserDAO userDAO;
    private final TeamDAO teamDAO;
    private final GameDAO gameDAO;
    private final MatchService matchService;

    public GameService(UserDAO userDAO, TeamDAO teamDAO, GameDAO gameDAO, MatchService matchService) {
        this.userDAO = userDAO;
        this.teamDAO = teamDAO;
        this.gameDAO = gameDAO;
        this.matchService = matchService;
    }

    public boolean selectTeam(String teamName) {
        if (!teamDAO.isExistedTeam(teamName)) {
            return false;
        }

        if (userDAO.isSelectedTeam(teamName)) {
            return false;
        }
        // 선점 안 된 팀인 경우 update
        String userId = "ever";
        userDAO.choiceTeam(userId, teamName);
        Long user = userDAO.findIdByUserId(userId);
        MatchDTO matchDTO = matchService.updateUserAtMatch(user, teamName);

        if (matchService.isMatchCompleted(matchDTO.getId())) {
            updateGame(matchDTO.getId());
        }
        return true;
    }

    public boolean isStartedGame(Long matchId) {
        return gameDAO.existMatch(matchId);
    }

    public void updateGame(Long matchId) {
        gameDAO.updateMatch(matchId);
    }

}
