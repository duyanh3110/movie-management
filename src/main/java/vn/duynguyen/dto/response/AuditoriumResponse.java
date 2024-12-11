package vn.duynguyen.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class AuditoriumResponse implements Serializable {
    private String id;

    private String name;

    private int seatNumber;
}
