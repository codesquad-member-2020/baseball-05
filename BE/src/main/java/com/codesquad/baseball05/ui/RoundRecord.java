package com.codesquad.baseball05.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoundRecord {
    private String player;
    private int mounts;
    private int hits;
    private int outs;
    private Long average;
}
