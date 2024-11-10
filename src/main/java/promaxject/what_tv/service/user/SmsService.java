package promaxject.what_tv.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import promaxject.what_tv.dto.SmsRequestDto;
import promaxject.what_tv.util.SmsCertificationUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
    private final SmsCertificationUtil smsCertificationUtil;
    private final Map<String, String> verificationCodes = new HashMap<>(); // 전화번호와 인증번호 저장

    // 의존성 주입
    public SmsService(@Autowired SmsCertificationUtil smsCertificationUtil) {
        this.smsCertificationUtil = smsCertificationUtil;
    }

    public void sendSms(SmsRequestDto smsRequestDto) {
        String phoneNumber = smsRequestDto.getP_number();
        String certificationCode = generateCertificationCode(); // 인증번호 생성
        verificationCodes.put(phoneNumber, certificationCode); // 전화번호와 인증번호 저장
        smsCertificationUtil.sendSMS(phoneNumber, certificationCode); // SMS 전송
    }

    public boolean verifyCode(String phoneNumber, String code) {
        String savedCode = verificationCodes.get(phoneNumber); // 저장된 인증번호 가져오기
        return savedCode != null && savedCode.equals(code); // 인증번호 일치 여부 확인
    }

    private String generateCertificationCode() {
        return Integer.toString((int) (Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증번호 생성
    }
}