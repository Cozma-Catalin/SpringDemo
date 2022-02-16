package learning.spring.demo.persistence.entity;


import lombok.*;


import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "surname",nullable = false)
    private String surname;
    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "logged_in")
    private Boolean loggedIn;


}
