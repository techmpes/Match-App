package com.example.matchapi.matches.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchOddDTO {
    private Long id;
    @NotBlank(message = "Description is required")
    private String specifier;
    @NotNull(message = "Odd is required")
    @Positive(message = "Odd must be a positive number")
    private Double odd;
    @NotNull(message = "Match ID is required")
    @Positive(message = "Match ID must be a positive number")
    private Long matchId;
}

