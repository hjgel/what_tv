package promaxject.what_tv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import promaxject.what_tv.domain.Balance;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.BalanceRepository;
import promaxject.what_tv.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BalanceInitializer {

    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;

    @PostConstruct
    public void initializeBalances() {
        List<SiteUser> users = userRepository.findAll();
        for (SiteUser user : users) {
            if (balanceRepository.findByUserId(user.getId()) == null) {
                Balance balance = new Balance();
                balance.setUser(user);
                balance.setAmount(BigDecimal.ZERO);
                balanceRepository.save(balance);
            }
        }
    }
}
