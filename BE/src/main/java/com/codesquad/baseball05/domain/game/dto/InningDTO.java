package com.codesquad.baseball05.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InningDTO {
    private Long id;
    private Long gameId;
    private Long firstHalfId;
    private Long secondHalfId;
    private String half;

    public InningDTO(Long id, String half) {
        this.id = id;
        this.half = half;
    }
}
