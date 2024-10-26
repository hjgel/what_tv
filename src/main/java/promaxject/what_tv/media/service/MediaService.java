//package promaxject.what_tv.media.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import promaxject.what_tv.media.Media;
//import promaxject.what_tv.media.repository.MediaRepository;
//import promaxject.what_tv.post.Post;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class MediaService {
//
//    @Value("${file.upload-dir}") // application.properties에서 파일 경로 설정
//    private String uploadDir;
//
//    private final MediaRepository mediaRepository;
//
//    public List<Media> uploadFiles(Post post, List<MultipartFile> files) throws IOException {
//        List<Media> mediaList = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); // 파일 이름에 타임스탬프 추가
//            String filePath = uploadDir + File.separator + fileName;
//            Files.createDirectories(Paths.get(uploadDir)); // 폴더가 없으면 생성
//            file.transferTo(new File(filePath));
//
//            Media media = new Media();
//            media.setFileName(fileName);
//            media.setFileUrl(filePath); // 파일 경로 저장
//            media.setFileType(file.getContentType());
//            media.setCreatedAt(LocalDateTime.now());
//            media.setPost(post); // 연결된 Post 설정
//            mediaList.add(media);
//        }
//        return mediaRepository.saveAll(mediaList);
//    }
//}