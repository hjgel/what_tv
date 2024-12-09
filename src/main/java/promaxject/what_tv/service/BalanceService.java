package promaxject.what_tv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import promaxject.what_tv.domain.Balance;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.BalanceRepository;
import promaxject.what_tv.repository.PostRepository;
import promaxject.what_tv.repository.UserRepository;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void transferFromPost(Long postId, Long receiverId) {
        // Post와 수신자 정보 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        SiteUser receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("수신자를 찾을 수 없습니다."));

        // Post의 order_price 확인
        BigDecimal orderPrice = post.getOrder_price();
        if (orderPrice == null || orderPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("송금할 금액이 없습니다.");
        }



        // 수신자의 Balance 업데이트
        Balance receiverBalance = balanceRepository.findByUserId(receiver.getId());
        // 수신자의 Balance 확인 및 생성
        if (receiverBalance == null) {
            receiverBalance = new Balance();
            receiverBalance.setUser(receiver);
            receiverBalance.setAmount(BigDecimal.ZERO);
        }

        receiverBalance.deposit(orderPrice);
        balanceRepository.save(receiverBalance);

        // Post의 order_price를 0으로 설정
        post.setOrder_price(BigDecimal.ZERO);
        postRepository.save(post);
    }
}