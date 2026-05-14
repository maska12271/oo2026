package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.OrderRowDto;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-02-2026.onrender.com"})
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @PostMapping("orders")
    public Order addOrder(@RequestParam Long personId, @RequestBody List<OrderRowDto> orderRows){
        return orderService.saveOrder(orderRows, personId);
    }
}
