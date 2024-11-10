package promaxject.what_tv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import promaxject.what_tv.dto.SmsRequestDto;
import promaxject.what_tv.service.user.SmsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(@Autowired SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody @Valid SmsRequestDto smsRequestDto) {
        smsService.sendSms(smsRequestDto);
        return ResponseEntity.ok("문자를 전송했습니다.");
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestParam String phoneNumber, @RequestParam String code) {
        boolean isValid = smsService.verifyCode(phoneNumber, code);
        if (isValid) {
            return ResponseEntity.ok("인증 성공");
        } else {
            return ResponseEntity.status(400).body("인증번호가 일치하지 않습니다.");
        }
    }
}