package com.codesquad.baseball05.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoundRecord {
    private String player;
    //타석, 안타, 아웃, 평균
    private int mounts;
    private int hits;
    private int outs;
    private Long average;
}
