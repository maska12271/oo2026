package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.Supplier1Product;
import ee.mihkel.veebipood.dto.Supplier2Product;
import ee.mihkel.veebipood.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-02-2026.onrender.com"})
@RestController
@RequiredArgsConstructor
public class SupplierController {

    // SmartID --> isikukood+pin koodi -> true/false
    // MobiilId
    // Statistikaamet
    // Eesti.ee --> kas on tema laps?
    // Tarnija
    private final SupplierService supplierService;

    @GetMapping("supplier1")
    public List<Supplier1Product> getSupplier1Products(){
        return supplierService.getSupplier1Products();
    }

    @GetMapping("supplier2")
    public List<Supplier2Product> getSupplier2Products(){
        return supplierService.getSupplier2Products();
    }
}
