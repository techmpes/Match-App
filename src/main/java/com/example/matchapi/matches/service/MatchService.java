package com.example.matchapi.matches.service;

import com.example.matchapi.matches.dto.MatchDTO;
import com.example.matchapi.matches.mapper.MapperMatch;
import com.example.matchapi.matches.model.Match;
import com.example.matchapi.matches.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<MatchDTO> findAll() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(MapperMatch::toDTO)
                .collect(Collectors.toList());
    }

    public MatchDTO findById(Long id) {
        Match matchEntity = matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + id));
        return MapperMatch.toDTO(matchEntity);
    }

    public MatchDTO save(MatchDTO match) {
        Match matchEntity = MapperMatch.toEntity(match);
        return MapperMatch.toDTO(matchRepository.save(matchEntity));
    }

    public MatchDTO deleteById(Long id) {
        Match match = matchRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + id));

        matchRepository.delete(match);
        return MapperMatch.toDTO(match);
    }

    public MatchDTO update(Long id, MatchDTO updatedMatch) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + id));

        MapperMatch.updateMatchFromDTO(existingMatch, updatedMatch);
        return MapperMatch.toDTO(matchRepository.save(existingMatch));
    }
}
