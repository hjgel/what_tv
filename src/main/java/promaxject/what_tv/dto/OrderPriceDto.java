package promaxject.what_tv.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderPriceDto {
    public String price;

    @Builder
    public OrderPriceDto(String price) {
        this.price = price;
    }
}
