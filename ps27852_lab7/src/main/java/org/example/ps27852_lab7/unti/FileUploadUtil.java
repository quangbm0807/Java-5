package org.example.ps27852_lab7.unti;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    // Thư mục mặc định để lưu file

    /**
     * Lưu tệp được upload với tên file truyền vào.
     *
     * @param fileName    Tên của tệp.
     * @param multipartFile Đối tượng MultipartFile chứa tệp được upload.
     * @throws IOException Nếu có lỗi khi lưu tệp.
     */
    public static void saveFile(String fileName, MultipartFile multipartFile, String foldeName) throws IOException {
        String DEFAULT_UPLOAD_DIR = "src/main/resources/static/";
        DEFAULT_UPLOAD_DIR += foldeName;
        Path uploadPath = Paths.get(DEFAULT_UPLOAD_DIR);

        // Tạo thư mục nếu chưa tồn tại
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Không thể lưu tệp: " + fileName, e);
        }
    }
}
