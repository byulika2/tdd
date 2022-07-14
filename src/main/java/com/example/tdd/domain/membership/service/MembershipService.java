package com.example.tdd.domain.membership.service;

import com.example.tdd.config.exception.MembershipException;
import com.example.tdd.domain.membership.Membership;
import com.example.tdd.domain.membership.controller.response.MembershipResponse;
import com.example.tdd.domain.membership.repository.MembershipRepository;
import com.example.tdd.model.enum_type.MembershipErrorResult;
import com.example.tdd.model.enum_type.MembershipType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

  private final MembershipRepository membershipRepository;

  public MembershipResponse addMembership(final String userId, final MembershipType membershipType,
      final Integer point) {
    final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
    if (result != null) {
      throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

    final Membership membership = Membership.builder()
        .userId(userId)
        .point(point)
        .membershipType(membershipType)
        .build();

    final Membership savedMembership = membershipRepository.save(membership);

    return MembershipResponse.builder()
        .id(savedMembership.getId())
        .membershipType(savedMembership.getMembershipType())
        .build();
  }
}
