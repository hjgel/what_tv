package promaxject.what_tv.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import promaxject.what_tv.qna.Answer;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.qna.repository.AnswerRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreate_at(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
}
