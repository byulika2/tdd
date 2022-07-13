package com.example.tdd.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @Column(name = "email", length = 200, nullable = false)
  private String email;

  @Column(name = "passwd", length = 50, nullable = false)
  private String passwd;

  @ColumnDefault("0")
  @Column(name = "age", length = 11, nullable = false)
  private int age;

  @Builder
  public Member(String name, String email, String passwd, int age) {
    this.name = name;
    this.email = email;
    this.passwd = passwd;
    this.age = age;
  }
}
