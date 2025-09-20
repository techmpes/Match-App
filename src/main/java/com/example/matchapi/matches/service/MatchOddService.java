package com.example.matchapi.matches.service;

import com.example.matchapi.matches.dto.MatchOddDTO;
import com.example.matchapi.matches.dto.MatchOddDTOFindAll;
import com.example.matchapi.matches.mapper.MapperMatchOdd;
import com.example.matchapi.matches.model.Match;
import com.example.matchapi.matches.model.MatchOdd;
import com.example.matchapi.matches.repository.MatchOddRepository;
import com.example.matchapi.matches.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchOddService {

    private final MatchOddRepository matchOddRepository;

    private final MatchRepository matchRepository;

    public MatchOddService(MatchOddRepository matchOddRepository, MatchRepository matchRepository) {
        this.matchOddRepository = matchOddRepository;
        this.matchRepository = matchRepository;
    }

    public List<MatchOddDTOFindAll> findAll() {

        List<Match> matches = matchRepository.findAll();
        List<Match> matchesWithOdds = matches.stream()
                .filter(match -> match.getMatchOdd() != null && !match.getMatchOdd().isEmpty())
                .collect(Collectors.toList());

        return MapperMatchOdd.mapAllResults(matchesWithOdds);
    }

    public MatchOddDTO findById(Long id) {
        MatchOdd matchEntity = matchOddRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match odd not found with ID: " + id));
        return MapperMatchOdd.toDTO(matchEntity);
    }

    public MatchOddDTO save(MatchOddDTO matchOdd) {
        Match match = matchRepository.findById(matchOdd.getMatchId())
                .orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + matchOdd.getMatchId()));
        MatchOdd matchEntity = MapperMatchOdd.toEntity(matchOdd,match);
        return MapperMatchOdd.toDTO(matchOddRepository.save(matchEntity));
    }

    public MatchOddDTO deleteById(Long id) {
        MatchOdd matchOdd = matchOddRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match Odd not found with ID: " + id));

        matchOddRepository.delete(matchOdd);
        return MapperMatchOdd.toDTO(matchOdd);
    }

    public MatchOddDTO update(Long id, MatchOddDTO updatedMatchOdd) {
        Match existingMatch = matchRepository.findById(updatedMatchOdd.getMatchId())
                .orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + updatedMatchOdd.getMatchId()));

        MatchOdd existingMatchOdd = matchOddRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match Odd not found with ID: " + id));

        MapperMatchOdd.updateEntityFromDTO(existingMatchOdd, updatedMatchOdd,existingMatch);
        return MapperMatchOdd.toDTO(matchOddRepository.save(existingMatchOdd));
    }
}