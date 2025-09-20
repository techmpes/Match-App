package com.example.matchapi.matches.dto;

import com.example.matchapi.matches.model.Sport;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MatchDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Auto-generated match ID")
    private Long id;

    @NotBlank(message = "Description is required")
    private String description;

    @Schema(example = "2025-09-25")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Match date is required")
    private LocalDate match_Date;

    @Schema(example = "00:00:00")
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Match time is required")
    private LocalTime match_Time;

    @NotBlank(message = "Team A is required")
    private String team_A;

    @NotBlank(message = "Team B is required")
    private String team_B;

    @NotNull(message = "Sport is required")
    private Sport sport; // or use enum if you prefer

}
