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
@Table(name = "tbl_group")
public class Group extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "group")
    @Builder.Default
    private Set<GroupHasUser> groups = new HashSet<>();
}
