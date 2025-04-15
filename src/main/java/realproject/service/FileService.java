package realproject.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final Path storagePath = Paths.get(System.getProperty("user.dir"), "uploaded_files");
//    if (!Files.exists(storagePath)) {
//        try {
//            Files.createDirectories(storagePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void saveFile(MultipartFile file) {
        try {
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
            }

            String originalFilename = file.getOriginalFilename();
            String savedFileName = UUID.randomUUID() + "_" + originalFilename;

            Path filePath = storagePath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            throw new RuntimeException("Faylni saqlab bo‘lmadi!", e);
        }
    }

    public List<String> getAllFiles() {
        try {
            return Files.list(storagePath)
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Fayllar ro‘yxatini olishda xatolik!", e);
        }
    }

    public Resource getFile(String filename) {
        try {
            Path filePath = storagePath.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fayl mavjud emas yoki o‘qib bo‘lmaydi: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Noto‘g‘ri fayl yo‘li: " + filename, e);
        }
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = storagePath.resolve(filename);
            Files.deleteIfExists(filePath); // Faylni o'chirish

        } catch (IOException e) {
            throw new RuntimeException("Faylni o'chirishda xatolik!", e);
        }
    }

}
