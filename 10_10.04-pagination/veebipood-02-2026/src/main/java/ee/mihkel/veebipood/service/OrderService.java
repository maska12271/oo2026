package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.dto.OrderRowDto;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order saveOrder(List<OrderRowDto> orderRows, Long personId) {
        Order order = new Order();
        order.setCreated(new Date());
        Person person = new Person();
        person.setId(personId);
        order.setPerson(person);
        calculateOrderSumAndSaveOrderRows(order, orderRows);
        return orderRepository.save(order);
    }

    private void calculateOrderSumAndSaveOrderRows(Order order, List<OrderRowDto> orderRows) {
        double sum = 0;
        List<OrderRow> orderRowList = new ArrayList<>();
        for(OrderRowDto orderRow : orderRows){
            Product dbProduct = productRepository.findById(orderRow.productId()).orElseThrow();
            sum += orderRow.quantity() * dbProduct.getPrice();
            OrderRow orderRowEntity = new OrderRow();
            orderRowEntity.setProduct(dbProduct);
            if (orderRow.quantity() <= 0){
                throw new RuntimeException("Quantity is zero or negative!");
            }
            orderRowEntity.setQuantity(orderRow.quantity());
            orderRowList.add(orderRowEntity);
        }
        order.setTotal(sum);
        order.setOrderRows(orderRowList);
    }
}
