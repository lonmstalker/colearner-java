package io.lonmstalker.colearner.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.lonmstalker.colearner.enums.PositionType;
import io.lonmstalker.colearner.enums.ProjectStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("NullAway")
@Table(name = "proposed_project_member")
@SuppressFBWarnings(justification = "Конфликт со spotbugs")
public class ProposedProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private ProjectStatusEnum status;

    @Enumerated(EnumType.ORDINAL)
    private PositionType positionType;

    @Type(StringArrayType.class)
    private String[] skills;

    private int userId;
    private int projectId;
    private int workWeekHours;
    private String title;
    private String description;
    private OffsetDateTime joinedDate;
}
