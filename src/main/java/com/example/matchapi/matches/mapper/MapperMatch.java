package com.example.matchapi.matches.mapper;

import com.example.matchapi.matches.dto.MatchDTO;
import com.example.matchapi.matches.model.Match;
import com.example.matchapi.matches.model.Sport;

public class MapperMatch {
    public static MatchDTO toDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setId(match.getId());
        dto.setDescription(match.getDescription());
        dto.setMatch_Date(match.getMatch_Date());
        dto.setMatch_Time(match.getMatch_Time());
        dto.setTeam_A(match.getTeam_A());
        dto.setTeam_B(match.getTeam_B());
        dto.setSport(Sport.valueOf(match.getSport().name()));
        return dto;
    }

    public static Match toEntity(MatchDTO dto) {
        Match match = new Match();
        match.setId(dto.getId());
        match.setDescription(dto.getDescription());
        match.setMatch_Date(dto.getMatch_Date());
        match.setMatch_Time(dto.getMatch_Time());
        match.setTeam_A(dto.getTeam_A());
        match.setTeam_B(dto.getTeam_B());
        match.setSport(dto.getSport());
        return match;
    }

    public static void updateMatchFromDTO(Match match, MatchDTO dto) {
        match.setDescription(dto.getDescription());
        match.setMatch_Date(dto.getMatch_Date());
        match.setMatch_Time(dto.getMatch_Time());
        match.setTeam_A(dto.getTeam_A());
        match.setTeam_B(dto.getTeam_B());
        match.setSport(dto.getSport());
    }
}
