package com.example.courier_management.repository;

import com.example.courier_management.entity.OrderPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<OrderPackage,String> {
}
