package vn.duynguyen.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class ServiceTypeResponse implements Serializable {

    private String id;

    private String name;
}
