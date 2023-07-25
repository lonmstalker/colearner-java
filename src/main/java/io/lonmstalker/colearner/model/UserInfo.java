package io.lonmstalker.colearner.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.lonmstalker.colearner.enums.RoleEnum;
import io.lonmstalker.colearner.enums.UserPositionEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
@EqualsAndHashCode(callSuper = true)
@SuppressFBWarnings(justification = "Конфликт со spotbugs")
public class UserInfo extends AbstractPersistableEntity<Long> {

    @Id
    private long id;

    @Type(StringArrayType.class)
    private String[] skills;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private RoleEnum role = RoleEnum.USER;

    @Builder.Default
    private String currentPosition = UserPositionEnum.START.name();

    private byte[] icon;
    private String username;
    private int age;
    private int experienceYears;
    private String biography;
    private OffsetDateTime createdDate;

    @Override
    public Long getId() {
        return id;
    }
}
