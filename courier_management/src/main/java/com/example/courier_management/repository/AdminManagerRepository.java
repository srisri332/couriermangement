package com.example.courier_management.repository;

import com.example.courier_management.entity.AdminManager;
import com.example.courier_management.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminManagerRepository extends JpaRepository<AdminManager,String> {
}
