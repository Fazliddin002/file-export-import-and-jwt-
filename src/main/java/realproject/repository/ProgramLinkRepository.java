package realproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import realproject.entity.ProgramLink;


import java.util.UUID;

public interface ProgramLinkRepository extends JpaRepository<ProgramLink, UUID> {
}