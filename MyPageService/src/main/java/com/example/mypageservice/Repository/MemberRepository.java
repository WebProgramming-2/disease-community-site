package com.example.mypageservice.Repository;

import com.example.mypageservice.Entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberProfile, Long> {

}
