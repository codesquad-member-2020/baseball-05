package com.codesquad.baseball05.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
