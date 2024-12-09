package promaxject.what_tv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.domain.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByUserId(Long userId);
}