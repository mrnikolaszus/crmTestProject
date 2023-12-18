package com.javarush.jira.profile.internal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static com.javarush.jira.login.internal.web.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private ObjectMapper objectMapper;


    private static final String REST_URL_API_PROFILE = "/api/profile";
    public static ProfileTo USER_PROFILE_TO = new ProfileTo(null,
            Set.of("assigned", "overdue", "deadline"),
            Set.of(new ContactTo("skype", "userSkype"),
                    new ContactTo("mobile", "+01234567890"),
                    new ContactTo("website", "user.com")));

    String WRONG_JSON = "{\"data\":\"abcdefghij\"\"data\":\"abcdefghij\"\"data\":\"abcdefghij\"\"data\":\"abcdefghij\"\"data\":\"abcdefghij\"\"data\":\"abcdefghij\"}";



    @Test
    @WithUserDetails(value = USER_MAIL)
    void userGetProfileTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_API_PROFILE))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminGetProfileTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_API_PROFILE))
                .andExpect(status().isOk());
    }

    @Test
    void guestGetProfileTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_API_PROFILE))
                .andExpect(status().isUnauthorized());
    }


        @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminUpdateProfileTest() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_API_PROFILE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_PROFILE_TO)))
                .andExpect(status().isNoContent());

    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void userUpdateProfileTest() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_API_PROFILE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_PROFILE_TO)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void userUpdateUnsupportedTypeTest() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_API_PROFILE)
                .contentType(MediaType.APPLICATION_PDF)
                .content(objectMapper.writeValueAsString(USER_PROFILE_TO)))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void patchProfileNotAllowedTest() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL_API_PROFILE ))
                .andExpect(status().isMethodNotAllowed());
    }


    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteProfileNotAllowedTest() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_API_PROFILE))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void userUpdateWithWrongJsonTest() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_API_PROFILE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(WRONG_JSON))
                .andExpect(status().isInternalServerError());
    }


}