package com.codesquad.baseball05.application;

import com.codesquad.baseball05.infra.TeamDAO;
import com.codesquad.baseball05.infra.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    private final UserDAO userDAO;
    private final TeamDAO teamDAO;

    public GameService(UserDAO userDAO, TeamDAO teamDAO) {
        this.userDAO = userDAO;
        this.teamDAO = teamDAO;
    }

    public boolean selectTeam(String teamName) {
        // 존재하는 team인지 여부 확인
        if (!teamDAO.isExistedTeam(teamName)) {
            return false;
        }

        if (userDAO.isSelectedTeam(teamName)) {
            return false;
        }
        // 선점 안 된 팀인 경우 update
        String userId = "ever";
        userDAO.choiceTeam(userId, teamName);
        return true;
    }
}
