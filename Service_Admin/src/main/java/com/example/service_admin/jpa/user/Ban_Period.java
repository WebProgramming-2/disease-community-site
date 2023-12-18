package com.example.service_admin.jpa.user;

import com.example.service_admin.dto.user.request.RequestBanUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Ban_Period {

    @Id
    private Long id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

    public Ban_Period(){}
    public Ban_Period(RequestBanUser request){
        this.id = request.getId();
        this.start_date = request.getStartDate();
        this.end_date = request.getEndDate();
    }
}
