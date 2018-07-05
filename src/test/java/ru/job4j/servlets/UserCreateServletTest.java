package ru.job4j.servlets;

import org.junit.Test;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Пользователь on 03.07.2018.
 */
public class UserCreateServletTest {
    private final ValidateService logic = ValidateService.getValidateService();
    @Test
    public void createUser() throws IOException {
        UserCreateServlet controller = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);
        when(request.getAttribute("name")).thenReturn("ilya");
        when(request.getAttribute("role")).thenReturn("user");
        when(request.getAttribute("login")).thenReturn("ilyaV");
        when(request.getAttribute("password")).thenReturn("pobeda");
        when(request.getAttribute("email")).thenReturn("ilya@mail.ru");
        controller.doPost(request, responce);
        User user = new User("ilya");
        user.setRole("user");
        user.setLogin("ilyaV");
        user.setPassword("pobeda");
        user.setEmail("ilya@mail.ru");
        assertThat(user, is(logic.isCredential("ilyaV", "pobeda")));

    }


}