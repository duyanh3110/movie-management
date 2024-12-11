package vn.duynguyen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ServiceTypeDTO implements Serializable {
    @NotBlank(message = "name must be not blank")
    private String name;

    public ServiceTypeDTO(String name) {
        this.name = name;
    }
}


