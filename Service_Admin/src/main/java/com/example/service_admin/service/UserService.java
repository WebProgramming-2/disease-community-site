package com.example.service_admin.service;

import com.example.service_admin.dto.user.request.RequestBanUser;
import com.example.service_admin.dto.user.request.RequestGivingAuthority;
import com.example.service_admin.dto.user.response.ReportedUserResponse;
import com.example.service_admin.jpa.comment.ReportedComment;
import com.example.service_admin.jpa.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReportedUserRepository reportedUserRepository;
    private final BanPeriodRepository banPeriodRepository;

    public boolean confirm(long userID) {
        return getUser(userID).isAdmin();
    }

    public void give(RequestGivingAuthority request){
        User user = getUser(request.getId());
        user.setAdmin(request.isAuthority());
        this.userRepository.save(user);
    }

    public User getUser(long userID){
        Optional<User> temp = this.userRepository.findById(userID);
        if(temp.isPresent()){
            return temp.get();
        }else
            throw new IllegalArgumentException("Not existing user.");
    }

    public List<ReportedUserResponse> getReportedUsers() {
        return this.reportedUserRepository.findAll().stream()
                .map(ReportedUserResponse::new)
                .collect(Collectors.toList());
    }

    public void removeAtTable(long id) {
        ReportedUser user = getRepoUser(id);
        this.reportedUserRepository.delete(user);
    }

    public ReportedUser getRepoUser(long id){
        Optional<ReportedUser> optional= this.reportedUserRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else
            throw new IllegalArgumentException("");
    }

    public void warn(long id) {
        User user = getUser(id);
        user.setWarn_count(user.getWarn_count()+1);
        this.userRepository.save(user);
    }

    public void ban(RequestBanUser request) {
        this.banPeriodRepository.save(new Ban_Period(request));
    }

    public void banRelease(long id) {
        this.banPeriodRepository.deleteById(id);
    }
}
