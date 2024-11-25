package promaxject.what_tv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import promaxject.what_tv.domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // Notice에 대한 기본적인 CRUD 작업이 JpaRepository에서 제공됨
    Page<Notice> findAll(Specification<Notice> spec, Pageable pageable);
    Page<Notice> findAll(Pageable pageable);

}
