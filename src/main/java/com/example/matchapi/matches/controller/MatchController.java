package com.example.matchapi.matches.controller;

import com.example.matchapi.matches.dto.MatchDTO;
import com.example.matchapi.matches.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @Operation(tags = {"Match"},
            summary = "Get all Matches",
            description = "The service returns all the matches as a list of MatchDTO",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request",
                            content = @Content(schema = @Schema(implementation = MatchDTO.class)))
            })
    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.findAll();
    }

    @Operation(tags = {"Match"},
            summary = "Get Match by id",
            description = "The service returns the related info in the dto MatchDTO by id of the match",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request",
                            content = @Content(schema = @Schema(implementation = MatchDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Match not found with ID: id")
            })
    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        MatchDTO match = matchService.findById(id);
        return ResponseEntity.ok(match);
    }

    @Operation(tags = {"Match"},
            summary = "Create a new Match",
            description = "Creates a match using the data provided in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request",
                    content = @Content(schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(responseCode = "400", description = "Show validation with missing values"),
            @ApiResponse(responseCode = "500", description = "Show message with caught exception")
    })
    @PostMapping
    public MatchDTO createMatch(@Valid @RequestBody MatchDTO match) {
        return matchService.save(match);
    }

    @Operation(tags = {"Match"},
            summary = "Update existing Match",
            description = "Update a certain match with the content of the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(responseCode = "400", description = "Show validation with missing values"),
            @ApiResponse(responseCode = "404", description =  "Match not found with ID: id"),
            @ApiResponse(responseCode = "500", description = "Show message with caught exception")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @Valid @RequestBody MatchDTO updatedMatch) throws Exception {
        MatchDTO match = matchService.update(id, updatedMatch);
        return ResponseEntity.ok(match);
    }

    @Operation(tags = {"Match"},
            summary = "Delete an existing Match",
            description = "The service delete the match corresponding the the match id and match odds related to it"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful delete",
                    content = @Content(schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(responseCode = "404", description = "Match not found with ID: id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MatchDTO> deleteMatch(@PathVariable Long id) {
        MatchDTO deletedMatch = matchService.deleteById(id);
        return ResponseEntity.ok(deletedMatch);
    }
}
