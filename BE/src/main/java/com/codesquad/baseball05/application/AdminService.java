package com.codesquad.baseball05.application;

import com.codesquad.baseball05.domain.matches.dto.MatchDTO;
import com.codesquad.baseball05.infra.dao.GameDAO;
import com.codesquad.baseball05.infra.dao.MatchDAO;
import com.codesquad.baseball05.infra.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private UserDAO userDAO;
    private MatchDAO matchDAO;
    private GameDAO gameDAO;

    public AdminService(UserDAO userDAO, MatchDAO matchDAO, GameDAO gameDAO) {
        this.userDAO = userDAO;
        this.matchDAO = matchDAO;
        this.gameDAO = gameDAO;
    }

    public boolean resetMatchUser(Long id) {
        // user테이블에서 선택한 팀정보도 reset 해줘야함
        MatchDTO matchDTO = matchDAO.findById(id);
        userDAO.deleteTeam(matchDTO.getUserA());
        userDAO.deleteTeam(matchDTO.getUserB());
        matchDAO.resetMatchById(id);
        gameDAO.deleteMatch(id);
        return true;
    }
}
