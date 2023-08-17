package library.librarymanagement.excercise;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public  void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public static Order createOrder(Item item, int count) {
        var order = new Order();
        order.orderItems.add(new OrderItem(order, item, count));
        return order;
    }
}
