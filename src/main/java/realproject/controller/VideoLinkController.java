package realproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realproject.dto.VideoLinkDto;
import realproject.entity.VideoLink;
import realproject.service.VideoLinkService;


import java.util.UUID;

@RestController
@RequestMapping("/api/video_link")
@RequiredArgsConstructor
public class VideoLinkController {
    private final VideoLinkService videoLinkService;

    @GetMapping
    public HttpEntity<?> getVideoLinks() {
        return ResponseEntity.ok(videoLinkService.getAllLink());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getVideoLinkById(@PathVariable UUID id) {
        return ResponseEntity.ok(videoLinkService.getVideoLinkById(id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        videoLinkService.deleteVideoLink(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public HttpEntity<?> createVideoLink(@RequestBody VideoLinkDto videoLinkDto) {
        VideoLink saveVideoLink = videoLinkService.saveVideoLink(videoLinkDto);
        return ResponseEntity.ok(saveVideoLink);
    }

}
