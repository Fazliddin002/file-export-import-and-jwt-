package realproject.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import realproject.security.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}