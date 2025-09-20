package com.example.matchapi.matches.mapper;

import com.example.matchapi.matches.dto.MatchDTO;
import com.example.matchapi.matches.dto.MatchOddDTO;
import com.example.matchapi.matches.dto.MatchOddDTOFindAll;
import com.example.matchapi.matches.model.Match;
import com.example.matchapi.matches.model.MatchOdd;
import com.example.matchapi.matches.model.Sport;

import java.util.ArrayList;
import java.util.List;

public class MapperMatchOdd {
    public static MatchOddDTO toDTO(MatchOdd entity) {
        MatchOddDTO dto = new MatchOddDTO();
        dto.setId(entity.getId());
        dto.setSpecifier(entity.getSpecifier());
        dto.setOdd(entity.getOdd());
        dto.setMatchId(entity.getMatch() != null ? entity.getMatch().getId() : null);
        return dto;
    }

    public static MatchOdd toEntity(MatchOddDTO dto, Match match) {
        MatchOdd entity = new MatchOdd();
        entity.setId(dto.getId()); // Optional: skip if ID is handled by DB
        entity.setSpecifier(dto.getSpecifier());
        entity.setOdd(dto.getOdd());
        entity.setMatch(match);
        return entity;
    }

    public static void updateEntityFromDTO(MatchOdd entity, MatchOddDTO dto, Match match) {
        entity.setSpecifier(dto.getSpecifier());
        entity.setOdd(dto.getOdd());
        entity.setMatch(match);
    }

    public static List<MatchOddDTOFindAll> mapAllResults(List<Match> matches){
        List<MatchOddDTOFindAll> matchesAll = new ArrayList<>();
        for(Match match : matches){
            MatchOddDTOFindAll matchOddDTOFindAll = new MatchOddDTOFindAll();
            matchOddDTOFindAll.setId(match.getId());
            matchOddDTOFindAll.setDescription(match.getDescription());
            matchOddDTOFindAll.setMatch_Date(match.getMatch_Date());
            matchOddDTOFindAll.setMatch_Time(match.getMatch_Time());
            matchOddDTOFindAll.setTeam_A(match.getTeam_A());
            matchOddDTOFindAll.setTeam_B(match.getTeam_B());
            matchOddDTOFindAll.setSport(match.getSport());
            List<MatchOdd> matchOdds = match.getMatchOdd();
            List<MatchOddDTO> matchOddDTOList = new ArrayList<>();
            for(MatchOdd matchOdd : matchOdds){
                MatchOddDTO matchOddDTO = new MatchOddDTO();
                matchOddDTO.setMatchId(matchOdd.getMatch().getId());
                matchOddDTO.setId(matchOdd.getId());
                matchOddDTO.setSpecifier(matchOdd.getSpecifier());
                matchOddDTO.setOdd(matchOdd.getOdd());
                matchOddDTOList.add(matchOddDTO);
            }
            matchOddDTOFindAll.setMatchOddDTOList(matchOddDTOList);
            matchesAll.add(matchOddDTOFindAll);
        }
        return matchesAll;
    }
}
