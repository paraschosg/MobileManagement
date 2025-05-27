package com.example.mobilemanagement.repository;

import com.example.mobilemanagement.model.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, String> {

    // Έλεγχος μοναδικού IMEI
    boolean existsByImei(String imei);

    // Αναζήτηση με βάση οποιοδήποτε πεδίο
    List<MobilePhone> findByBrandContainingIgnoreCase(String brand);
    List<MobilePhone> findByModelContainingIgnoreCase(String model);
    List<MobilePhone> findByNetworkTechnologyContainingIgnoreCase(String tech);
}
