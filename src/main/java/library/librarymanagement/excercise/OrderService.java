package library.librarymanagement.excercise;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public Long addOrderItem(Long orderId, Long itemId, int count) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();
        order.addOrderItem(new OrderItem(order, item, count));
        return order.getId();
    }

    @Transactional(readOnly = true)
    public List<String> findAllPrice(Long orderId) {
        // n +1 발생
        return orderRepository.findById(orderId).orElseThrow()
                .getOrderItems().stream()
                .map(o -> o.getItem().getName())
                .collect(Collectors.toList());
    }
}
