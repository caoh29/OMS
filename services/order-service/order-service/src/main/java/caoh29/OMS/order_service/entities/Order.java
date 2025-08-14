package caoh29.OMS.order_service.entities;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    private String orderId;
    private String productId;
    private Integer quantity;
    private String customerName;
    private String customerEmail;
    private String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"
}
