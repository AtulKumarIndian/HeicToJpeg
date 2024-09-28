package com.example.converter.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/convert")
@Slf4j
public class ImageConversionController {

    private final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    @PostMapping(value = "/heic-to-jpeg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> convertHeicToJpeg(@RequestParam("file") MultipartFile file)
            throws IOException, InterruptedException {

            log.info(file.getOriginalFilename());
            File tempHeicFile = new File(TEMP_DIR + File.separator + file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempHeicFile);

            // Target file for converted JPEG
            String outputFileName = tempHeicFile.getName().replace(".heic", ".jpeg");
            File jpegFile = new File(TEMP_DIR + File.separator + outputFileName);

            // Invoke ImageMagick to convert HEIC to JPEG
            ProcessBuilder pb = new ProcessBuilder("\"C:\\Program Files\\ImageMagick-7.1.1-Q16-HDRI\\magick.exe\"", tempHeicFile.getAbsolutePath(),
                    jpegFile.getAbsolutePath());
            Process process = pb.start();
            process.waitFor(); // Wait for conversion to complete

            // Read the converted JPEG file to byte array
            byte[] jpegData = FileUtils.readFileToByteArray(jpegFile);

            // Cleanup temporary files
            tempHeicFile.delete();
            jpegFile.delete();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + outputFileName + "\"")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(jpegData);

    }
}
