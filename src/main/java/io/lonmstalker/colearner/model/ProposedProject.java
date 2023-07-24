package io.lonmstalker.colearner.model;

import io.lonmstalker.colearner.enums.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("proposal_project")
public class ProposedProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private ProjectStatus status;


    @Column(name = "p_type")
    @Enumerated(EnumType.ORDINAL)
    private ProjectStatus type;

    private int authorId;
    private String title;
    private String description;
    private OffsetDateTime createdDate;
}
