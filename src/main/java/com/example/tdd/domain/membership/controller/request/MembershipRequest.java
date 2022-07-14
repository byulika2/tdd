package com.example.tdd.domain.membership.controller.request;

import com.example.tdd.model.enum_type.MembershipType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequest {

  @NotNull
  @Min(0)
  private final Integer point;

  @NotNull
  private final MembershipType membershipType;

}
