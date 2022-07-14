package com.example.tdd.domain.membership.repository;

import com.example.tdd.domain.membership.Membership;
import com.example.tdd.model.enum_type.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

  Membership findByUserIdAndMembershipType(String userId, MembershipType membershipType);
}
