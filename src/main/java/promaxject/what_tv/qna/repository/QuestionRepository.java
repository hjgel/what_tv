package promaxject.what_tv.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.qna.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);
}
