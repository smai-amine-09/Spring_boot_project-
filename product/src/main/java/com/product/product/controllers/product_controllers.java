package com.product.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.product.Model.Product;
import com.product.product.Model.voucher;
import com.product.product.Repository.repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class product_controllers {
    @Autowired
    private repository repo;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${VoucherServise.url}")
    private String voucherServiceUrl;

    @PostMapping("/products")
    public ResponseEntity postMethodName(@RequestBody Product entity) {
        try {
            voucher voucher = restTemplate.getForObject(voucherServiceUrl + entity.getCode(),
                    com.product.product.Model.voucher.class);
            if (voucher != null) {
                double price_after_discount = entity.getPrice() - voucher.getDiscount();
                entity.setPrice(price_after_discount);
                repo.save(entity);
                return new ResponseEntity<>("Successes", HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
