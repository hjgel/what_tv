package promaxject.what_tv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.qna.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WhatTvApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("이건 내용이에요");
//        q1.setCreate_at(LocalDateTime.now());
//        this.questionRepository.save(q1);

        // findBySubject
        Question question = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, question.getId());

        // 질문 데이터 수정하기
        Optional<Question> oq = this.questionRepository.findById(1L);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

}
