package com.product.product.Model;

import lombok.Data;

@Data
public class voucher {
    private Long id;
    private String code;
    private float discount;
    private String expire_date;
}
