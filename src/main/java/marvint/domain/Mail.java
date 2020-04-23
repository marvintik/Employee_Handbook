package marvint.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mail {
    @Id
    private Long id;

    @ManyToOne
    private Employee employee;

    private String mail;
}
