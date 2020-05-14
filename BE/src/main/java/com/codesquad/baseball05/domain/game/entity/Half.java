package com.codesquad.baseball05.domain.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Half {

    @Id
    private Long id;

    private int lastBatPlayer;

    private int totalPlate;

    private int outs;

    private int hit;

    private int point;

    private List<Plate> plates;
}
