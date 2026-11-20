package mundia.amine.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import mundia.amine.ebankservice.model.Customer;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date CreatedAt;
    private double balance;
    private String type;
    private Long customerId;
    @Transient
    private Customer customer;
}
