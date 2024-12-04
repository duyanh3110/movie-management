package vn.duynguyen.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_role")
public class Role extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role")
    @Builder.Default
    private Set<RoleHasPermission> roles = new HashSet<>();

    @OneToMany(mappedBy = "role")
    @Builder.Default
    private Set<RoleHasPermission> permissions = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<Group> groups = new HashSet<>();
}
