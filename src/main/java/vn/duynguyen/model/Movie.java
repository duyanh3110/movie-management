package vn.duynguyen.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_movie")
public class Movie extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "casts")
    private String casts;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "kind")
    private String kind;
}
