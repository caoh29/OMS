package caoh29.OMS.inventory_service.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private String inventoryId;
    private String sku;
    private Integer quantityOnHand;
    private Integer quantityReserved;
    private Integer quantityAvailable;
    private Integer reorderPoint;
    private Integer maxStockLevel;
}
