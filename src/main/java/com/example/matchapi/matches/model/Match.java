package com.example.matchapi.matches.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDate match_Date;
    private LocalTime match_Time;

    private String team_A;
    private String team_B;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchOdd> matchOdd;

}
