package com.example.mobilemanagement.controller;

import com.example.mobilemanagement.model.MobilePhone;
import com.example.mobilemanagement.repository.MobilePhoneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/phones")
public class MobilePhoneController {

    @Autowired
    private MobilePhoneRepository repository;

    //Εισαγωγή νέου κινητού
    @PostMapping
    public ResponseEntity<?> createPhone(@Valid @RequestBody MobilePhone phone) {
        if (repository.existsById(phone.getSerialNumber())) {
            return ResponseEntity.badRequest().body("Το κινητό με αυτόν τον σειριακό αριθμό υπάρχει ήδη.");
        }
        if (repository.existsByImei(phone.getImei())) {
            return ResponseEntity.badRequest().body("Το κινητό με αυτό το IMEI υπάρχει ήδη.");
        }
        return ResponseEntity.ok(repository.save(phone));
    }

    //Λήψη κινητού με βάση τον σειριακό αριθμό
    @GetMapping("/{serial}")
    public ResponseEntity<?> getPhone(@PathVariable String serial) {
        return repository.findById(serial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Λήψη όλων των κινητών
    @GetMapping
    public List<MobilePhone> getAllPhones() {
        return repository.findAll();
    }

    //Αναζήτηση με βάση πεδία (brand, model, tech)
    @GetMapping("/search")
    public List<MobilePhone> searchPhones(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String tech
    ) {
        List<MobilePhone> results = repository.findAll();

        if (brand != null) {
            results.retainAll(repository.findByBrandContainingIgnoreCase(brand));
        }
        if (model != null) {
            results.retainAll(repository.findByModelContainingIgnoreCase(model));
        }
        if (tech != null) {
            results.retainAll(repository.findByNetworkTechnologyContainingIgnoreCase(tech));
        }

        results.sort(Comparator
                .comparing(MobilePhone::getBrand)
                .thenComparing(MobilePhone::getModel)
                .thenComparing(MobilePhone::getPrice));

        return results;
    }

    //Διαγραφή κινητού
    @DeleteMapping("/{serial}")
    public ResponseEntity<?> deletePhone(@PathVariable String serial) {
        if (!repository.existsById(serial)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(serial);
        return ResponseEntity.ok("Το κινητό διαγράφηκε επιτυχώς.");
    }

    //Ενημέρωση κινητού
    @PutMapping("/{serial}")
    public ResponseEntity<?> updatePhone(@PathVariable String serial, @Valid @RequestBody MobilePhone update) {
        Optional<MobilePhone> existing = repository.findById(serial);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MobilePhone phone = existing.get();

        //Μόνο τα πεδία που επιτρέπεται να αλλάξουν
        phone.setNetworkTechnology(update.getNetworkTechnology());
        phone.setCameraCount(update.getCameraCount());
        phone.setCpuCores(update.getCpuCores());
        phone.setWeightGrams(update.getWeightGrams());
        phone.setBatteryCapacity(update.getBatteryCapacity());
        phone.setPrice(update.getPrice());

        return ResponseEntity.ok(repository.save(phone));
    }
}
