package com.example.matchapi.matches.dto;

import com.example.matchapi.matches.model.Sport;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class MatchOddDTOFindAll {
    private Long id;
    private String description;
    private LocalDate match_Date;
    private LocalTime match_Time;
    private String team_A;
    private String team_B;
    private Sport sport; // or use enum if you prefer
    private List<MatchOddDTO> matchOddDTOList;
}

