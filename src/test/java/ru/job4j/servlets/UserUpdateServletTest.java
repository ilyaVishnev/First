package ru.job4j.servlets;

import org.junit.Test;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Пользователь on 03.07.2018.
 */
public class UserUpdateServletTest {
    private final ValidateService logic = ValidateService.getValidateService();

    @Test
    public void editUser() throws IOException {
        UsersController controller = new UsersController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);
        HttpSession session = request.getSession();
        when(request.getAttribute("id")).thenReturn(1);
        when(request.getAttribute("name")).thenReturn("philip");
        when(request.getAttribute("role")).thenReturn("user");
        when(request.getAttribute("login")).thenReturn("philipL");
        when(request.getAttribute("password")).thenReturn("pobeda");
        when(request.getAttribute("email")).thenReturn("dima@dfre");
        controller.doPost(request, responce);
        assertThat(request.getAttribute("myusers"), is(logic.findAll()));
    }

}