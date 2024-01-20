package entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userUuid;

    private String birthday;
    private String gender;
    private String phone;

    @ElementCollection
    private List<String> collections;
}
