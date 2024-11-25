package promaxject.what_tv.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser user;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO; // 기본 잔액 0

    // 잔액을 증가시키는 메서드
    public void deposit(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    // 잔액을 감소시키는 메서드
    public void withdraw(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }
}