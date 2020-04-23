package marvint.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class Filter {

    private String firstName;
    private String lastName;
    private String secondName;
    private String phone;
    private String mail;

}
