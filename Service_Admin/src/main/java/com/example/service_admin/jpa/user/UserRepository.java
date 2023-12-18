package com.example.service_admin.jpa.user;


import com.example.service_admin.jpa.inquiredcategory.InquiredCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
