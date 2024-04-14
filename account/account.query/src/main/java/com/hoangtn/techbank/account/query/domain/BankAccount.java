package com.hoangtn.techbank.account.query.domain;

import com.hoangtn.techbank.account.common.dto.AccountType;
import com.hoangtn.techbank.cqrs.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BankAccount extends BaseEntity {

    @Id
    private String id;

    private String accountHolder;
    private Date creationDate;
    private AccountType accountType;
    private double balance;

}
