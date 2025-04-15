package realproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import realproject.entity.VideoLink;


import java.util.UUID;

public interface VideoLinkRepository extends JpaRepository<VideoLink, UUID> {
}