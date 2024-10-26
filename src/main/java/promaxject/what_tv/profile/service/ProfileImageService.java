package promaxject.what_tv.profile.service;

import promaxject.what_tv.image.dto.PostImageDto;
import promaxject.what_tv.post.dto.PostResponseDTO;

public interface ProfileImageService {

    /**
     * 프로필 사진 upload
     * @param PostImageDto file
     * @param email 유저 정보
     */
    void upload(PostImageDto postImageDto, String email);

    /**
     * 이미지 url 조회
     * @param email 유저 정보
     * @return 이미지 url
     */
    PostResponseDTO findImage(String email);
}
