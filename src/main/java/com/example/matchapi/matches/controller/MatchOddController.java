package com.example.matchapi.matches.controller;

import com.example.matchapi.matches.dto.MatchDTO;
import com.example.matchapi.matches.dto.MatchOddDTO;
import com.example.matchapi.matches.dto.MatchOddDTOFindAll;
import com.example.matchapi.matches.model.MatchOdd;
import com.example.matchapi.matches.service.MatchOddService;
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

    @GetMapping
    public List<MatchOddDTOFindAll> getAllOdds() {
        return matchOddService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchOddDTO> getOddById(@PathVariable Long id) {
        MatchOddDTO oddsDTO = matchOddService.findById(id);
        return ResponseEntity.ok(oddsDTO);
    }

    @PostMapping
    public MatchOddDTO createOdd(@Valid @RequestBody MatchOddDTO odds) {
        return matchOddService.save(odds);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchOddDTO> updateOdd(@PathVariable Long id, @Valid @RequestBody MatchOddDTO updatedOdds) {
        MatchOddDTO odds = matchOddService.update(id, updatedOdds);
        return ResponseEntity.ok(odds);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MatchOddDTO> deleteOdd(@PathVariable Long id) {
        MatchOddDTO deletedMatchOdd = matchOddService.deleteById(id);
        return ResponseEntity.ok(deletedMatchOdd);
    }
}
