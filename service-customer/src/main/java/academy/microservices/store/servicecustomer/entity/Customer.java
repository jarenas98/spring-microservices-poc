package academy.microservices.store.servicecustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The document number should be not empty")
    @Size(min = 8, max = 8, message = "The document number length should be 8")
    @Column(name = "number_id", unique = true, length = 8, nullable = false)
    private String numberId;

    @NotEmpty(message = "The name should be not empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "the last name should be not empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "the email should be not empty")
    @Email(message = "the format email is not valid")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="photo_url")
    private String photoUrl;


    @NotNull(message = "la región no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;

    private String state;

}
