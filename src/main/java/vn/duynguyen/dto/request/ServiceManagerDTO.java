package vn.duynguyen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ServiceManagerDTO implements Serializable {
    @NotBlank(message = "name must be not blank")
    private String name;

    private float price;

    public ServiceManagerDTO(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
