package com.luizdev.order_management_system.services;


import com.luizdev.order_management_system.DTO.request.OrderItemRequestDTO;
import com.luizdev.order_management_system.DTO.request.OrderRequestDTO;
import com.luizdev.order_management_system.DTO.response.OrderResponseDTO;
import com.luizdev.order_management_system.domain.Order;
import com.luizdev.order_management_system.domain.OrderItem;
import com.luizdev.order_management_system.domain.Product;
import com.luizdev.order_management_system.enums.OrderStatus;
import com.luizdev.order_management_system.exceptions.*;
import com.luizdev.order_management_system.repositories.OrderItemRepository;
import com.luizdev.order_management_system.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;


    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        ProductService productService) {


        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;

    }

    @Transactional
    public void pay(Long orderId) {
        Order order = searchOrder(orderId);

        if (order.getOrderStatus() != OrderStatus.CREATED) {
            throw new AlreadyPaidOrderException("This order is already paid.");
        }

        for (OrderItem item : order.getOrderItem()) {
            Product product = item.getProduct();

            if (product.getStock() < item.getQuantity()) {
                throw new WithoutStockException("No stock available for this order.");
            }
            product.setStock(product.getStock() - item.getQuantity());
        }

        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = new Order();

        userService.associateOrdersToUsers(request.userId(), order);
        order.setOrderStatus(OrderStatus.CREATED);

        for (OrderItemRequestDTO itemRequestDTO : request.items()) {
            order.getOrderItem().add(createOrderItem(itemRequestDTO, order));
        }

        order.setUser(userService.
                getRepository().
                findById(request.userId()).
                orElseThrow(() -> new NotFoundUserException("User was not found.")));
        order.setDescription(request.description());


        orderRepository.save(order);
        return new OrderResponseDTO(order.getId(), order.getOrderItem(), order.getId(), order.getOrderStatus(), order.getDescription());
    }


    @Transactional
    public void cancelOrder(Long id) {
        var order = searchOrder(id);

        if (order.getOrderStatus() == OrderStatus.CREATED) {
            order.setOrderStatus(OrderStatus.CANCELED);
            return;
        }

        throw new AlreadyPaidOrderException("Your order is already paid. Can't cancel.");
    }

    @Transactional
    public void sendOrder(Long id) {
        var order = searchOrder(id);

        if (order.getOrderStatus() == OrderStatus.PAID) {
            order.setOrderStatus(OrderStatus.SHIPPED);
        } else if (order.getOrderStatus() == OrderStatus.CREATED) {
            throw new UnpaidOrderException("This order is not paid. Impossible to send.");
        } else if (order.getOrderStatus() == OrderStatus.SHIPPED) {
            throw new AlreadyShippedOrderException("This order was already shipped.");
        } else if (order.getOrderStatus() == OrderStatus.CANCELED) {
            throw new CanceledOrderException("This order is canceled.");
        }
    }

    private Order searchOrder(Long id) {
        return orderRepository.
                findById(id).
                orElseThrow(() -> new NotFoundOrderException("Order was not found."));

    }

    private OrderItem createOrderItem(OrderItemRequestDTO orderItemRequestDTO, Order order) {
        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity(orderItemRequestDTO.quantity());
        orderItem.setProduct(productService.findForOrder(orderItemRequestDTO.productId()));
        orderItem.setOrder(order);
        return orderItem;

    }


}
