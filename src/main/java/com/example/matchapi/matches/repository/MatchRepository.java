package com.example.matchapi.matches.repository;

import com.example.matchapi.matches.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
