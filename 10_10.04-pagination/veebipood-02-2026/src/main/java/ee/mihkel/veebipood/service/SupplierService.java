package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.dto.Supplier1Product;
import ee.mihkel.veebipood.dto.Supplier2Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierService {

    private RestTemplate restTemplate = new RestTemplate();

    public List<Supplier1Product> getSupplier1Products() {
        String url = "https://fakestoreapi.com/products";
        Supplier1Product[] response = restTemplate.exchange(url, HttpMethod.GET, null, Supplier1Product[].class).getBody();
        return Arrays.stream(response)
                .filter(e -> e.getRating().getRate() > 3.0)
                .toList();
    }

    public List<Supplier2Product> getSupplier2Products() {
        String url = "https://api.escuelajs.co/api/v1/products";
        Supplier2Product[] response = restTemplate.exchange(url, HttpMethod.GET, null, Supplier2Product[].class).getBody();
        return Arrays.stream(response)
                .filter(e -> e.getCreationAt().equals(e.getCategory().getCreationAt()))
                .toList();
    }
}
