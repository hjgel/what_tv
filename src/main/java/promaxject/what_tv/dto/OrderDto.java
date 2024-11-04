package promaxject.what_tv.dto;

import lombok.Getter;
import promaxject.what_tv.domain.Order;

@Getter
public class OrderDto {
    Long productId;
    String productName;
    int price;
    int quantity;
    String impUid;
    String merchantUid;

    public Order toEntity() {
        return Order.builder()
                .product_id(productId)
                .product_name(productName)
                .price(price)
                .quantity(quantity)
                .imp_uid(impUid)
                .merchantUid(merchantUid)
                .build();
    }
}
