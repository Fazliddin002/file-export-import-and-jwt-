package realproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
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

    // 📥 Faylni yuklash
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.saveFile(file);
        return ResponseEntity.ok("✅ Fayl muvaffaqiyatli yuklandi!");
    }

    // 📄 Barcha fayllar ro'yxati
    @GetMapping
    public ResponseEntity<List<String>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    // 📤 Faylni yuklab olish
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource file = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    // 🗑️ Faylni o'chirish
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        fileService.deleteFile(filename); // Faylni o'chirish
        return ResponseEntity.ok("✅ Fayl muvaffaqiyatli o'chirildi!");
    }

}
