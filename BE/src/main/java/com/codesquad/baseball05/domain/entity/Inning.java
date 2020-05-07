package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class Inning {

    @Id
    private Long id;

    private Half firstHalf;

    private Half secondHalf;

    private String halfOfFirstOrSecond;
}
