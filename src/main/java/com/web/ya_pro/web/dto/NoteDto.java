package com.web.ya_pro.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NoteDto implements Serializable {
    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @NotEmpty
    private String content;
}
