package promaxject.what_tv.service.post;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import promaxject.what_tv.exception.DataNotFoundException;
import promaxject.what_tv.domain.Image;
import promaxject.what_tv.dto.PostImageDto;
import promaxject.what_tv.repository.ImageRepository;
import promaxject.what_tv.domain.Content;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.repository.PostRepository;
import promaxject.what_tv.domain.SiteUser;

import javax.persistence.criteria.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Value("${file.boardImagePath}")
    private String uploadFolder;


    private Specification<Post> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Post> p, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Post, SiteUser> u1 = p.join("author", JoinType.LEFT);
                Join<Post, Content> a = p.join("contentList", JoinType.LEFT);
                Join<Post, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(p.get("title"), "%" + kw + "%"), // 제목
                        cb.like(p.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }

//    public Post getPost(Long id) {
//        Optional<Post> post = this.postRepository.findById(id);
//        if(post.isPresent()) {
//            return post.get();
//        } else {
//            throw new DataNotFoundException("Post not found");
//        }
//    }



    public Post getPost(Long id) {
        Post post = postRepository.findWithImagesById(id)
                .orElseThrow(() -> new DataNotFoundException("Post not found"));

        logger.info("Loaded Post with images: {}", post.getImageList());  // 이미지 리스트 로그 확인
        return post;
    }

    @Transactional
    public void create(String title, String content, SiteUser user, PostImageDto postImageDto, Integer price) {
        Post p = new Post();
        p.setTitle(title);
        p.setContent(content);
        p.setCreateAt(LocalDateTime.now());
        p.setAuthor(user);
        p.setOrder_price(BigDecimal.valueOf(price));
        this.postRepository.save(p);

        if(postImageDto.getFiles() != null && !postImageDto.getFiles().isEmpty()) {
            for (MultipartFile file : postImageDto.getFiles()) {
                UUID uuid = UUID.randomUUID();
                String imageFileName = uuid + "_" + file.getOriginalFilename();

                File destinationFile = new File(uploadFolder + imageFileName);
                destinationFile.getParentFile().mkdirs();
                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Image image = Image.builder().url("/postImages/" + imageFileName).post(p).build();

                imageRepository.save(image);
            }
        }
    }

    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
        post.setUpdateAt(LocalDateTime.now());
        this.postRepository.save(post);
    }

    public Page<Post> getList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createAt"));
        Pageable pageable = PageRequest.of(page, 15, Sort.by(sorts));
        Specification<Post> spec = search(kw);
        return this.postRepository.findAll(spec, pageable);
    }

    public List<Post> getPostByUser(SiteUser user) {
        return this.postRepository.findByAuthor(user);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }
}
