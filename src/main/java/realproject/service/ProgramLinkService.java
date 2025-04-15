package realproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import realproject.dto.ProgramLinkDto;
import realproject.entity.ProgramLink;
import realproject.repository.ProgramLinkRepository;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgramLinkService {
    private final ProgramLinkRepository programLinkRepository;

    public List<ProgramLink> getAllLink() {
        return programLinkRepository.findAll();
    }

    public ProgramLink getProgramLinkById(UUID id) {
        return programLinkRepository.findById(id).orElseThrow(() -> new RuntimeException("ProgramLink not found"));
    }

    public void deleteProgramLink(UUID id) {
        programLinkRepository.deleteById(id);
    }

    public ProgramLink saveProgramLink(ProgramLinkDto programLinkDto) {
        ProgramLink programLink = new ProgramLink();
        programLink.setLink(programLinkDto.getLink());
        programLink.setDescription(programLinkDto.getDescription());
        return   programLinkRepository.save(programLink);
    }


}
