package promaxject.what_tv.service.user;

import promaxject.what_tv.dto.PostImageDto;
import promaxject.what_tv.dto.ProfileImageDto;
import promaxject.what_tv.dto.ProfileImageResponseDto;

public interface ProfileImageService {

    /**
     * 프로필 사진 upload
     * @param username 유저 정보
     */
    void upload(ProfileImageDto profileImageDto, String username);

    /**
     * 이미지 url 조회
     *
     * @param username 유저 정보
     * @return 이미지 url
     */
    ProfileImageResponseDto findImage(String username);
}
