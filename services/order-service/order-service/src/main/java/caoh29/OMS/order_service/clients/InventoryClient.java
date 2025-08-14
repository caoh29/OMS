package caoh29.OMS.order_service.clients;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
    @GetExchange("/api/inventory/in-stock")
    boolean isInStock(
//            @RequestHeader("Authorization") String authHeader,
            @RequestParam String sku,
            @RequestParam Integer quantity
    );
}
