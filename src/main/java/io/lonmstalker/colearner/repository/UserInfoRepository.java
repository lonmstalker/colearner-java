package io.lonmstalker.colearner.repository;

import io.lonmstalker.colearner.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
