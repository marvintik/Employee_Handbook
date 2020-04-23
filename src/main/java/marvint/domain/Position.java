package marvint.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Position {
    @Id
//    @GeneratedValue
    @Column(unique = true)
    private Long code;

    private String title;
}
