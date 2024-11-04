package promaxject.what_tv.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "product_id")
    Long product_id;

    @Column(name = "product_name")
    String product_name;
    int price;
    int quantity;

    @Column(name = "imp_uid")
    String imp_uid;

    @Column(name = "merchant_uid")
    String merchantUid;


}
