package promaxject.what_tv.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import promaxject.what_tv.service.BalanceService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferBalance(@RequestBody TransferRequest request) {
        try {
            balanceService.transferFromPost(request.getPostId(), request.getReceiverId());
            return ResponseEntity.ok("송금이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class TransferRequest {
        private Long postId;      // Post ID
        private Long receiverId;  // 답글 작성자 ID
    }
}