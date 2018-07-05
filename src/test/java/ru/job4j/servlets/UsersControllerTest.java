package ru.job4j.servlets;

import org.junit.Test;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Пользователь on 03.07.2018.
 */
public class UsersControllerTest {

    private final ValidateService logic = ValidateService.getValidateService();
    @Test
    public void getList() throws Exception {
        UsersController controller = new UsersController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);
        HttpSession session = request.getSession();
        User user = new User("ilya");
        user.setRole("user");
        user.setLogin("ilyaV");
        user.setPassword("pobeda");
        user.setEmail("ilya@mail.ru");
        when(session.getAttribute("myuser")).thenReturn(user);
        controller.doPost(request, responce);
        assertThat(request.getAttribute("myusers"), is(logic.findAll()));
    }

}