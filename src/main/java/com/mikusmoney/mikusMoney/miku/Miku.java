package com.mikusmoney.mikusMoney.miku;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Mikus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Miku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private Date birth_date;

    @Column(nullable = false)
    private Integer public_code;

    @Column(nullable = false)
    private Date created_at;
}
