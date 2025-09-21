package com.example.matchapi.matches.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchOddDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Auto-generated match ID")
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

