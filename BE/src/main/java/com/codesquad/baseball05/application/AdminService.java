package com.codesquad.baseball05.application;

import com.codesquad.baseball05.infra.dao.GameDAO;
import com.codesquad.baseball05.infra.dao.MatchDAO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private MatchDAO matchDAO;
    private GameDAO gameDAO;

    public AdminService(MatchDAO matchDAO, GameDAO gameDAO) {
        this.matchDAO = matchDAO;
        this.gameDAO = gameDAO;
    }

    public boolean resetMatchUser(Long id) {
        matchDAO.resetMatchById(id);
        gameDAO.deleteMatch(id);
        return true;
    }
}
