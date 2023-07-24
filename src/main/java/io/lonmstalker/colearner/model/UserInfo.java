package io.lonmstalker.colearner.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import io.lonmstalker.colearner.enums.RoleEnum;
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
@Table("user_info")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] icon;

    @Type(StringArrayType.class)
    private String[] skills;

    @Enumerated(EnumType.ORDINAL)
    private RoleEnum role;

    private String username;
    private String currentPosition;
    private int age;
    private int experienceYears;
    private String biography;
    private OffsetDateTime createdDate;
}
