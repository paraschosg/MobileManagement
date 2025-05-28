package com.example.mobilemanagement.model;

import com.example.mobilemanagement.validation.ValidNetworkTechnology;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "mobile_phones")
public class MobilePhone {

    @Id
    @Column(length = 11, unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{11}$", message = "Ο σειριακός αριθμός πρέπει να έχει ακριβώς 11 αλφαριθμητικούς χαρακτήρες.")
    private String serialNumber;

    @Column(unique = true, nullable = false, length = 15)
    @Pattern(regexp = "^[0-9]{15}$", message = "Ο αριθμός IMEI πρέπει να έχει ακριβώς 15 ψηφία.")
    private String imei;

    @NotBlank(message = "Το μοντέλο είναι υποχρεωτικό.")
    @Size(min = 2, message = "Το μοντέλο πρέπει να έχει τουλάχιστον 2 χαρακτήρες.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Το μοντέλο πρέπει να είναι αλφαριθμητικό.")
    private String model;

    @NotBlank(message = "Η μάρκα είναι υποχρεωτική.")
    @Size(min = 2, message = "Η μάρκα πρέπει να έχει τουλάχιστον 2 γράμματα.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Η μάρκα πρέπει να περιέχει μόνο γράμματα.")
    private String brand;

    @ValidNetworkTechnology
    private String networkTechnology;

    @Min(value = 1)
    @Max(value = 3)
    private int cameraCount;

    @Min(value = 1)
    private int cpuCores;

    @Positive
    private int weightGrams;

    @Positive
    private int batteryCapacity;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getImei() { return imei; }
    public void setImei(String imei) { this.imei = imei; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getNetworkTechnology() { return networkTechnology; }
    public void setNetworkTechnology(String networkTechnology) { this.networkTechnology = networkTechnology; }

    public int getCameraCount() { return cameraCount; }
    public void setCameraCount(int cameraCount) { this.cameraCount = cameraCount; }

    public int getCpuCores() { return cpuCores; }
    public void setCpuCores(int cpuCores) { this.cpuCores = cpuCores; }

    public int getWeightGrams() { return weightGrams; }
    public void setWeightGrams(int weightGrams) { this.weightGrams = weightGrams; }

    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
