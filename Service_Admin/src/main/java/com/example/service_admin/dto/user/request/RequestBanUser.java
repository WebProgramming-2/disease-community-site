package com.example.service_admin.dto.user.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestBanUser {
    private long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public RequestBanUser(){}
    public RequestBanUser(long id, LocalDateTime startDate, LocalDateTime endDate){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
