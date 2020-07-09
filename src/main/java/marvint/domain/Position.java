package marvint.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long code;

    private String title;
}
