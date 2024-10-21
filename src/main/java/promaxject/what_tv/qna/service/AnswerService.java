package promaxject.what_tv.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import promaxject.what_tv.qna.Answer;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.qna.repository.AnswerRepository;
import promaxject.what_tv.user.SiteUser;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateAt(LocalDateTime.now());
        answer.setAuthor(author);
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
}
