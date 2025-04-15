package realproject.security.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import realproject.security.entity.Attachment;

import realproject.security.entity.User;
import realproject.security.repo.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final UserRepository userRepository;


    @Transactional
    @PostMapping("/user/{id}")
    public void returnUserImage(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new FileNotFoundException(id.toString()));
        Attachment attachment = user.getAttachment();
        response.getOutputStream().write(attachment.getImage());
    }

}
