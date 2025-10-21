package com.voucher.voucher.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Voucher")
@Data
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "discount")
    private float discount;
    @Column(name = "expire_date", nullable = false)
    private String expire_date;

}
