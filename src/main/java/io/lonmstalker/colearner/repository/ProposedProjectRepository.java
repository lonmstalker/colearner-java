package io.lonmstalker.colearner.repository;

import io.lonmstalker.colearner.model.ProposedProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposedProjectRepository extends JpaRepository<ProposedProject, Integer> {
}
