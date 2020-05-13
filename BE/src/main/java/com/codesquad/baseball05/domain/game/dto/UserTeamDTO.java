package com.codesquad.baseball05.domain.game.dto;

import com.codesquad.baseball05.domain.team.entity.Team;
import com.codesquad.baseball05.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserTeamDTO {

    private User user;

    private Team team;

    private boolean isHome;

    private boolean isOffense;

    public UserTeamDTO(User user, Team team) {
        this.user = user;
        this.team = team;
    }
}
