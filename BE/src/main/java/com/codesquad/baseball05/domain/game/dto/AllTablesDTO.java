package com.codesquad.baseball05.domain.game.dto;

import com.codesquad.baseball05.domain.game.entity.Game;
import com.codesquad.baseball05.domain.game.entity.Inning;
import com.codesquad.baseball05.domain.matches.entity.Matches;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.SQLException;
import java.util.List;

@Getter
@AllArgsConstructor
public class AllTablesDTO {

    private UserTeamDTO userA;

    private UserTeamDTO userB;

    private Matches matches;

    private Game game;

    public void setHomeAndOffense() throws SQLException {
        Matches matches = this.getMatches();
        boolean isUserAHome = this.getUserA().getTeam().getName().equals(matches.getHomeTeam());

        boolean isUserAOffense = isUserAHome ^ isTopHalf(this);

        if (isUserAOffense) {
            this.getUserA().setOffense(true);
        } else {
            this.getUserB().setOffense(true);
        }

        this.getUserA().setHome(isUserAHome);
        this.getUserB().setHome(!isUserAHome);
    }

    private boolean isTopHalf(AllTablesDTO allTablesDTO) {
        List<Inning> innings = allTablesDTO.getGame().getInnings();
        Inning nowInning = innings.get(innings.size() - 1);
        return nowInning.isFirstHalf();
    }
}
