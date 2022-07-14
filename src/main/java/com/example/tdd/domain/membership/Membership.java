package com.example.tdd.domain.membership;

import com.example.tdd.model.enum_type.MembershipType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  private MembershipType membershipType;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  @ColumnDefault("0")
  private Integer point;

  @CreationTimestamp
  @Column(nullable = false, length = 20, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(length = 20)
  private LocalDateTime updatedAt;

  @Builder
  public Membership(Long id, MembershipType membershipType, String userId, Integer point) {
    this.id = id;
    this.membershipType = membershipType;
    this.userId = userId;
    this.point = point;
  }
}
