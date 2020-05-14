package com.codesquad.baseball05.domain.game.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RosterBoardDTO {
    private String team;
    private Boolean isOffense = false;
    private String currentPlayer;
    private List<RoundRecordDTO> roundRecordDTOList = new ArrayList<>();

    public RosterBoardDTO(String team) {
        this.team = team;
    }
}
