package pl.sda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String pesel;

    @OneToOne (cascade = CascadeType.ALL) // po dodaniu person stworzy się pole w account
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", account=" + account +
                '}';
    }
}
