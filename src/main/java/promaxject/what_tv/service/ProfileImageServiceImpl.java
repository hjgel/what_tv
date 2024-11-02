package promaxject.what_tv.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import promaxject.what_tv.dto.PostImageDto;
import promaxject.what_tv.domain.ProfileImage;
import promaxject.what_tv.dto.ProfileImageDto;
import promaxject.what_tv.dto.ProfileImageResponseDto;
import promaxject.what_tv.repository.ImageRepository;
import promaxject.what_tv.repository.ProfileImageRepository;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.UserRepository;
import promaxject.what_tv.service.user.ProfileImageService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileImageServiceImpl.class);

    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;

    @Value("${file.profileImagePath}")
    private String uploadFolder;

    @Override
    public void upload(ProfileImageDto profileImageDto, String username) {
        SiteUser siteUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("id가 존재하지 않습니다."));
        MultipartFile file = profileImageDto.getFile();

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();
        File directory = new File(uploadFolder);
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리가 없으면 생성
        }

        File destinationFile = new File(uploadFolder + imageFileName);

        try {
            file.transferTo(destinationFile);

            ProfileImage profileImage = profileImageRepository.findByUser(siteUser);
            if (profileImage != null) {
                profileImage.updateUrl("/profileImages/" + imageFileName);
            } else {
                profileImage = ProfileImage.builder()
                        .user(siteUser)
                        .url("/profileImages/" + imageFileName)
                        .build();
            }
            profileImageRepository.save(profileImage);
        } catch (IOException e) {
            logger.error("File upload failed", e);
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public ProfileImageResponseDto findImage(String username) {
        SiteUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("id가 존재하지 않습니다."));
        ProfileImage image = profileImageRepository.findByUser(user);

        String defaultImageUrl = "/profileImages/anonymous.png";

        if (image == null) {
            return ProfileImageResponseDto.builder().url(defaultImageUrl).build();
        } else {
            return ProfileImageResponseDto.builder().url(image.getUrl()).build();
        }
    }
}