package caoh29.OMS.order_service.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private String productId;
    private String sku;
    private String name;
    private String description;
    private Double price;
}
