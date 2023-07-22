package io.lonmstalker.colearner.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
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
    private int id;

    @Lob
    private byte[] icon;

    @Type(StringArrayType.class)
    private String[] skills;

    private String telegramId;
    private String username;
    private String currentPosition;
    private int age;
    private int experienceYears;
    private String biography;
    private OffsetDateTime createdDate;
}
