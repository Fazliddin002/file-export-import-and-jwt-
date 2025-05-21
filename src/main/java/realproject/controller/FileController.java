package realproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import realproject.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public HttpEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.saveFile(file);
        return ResponseEntity.ok("✅ Fayl muvaffaqiyatli yuklandi!");
    }

    @GetMapping
    public HttpEntity<?> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @GetMapping("/download/{fileName:.+}")
    public HttpEntity<?> downloadFile(@PathVariable String fileName) {
        Resource file = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @DeleteMapping("/delete/{filename}")
    public HttpEntity<?> deleteFile(@PathVariable String filename) {
        fileService.deleteFile(filename);
        return ResponseEntity.ok("✅ Fayl muvaffaqiyatli o'chirildi!");
    }

}
