package com.codesquad.baseball05.application;

import com.codesquad.baseball05.domain.matches.dto.MatchDTO;
import com.codesquad.baseball05.infra.dao.MatchDAO;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private MatchDAO matchDAO;

    public MatchService(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public MatchDTO updateUserAtMatch(Long userId, String teamName) {
        MatchDTO matchDTO = matchDAO.findByTeamName(teamName);
        if ( matchDTO.userA != 0 ) {
            matchDTO.setUserB(userId);
        } else {
            matchDTO.setUserA(userId);
        }
        matchDAO.updateUser(matchDTO);

        return matchDTO;
    }

    public boolean isMatchCompleted(Long matchId) {
        MatchDTO matchDTO = matchDAO.findById(matchId);
        if ( matchDTO.userA != null && matchDTO.userB != 0 ) {
            return true;
        }
        return false;
    }

}
