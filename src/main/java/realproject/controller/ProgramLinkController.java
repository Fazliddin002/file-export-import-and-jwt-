package realproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realproject.dto.ProgramLinkDto;
import realproject.entity.ProgramLink;
import realproject.service.ProgramLinkService;


import java.util.UUID;

@RestController
@RequestMapping("/api/program_link")
@RequiredArgsConstructor
public class ProgramLinkController {
    private final ProgramLinkService programLinkService;
    
    @GetMapping
    public HttpEntity<?> getProgramLinks() {
       return ResponseEntity.ok(programLinkService.getAllLink());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProgramLinkById(@PathVariable UUID id) {
        return ResponseEntity.ok(programLinkService.getProgramLinkById(id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable UUID id) {
        programLinkService.deleteProgramLink(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public HttpEntity<?> createProgramLink(@RequestBody ProgramLinkDto programLinkDto) {
        ProgramLink saveProgramLink = programLinkService.saveProgramLink(programLinkDto);
        return ResponseEntity.ok(saveProgramLink);
    }



}
