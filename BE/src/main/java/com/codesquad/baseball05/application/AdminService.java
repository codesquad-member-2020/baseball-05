package com.codesquad.baseball05.application;

import com.codesquad.baseball05.infra.MatchDAO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private MatchDAO matchDAO;

    public AdminService(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public boolean resetMatchUser(Long id) {
        matchDAO.resetMatchById(id);
        return true;
    }
}
