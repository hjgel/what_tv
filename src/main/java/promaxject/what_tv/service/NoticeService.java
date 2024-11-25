package promaxject.what_tv.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import promaxject.what_tv.domain.*;
import promaxject.what_tv.repository.NoticeRepository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {


    private final NoticeRepository noticeRepository;

    private Specification<Notice> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Notice> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Notice, SiteUser> u1 = q.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("nickname"), "%" + kw + "%"));    // 질문 작성자
            }
        };
    }

    // 모든 공지사항 가져오기
    public List<Notice> getAllNotices() {
        return this.noticeRepository.findAll();
    }

    public Page<Notice> getAllNotices(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdAt"));
        Pageable pageable = PageRequest.of(page, 15, Sort.by(sorts));
        Specification<Notice> spec = search(kw);
        return this.noticeRepository.findAll(spec, pageable);
    }

    // 공지사항 생성
    public void createNotice(String title, SiteUser user, String content) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setAuthor(user);
        notice.setContent(content);
        noticeRepository.save(notice);
    }

    // 공지사항 수정
    public void updateNotice(Long id, String title,SiteUser user, String content) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
        notice.setTitle(title);
        notice.setAuthor(user);
        notice.setContent(content);
        noticeRepository.save(notice);
    }

    // 공지사항 상세보기
    public Notice getNoticeById(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
    }

    public void delete(Notice notice) {
        this.noticeRepository.delete(notice);
    }
}