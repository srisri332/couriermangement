package com.example.courier_management.repository;

import com.example.courier_management.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Short> {
}
