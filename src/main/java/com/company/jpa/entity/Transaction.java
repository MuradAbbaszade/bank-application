package com.company.jpa.entity;

import com.company.enums.TransactionState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "to_card_number")
    private String toCardNumber;

    @Column(name = "from_card_number")
    private String fromCardNumber;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "state")
    private TransactionState transactionState;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="card_id", nullable=false)
    private Card card;

    @PrePersist
    public void setCreateTime(){
        createdAt=LocalDateTime.now();
    }
}
