package com.codesquad.baseball05.domain.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
