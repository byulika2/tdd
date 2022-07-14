package com.example.tdd.domain.membership.repository;

import static com.example.tdd.model.enum_type.MembershipType.NAVER;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.tdd.domain.membership.Membership;
import com.example.tdd.model.enum_type.MembershipType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MembershipRepositoryTest {

  @Autowired
  private MembershipRepository membershipRepository;

  @Test
  @DisplayName("멤버십 Repository != null")
  public void MembershipRepositoryNotNull() {
    assertThat(membershipRepository).isNotNull();
  }

  @Test
  public void 멤버십등록() {
    // given
    final Membership membership = Membership.builder()
        .userId("userId")
        .membershipType(NAVER)
        .point(10000)
        .build();

    // when
    final Membership result = membershipRepository.save(membership);

    // then
    assertThat(result.getId()).isNotNull();
    assertThat(result.getUserId()).isEqualTo("userId");
    assertThat(result.getMembershipType()).isEqualTo(NAVER);
    assertThat(result.getPoint()).isEqualTo(10000);
  }

  @Test
  public void 멤버십이존재하는지테스트() {
    // given
    final Membership membership = Membership.builder()
        .userId("userId")
        .membershipType(MembershipType.NAVER)
        .point(10000)
        .build();

    // when
    membershipRepository.save(membership);
    final Membership findResult = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

    // then
    assertThat(findResult).isNotNull();
    assertThat(findResult.getId()).isNotNull();
    assertThat(findResult.getUserId()).isEqualTo("userId");
    assertThat(findResult.getMembershipType()).isEqualTo(MembershipType.NAVER);
    assertThat(findResult.getPoint()).isEqualTo(10000);
  }
}
