package com.codesquad.baseball05.domain.team.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Record {

    @Id
    private Long id;

    private int pitch;

    private int mounts;

    private int hit;

    private int strike;

    private int ball;

    private int outs;

    private Double battingAverage;
}
