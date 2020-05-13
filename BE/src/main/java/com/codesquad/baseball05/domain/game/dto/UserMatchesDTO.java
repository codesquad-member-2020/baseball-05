package com.codesquad.baseball05.domain.game.dto;

import com.codesquad.baseball05.domain.matches.entity.Matches;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMatchesDTO {

    private UserTeamDTO userA;

    private UserTeamDTO userB;

    private Matches matches;
}
