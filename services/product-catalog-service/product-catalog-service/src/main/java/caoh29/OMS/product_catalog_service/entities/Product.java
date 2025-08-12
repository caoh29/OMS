package caoh29.OMS.product_catalog_service.entities;

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
