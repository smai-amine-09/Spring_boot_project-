package com.voucher.voucher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voucher.voucher.Model.Voucher;

@Repository
public interface Voucher_repo extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
}
