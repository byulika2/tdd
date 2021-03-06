package com.example.tdd.domain.membership.controller;

import static com.example.tdd.model.constants.MembershipConstants.USER_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tdd.config.exception.GlobalExceptionHandler;
import com.example.tdd.config.exception.MembershipException;
import com.example.tdd.domain.membership.controller.request.MembershipRequest;
import com.example.tdd.domain.membership.controller.response.MembershipResponse;
import com.example.tdd.domain.membership.service.MembershipService;
import com.example.tdd.model.enum_type.MembershipErrorResult;
import com.example.tdd.model.enum_type.MembershipType;
import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

  @InjectMocks
  private MembershipController target;

  @Mock
  private MembershipService membershipService;

  private MockMvc mockMvc;
  private Gson gson;

  @BeforeEach
  public void init() {
    gson = new Gson();
    mockMvc = MockMvcBuilders.standaloneSetup(target)
        .setControllerAdvice(new GlobalExceptionHandler())
        .build();
  }

//  @Test
//  public void ?????????????????????_????????????????????????????????????() throws Exception {
//    // given
//    final String url = "/api/v1/memberships";
//
//    // when
//    final ResultActions resultActions = mockMvc.perform(
//        MockMvcRequestBuilders.post(url)
//            .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
//            .contentType(MediaType.APPLICATION_JSON)
//    );
//
//    // then
//    resultActions.andExpect(status().isBadRequest());
//  }

//  @Test
//  public void ?????????????????????_????????????null() throws Exception {
//    // given
//    final String url = "/api/v1/memberships";
//
//    // when
//    final ResultActions resultActions = mockMvc.perform(
//        MockMvcRequestBuilders.post(url)
//            .header(USER_ID_HEADER, "12345")
//            .content(gson.toJson(membershipRequest(null, MembershipType.NAVER)))
//            .contentType(MediaType.APPLICATION_JSON)
//    );
//
//    // then
//    resultActions.andExpect(status().isBadRequest());
//  }

//  @Test
//  public void ?????????????????????_??????????????????() throws Exception {
//    // given
//    final String url = "/api/v1/memberships";
//
//    // when
//    final ResultActions resultActions = mockMvc.perform(
//        MockMvcRequestBuilders.post(url)
//            .header(USER_ID_HEADER, "12345")
//            .content(gson.toJson(membershipRequest(-1, MembershipType.NAVER)))
//            .contentType(MediaType.APPLICATION_JSON)
//    );
//
//    // then
//    resultActions.andExpect(status().isBadRequest());
//  }

//  @Test
//  public void ?????????????????????_??????????????????Null() throws Exception {
//    // given
//    final String url = "/api/v1/memberships";
//
//    // when
//    final ResultActions resultActions = mockMvc.perform(
//        MockMvcRequestBuilders.post(url)
//            .header(USER_ID_HEADER, "12345")
//            .content(gson.toJson(membershipRequest(10000, null)))
//            .contentType(MediaType.APPLICATION_JSON)
//    );
//
//    // then
//    resultActions.andExpect(status().isBadRequest());
//  }

  @ParameterizedTest
  @MethodSource("invalidMembershipAddParameter")
  public void ?????????????????????_?????????????????????(final Integer point, final MembershipType membershipType) throws Exception {
    // given
    final String url = "/api/v1/memberships";

    // when
    final ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders.post(url)
            .header(USER_ID_HEADER, "12345")
            .content(gson.toJson(membershipRequest(point, membershipType)))
            .contentType(MediaType.APPLICATION_JSON)
    );

    // then
    resultActions.andExpect(status().isBadRequest());
  }

  private static Stream<Arguments> invalidMembershipAddParameter() {
    return Stream.of(
        Arguments.of(null, MembershipType.NAVER),
        Arguments.of(-1, MembershipType.NAVER),
        Arguments.of(10000, null)
    );
  }

  @Test
  public void ?????????????????????_MemberService????????????Throw() throws Exception {
    // given
    final String url = "/api/v1/memberships";
    doThrow(new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER))
        .when(membershipService)
        .addMembership("12345", MembershipType.NAVER, 10000);

    // when
    final ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders.post(url)
            .header(USER_ID_HEADER, "12345")
            .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
            .contentType(MediaType.APPLICATION_JSON)
    );

    // then
    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void ?????????????????????() throws Exception {
    // given
    final String url = "/api/v1/memberships";
    final MembershipResponse membershipResponse = MembershipResponse.builder()
        .id(-1L)
        .membershipType(MembershipType.NAVER).build();

    doReturn(membershipResponse).when(membershipService).addMembership("12345", MembershipType.NAVER, 10000);

    // when
    final ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders.post(url)
            .header(USER_ID_HEADER, "12345")
            .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
            .contentType(MediaType.APPLICATION_JSON)
    );

    // then
    resultActions.andExpect(status().isCreated());

    final MembershipResponse response = gson.fromJson(resultActions.andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8), MembershipResponse.class);

    assertThat(response.getMembershipType()).isEqualTo(MembershipType.NAVER);
    assertThat(response.getId()).isNotNull();
  }


  private MembershipRequest membershipRequest(final Integer point, final MembershipType membershipType) {
    return MembershipRequest.builder()
        .point(point)
        .membershipType(membershipType)
        .build();
  }
}
