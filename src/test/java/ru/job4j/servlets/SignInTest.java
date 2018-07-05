package ru.job4j.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Пользователь on 03.07.2018.
 */
public class SignInTest {

    private final ValidateService logic = ValidateService.getValidateService();

    @Test
    public void userInSession() throws ServletException, IOException {
        SignIn controller = new SignIn();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);
        when(request.getAttribute("login")).thenReturn("mylogin");
        when(request.getAttribute("password")).thenReturn("pobeda");
        controller.doPost(request, responce);
        HttpSession session = request.getSession();
        assertThat(session.getAttribute("myuser"), is(logic.isCredential("mylogin", "pobeda")));
    }

}