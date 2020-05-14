package com.codesquad.baseball05.domain.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundRecordDTO {
    private String player;
    private int mounts;
    private int hits;
    private int outs;
}
