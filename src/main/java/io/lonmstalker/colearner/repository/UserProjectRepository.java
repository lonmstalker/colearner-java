package io.lonmstalker.colearner.repository;

import io.lonmstalker.colearner.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, Integer> {
}
