package com.codesquad.baseball05.domain.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inning {

    @Id
    private Long id;

    private Half firstHalf;

    private Half secondHalf;

    private String half;

    public boolean isFirstHalf() {
        return this.getHalf().equals("top");
    }
}
