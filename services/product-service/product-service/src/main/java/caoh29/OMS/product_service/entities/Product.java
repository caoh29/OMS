package caoh29.OMS.product_service.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
}
