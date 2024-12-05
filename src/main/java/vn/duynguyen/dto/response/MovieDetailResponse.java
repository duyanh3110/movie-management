package vn.duynguyen.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class MovieDetailResponse implements Serializable {
    private String id;

    private String title;

    private String director;

    private String casts;

    private String description;

    private int duration;

    private String kind;
}
