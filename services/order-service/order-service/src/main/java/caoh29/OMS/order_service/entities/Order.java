package caoh29.OMS.order_service.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    public Order(String productId, int quantity, String customerName, String customerEmail, String status) {
        this.productId = productId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.status = status;
    }

    private String productId;
    private int quantity;
    private String customerName;
    private String customerEmail;
    private String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
