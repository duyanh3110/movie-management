package vn.duynguyen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_service_type")
public class ServiceType extends AbstractEntity {

    @Column(name = "name")
    private String name;
}
