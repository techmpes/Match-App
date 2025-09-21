package com.example.matchapi.matches.controller;

import com.example.matchapi.matches.dto.MatchDTO;
import com.example.matchapi.matches.dto.MatchOddDTO;
import com.example.matchapi.matches.dto.MatchOddDTOFindAll;
import com.example.matchapi.matches.model.MatchOdd;
import com.example.matchapi.matches.service.MatchOddService;
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
@RequestMapping("/api/odd")
public class MatchOddController {

    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @Operation(tags = {"Match Odd"},
            summary = "Get all Match Odds",
            description = "The service returns all matches that has at least one match odd as a list of MatchOddDTOFindAll. " +
                    "If the no match odds exist for the match then the match will not be returned at all.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request",
                            content = @Content(schema = @Schema(implementation = MatchOddDTOFindAll.class)))
            })
    @GetMapping
    public List<MatchOddDTOFindAll> getAllOdds() {
        return matchOddService.findAll();
    }

    @Operation(tags = {"Match Odd"},
            summary = "Get Match Odd by id",
            description = "The service returns the related info in the dto MatchOddDTO by id of the match odd",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request",
                            content = @Content(schema = @Schema(implementation = MatchOddDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Match odd not found with ID: id")
            })
    @GetMapping("/{id}")
    public ResponseEntity<MatchOddDTO> getOddById(@PathVariable Long id) {
        MatchOddDTO oddsDTO = matchOddService.findById(id);
        return ResponseEntity.ok(oddsDTO);
    }

    @Operation(tags = {"Match Odd"},
            summary = "Create a new Match odd",
            description = "Creates a match odd using the data provided in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request",
                    content = @Content(schema = @Schema(implementation = MatchOddDTO.class))),
            @ApiResponse(responseCode = "400", description = "Show validation with missing, wrong values"),
            @ApiResponse(responseCode = "500", description = "Show message with caught exception")
    })
    @PostMapping
    public MatchOddDTO createOdd(@Valid @RequestBody MatchOddDTO odds) {
        return matchOddService.save(odds);
    }

    @Operation(tags = {"Match Odd"},
            summary = "Update existing Match odd",
            description = "Update a certain match odd with the content of the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(schema = @Schema(implementation = MatchOddDTO.class))),
            @ApiResponse(responseCode = "400", description = "Show validation with missing, wrong values"),
            @ApiResponse(responseCode = "404", description =  "Match not found with ID: id OR Match Odd not found with ID: id"),
            @ApiResponse(responseCode = "500", description = "Show message with caught exception")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MatchOddDTO> updateOdd(@PathVariable Long id, @Valid @RequestBody MatchOddDTO updatedOdds) {
        MatchOddDTO odds = matchOddService.update(id, updatedOdds);
        return ResponseEntity.ok(odds);
    }

    @Operation(tags = {"Match Odd"},
            summary = "Delete an existing Match odd",
            description = "The service delete the match corresponding the the match odd id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful delete",
                    content = @Content(schema = @Schema(implementation = MatchOddDTO.class))),
            @ApiResponse(responseCode = "404", description = "Match odd not found with ID: id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MatchOddDTO> deleteOdd(@PathVariable Long id) {
        MatchOddDTO deletedMatchOdd = matchOddService.deleteById(id);
        return ResponseEntity.ok(deletedMatchOdd);
    }
}
