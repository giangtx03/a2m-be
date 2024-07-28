package com.a2m.project.repositories;

import com.a2m.project.domains.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
}
