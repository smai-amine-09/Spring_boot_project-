package com.voucher.voucher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.voucher.voucher.Model.Voucher;
import com.voucher.voucher.Repository.Voucher_repo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/voucherapi")
public class voucher_controllers {
    @Autowired
    private Voucher_repo repo;

    @PostMapping("/vouchers")
    public ResponseEntity Creat_vouchers(@RequestBody Voucher entity) {
        repo.save(entity);
        return new ResponseEntity<>("Successes", HttpStatus.OK);
    }

    @GetMapping("/vouchers/{code}")
    public Voucher getVoucher(@PathVariable("code") String code) {
        return repo.findByCode(code);
    }

}
