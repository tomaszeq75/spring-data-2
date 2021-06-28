package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String accountNumber;

    private BigDecimal state;

    @OneToOne(mappedBy = "account")
    private PersonEntity person;

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountNumber='" + accountNumber + '\'' +
                ", state=" + state +
                '}';
    }

    public void addMoney(BigDecimal money) {
        state = state.add(money);
    }

    public void substractMoney(BigDecimal money) {
        state = state.subtract(money);
    }
}
