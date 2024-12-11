package vn.duynguyen.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuditoriumRequestDTO implements Serializable {
    @NotBlank(message = "name must be not blank")
    private String name;

    @Positive(message = "seatNumber mut be larger than 0")
    private int seatNumber;

    public AuditoriumRequestDTO(String name, int seatNumber) {
        this.name = name;
        this.seatNumber = seatNumber;
    }
}
