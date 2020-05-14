package com.codesquad.baseball05.domain.game.dto;

import com.codesquad.baseball05.domain.game.entity.Game;
import com.codesquad.baseball05.domain.matches.entity.Matches;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllTablesDTO {

    private UserTeamDTO userA;

    private UserTeamDTO userB;

    private Matches matches;

    private Game game;
}
