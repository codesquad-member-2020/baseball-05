package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@AllArgsConstructor
public class Plate {

    @Id
    private Long id;

    private int strike;

    private int ball;

    private String firstBaseMan;

    private String secondBaseMan;

    private String thirdBaseMan;

    private List<Round> rounds;
}
