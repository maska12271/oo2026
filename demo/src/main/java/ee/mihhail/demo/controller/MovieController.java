package ee.mihhail.demo.controller;

import ee.mihhail.demo.entity.Movie;
import ee.mihhail.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private MovieRepository productRepository;

    @GetMapping("products")
    public List<Movie> getProducts(){
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Movie> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Movie> addProduct(@RequestBody Movie product){
        productRepository.save(product);
        return productRepository.findAll();
    }
}
