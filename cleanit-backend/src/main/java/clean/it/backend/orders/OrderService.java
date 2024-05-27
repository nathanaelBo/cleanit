package clean.it.backend.orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<OrderDto> getOrderById(long id);
    List<OrderDto> getAllOrders();
    OrderDto createOrder(CreateOrderDto createOrderDto);
}
