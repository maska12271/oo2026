package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Category;
import ee.mihkel.veebipood.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-02-2026.onrender.com"})
@RestController
@AllArgsConstructor
public class CategoryController {

    private CategoryRepository categoryRepository;

    @GetMapping("categories")
    public ResponseEntity<@NonNull List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @PostMapping("categories")
    public ResponseEntity<@NonNull Category> addCategory(@RequestBody Category category){
        if (category.getId() != null){
            throw new RuntimeException("Cannot add category with ID");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<@NonNull List<Category>> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }
}
