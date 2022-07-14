package com.example.tdd.domain.membership.controller.response;

import com.example.tdd.model.enum_type.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipResponse {

  private final Long id;
  private final MembershipType membershipType;

}
