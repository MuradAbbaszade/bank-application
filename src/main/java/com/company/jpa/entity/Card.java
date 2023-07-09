package com.company.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "balance")
    private Long balance;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity userEntity;

    @OneToMany(mappedBy="card")
    private Set<Transaction> transactions;

    @PrePersist
    public void setCreateTime(){
        createdAt=LocalDateTime.now();
        expirationDate=createdAt.plusYears(5);
        byte[] array = new byte[16];
        new Random().nextBytes(array);
        cardNumber = new String(array, Charset.forName("UTF-8"));
    }

}
