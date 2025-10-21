package com.product.product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.product.Model.Product;
@Repository
public interface repository extends JpaRepository<Product,Long> {
    
}
