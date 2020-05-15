package com.codesquad.baseball05.domain.game.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HalfIdDTO {
    private List<Integer> firstHalfId;
    private List<Integer> secondHalfId;
}
