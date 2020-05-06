package com.codesquad.baseball05.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RosterBoard {
    private String team;
    private Boolean isOffense;
    private List<RoundRecord> roundRecords = new ArrayList<>();
    //현재 타자
    private String currentPlayer;
    public RosterBoard(String team) {
        this.team = team;
    }
}
