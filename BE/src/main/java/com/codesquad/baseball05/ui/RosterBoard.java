package com.codesquad.baseball05.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RosterBoard {
    private String team;
    private Boolean isOffense;
    private String currentPlayer;
    private List<RoundRecord> roundRecords = new ArrayList<>();
    private Map<String, Integer> totals = new HashMap<>();

    public RosterBoard(String team) {
        this.team = team;
    }
}
