package com.example.authservice.auth.repository;

import com.example.authservice.auth.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmailId(String email);
}
