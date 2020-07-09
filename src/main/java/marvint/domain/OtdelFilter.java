package marvint.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
public class OtdelFilter {
    private Long id;
    private String title;
    private String address;
    private String department;
}
