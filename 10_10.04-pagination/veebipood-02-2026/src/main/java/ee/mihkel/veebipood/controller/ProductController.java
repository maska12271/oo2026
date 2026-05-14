package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

//@CrossOrigin(origins = "*") // turvaviga ---> peame ise määratlema kes ligi pääseb
@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-02-2026.onrender.com"})
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;


    // default: localhost:8080
    // localhost:8080/products
    @GetMapping("products")
    public Page<Product> getProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (categoryId == null) {
            return productRepository.findByActiveTrue(pageable);
        } else {
            return productRepository.findByCategoryIdAndActiveTrue(categoryId, pageable);
        }
    }

    @GetMapping("products/admin")
    public List<Product> getAdminProducts(){
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product){
        if (product.getId() != null){
            throw new RuntimeException("Cannot add product with ID");
        }
        return productRepository.save(product);
    }

    // DELETE localhost:8080/products/4
    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    //DELETE localhost:8080/products?id=5
//    @DeleteMapping("products")
//    public List<Product> deleteProduct2(@RequestParam Long id){
//        productRepository.deleteById(id);
//        return productRepository.findAll();
//    }

    @PutMapping("products")
    public Product editProduct(@RequestBody Product product){
        if (product.getId() == null){
            throw new RuntimeException("Cannot edit product without ID");
        }
        return productRepository.save(product);
    }

    @PatchMapping("product-active")
    public List<Product> updateProductActive(@RequestParam Long id){
        Product product = productRepository.findById(id).orElseThrow();
        product.setActive(!product.isActive());
        productRepository.save(product);
        return productRepository.findAll();
    }

    // 2xx -> edukas
    // 4xx -> päringu tegija viga
    // 5xx -> back-endi viga
}
