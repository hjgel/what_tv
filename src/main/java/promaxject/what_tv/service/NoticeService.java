package promaxject.what_tv.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import promaxject.what_tv.domain.Content;
import promaxject.what_tv.domain.Notice;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.NoticeRepository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {


    private final NoticeRepository noticeRepository;

    // 모든 공지사항 가져오기
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
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
}