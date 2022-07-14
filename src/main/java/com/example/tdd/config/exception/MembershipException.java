package com.example.tdd.config.exception;

import com.example.tdd.model.enum_type.MembershipErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException {

  private final MembershipErrorResult errorResult;

}
