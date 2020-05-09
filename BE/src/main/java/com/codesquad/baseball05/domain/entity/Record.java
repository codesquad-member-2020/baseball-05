package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    private Long id;

    private int mount;

    private int hit;

    private int strike;

    private int ball;

    private int outs;

    private Double battingAverage;
}
