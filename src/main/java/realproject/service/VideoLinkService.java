package realproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import realproject.dto.VideoLinkDto;
import realproject.entity.VideoLink;
import realproject.repository.VideoLinkRepository;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoLinkService {
    private final VideoLinkRepository videoLinkRepository;

    public List<VideoLink> getAllLink() {
        return videoLinkRepository.findAll();
    }

    public VideoLink getVideoLinkById(UUID id) {
        return videoLinkRepository.findById(id).orElseThrow(() -> new RuntimeException("ProgramLink not found"));
    }

    public void deleteVideoLink(UUID id) {
        videoLinkRepository.deleteById(id);
    }

    public VideoLink saveVideoLink(VideoLinkDto videoLinkDto) {
        VideoLink videoLink = new VideoLink();
        videoLink.setLink(videoLinkDto.getLink());
        videoLink.setDescription(videoLinkDto.getDescription());

        return videoLinkRepository.save(videoLink);

    }

}
