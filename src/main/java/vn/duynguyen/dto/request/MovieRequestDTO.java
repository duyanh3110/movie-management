package vn.duynguyen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MovieRequestDTO implements Serializable {
    @NotBlank(message = "title must be not blank")
    private String title;

    @NotBlank(message = "director must be not blank")
    private String director;

    @NotBlank(message = "casts must be not blank")
    private String casts;

    private String description;

    private int duration;

    private String kind;

    public MovieRequestDTO(String title, String director, String casts) {
        this.title = title;
        this.director = director;
        this.casts = casts;
    }
}
