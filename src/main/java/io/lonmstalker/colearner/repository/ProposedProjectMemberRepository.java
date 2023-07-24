package io.lonmstalker.colearner.repository;

import io.lonmstalker.colearner.model.ProposedProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposedProjectMemberRepository extends JpaRepository<ProposedProjectMember, Integer> {
}
