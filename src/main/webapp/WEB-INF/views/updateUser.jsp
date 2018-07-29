<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 27.07.2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script>
        $(document).ready(function () {
            var countryUser = '${user.getCountry()}';
            var cityUser = '${user.getCity()}';
            var id = '${countryId}';
            var error = '${error}';
            if (!!error) {
                document.getElementById("email").style.borderColor = "#FF0000";
            }
            $.ajax({
                type: 'GET',
                url: '/countries',
                dataType: 'json',
                success: function (data) {
                    var countries = data.countryArray;
                    var cities = data.cityArray;
                    var content = '<option value="' + id + '">' + countryUser + '</option>';
                    for (var i = 0; i < countries.length; i++) {
                        if (countries[i].country != countryUser) {
                            content += '<option value="' + countries[i].id + '">' + countries[i].country + '</option>';
                        }
                    }
                    $('#country').empty().html(content);
                    var citySel = document.getElementById('country');
                    content = '<option value="' + cityUser + '">' + cityUser + '</option>';
                    for (var i = 0; i < cities.length; i++) {
                        if (cities[i].Id_country == citySel.options[citySel.selectedIndex].value && cities[i].city != cityUser) {
                            content += '<option value="' + cities[i].city + '">' + cities[i].city + '</option>';
                        }
                    }
                    $('#city').empty().html(content);
                    $('#country').change(function () {
                        var content = '';
                        for (var i = 0; i < cities.length; i++) {
                            if (cities[i].Id_country == citySel.options[citySel.selectedIndex].value) {
                                content += '<option value="' + cities[i].city + '">' + cities[i].city + '</option>';
                            }
                        }
                        $('#city').html(content);
                    })
                }
            })
            var adminRole = '${myuser}';
            var userRole = '${user.getRole()}';
            if (adminRole == 'user') {
                $('#role').append('<option value="user">user</option>');
            }
            else if (userRole == 'user') {
                $('#role').append('<option value="user">user</option><option value="admin">admin</option>');
            } else {
                $('#role').append('<option value="admin">admin</option><option value="user">user</option>');
            }
        })
        function checkEmptyPlaces() {
            var data = {
                empty: false
            };
            var elem = $("#createForm").find('input, select').each(
                function () {
                    if ($(this).val() == '' || $(this).val() == null) {
                        data.empty = true;
                    }
                }
            );
            if (data.empty) {
                alert("some of the fields are emty");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form action="edit" method="post" name="updateForm">
    <p>Введите имя пользователя : <input type="text" name="name" value="${user.getName()}"  class="form-control"></p>
    <p>Введите роль пользователя : <select name="role" id="role"  class="form-control">
    </select></p>
    <p>Введите логин пользователя : <input type="text" name="login" value="${user.getLogin()}"  class="form-control"></p>
    <p>Введите пароль пользователя : <input type="text" name="password" value="${user.getPassword()}"  class="form-control"></p>
    <p>Введите e-mail пользователя : <input type="text" name="email" value="${user.getEmail()}" id="email" class="form-control"></p>
    <c:if test="${error !=''}">
        <div style="background-color: antiquewhite">
            <c:out value="${error}"></c:out>
        </div>
    </c:if>
    <p>Введите страну пользователя : <select name="country" id="country"  class="form-control">
        <option disabled selected>${user.getCountry()}</option>
    </select></p>
    <p>Введите город пользователя : <select name="city" id="city" onchange="document.forms['updateForm'].submit()" class="form-control">
        <option disabled selected>${user.getCity()}</option>
    </select></p>
    <input type="hidden" name="id" value="${user.getId()}"/>
    <input type="submit" value="Сохранить" name="update" onclick="return checkEmptyPlaces();" class="btn btn-default">
</form>
</body>
</html>
