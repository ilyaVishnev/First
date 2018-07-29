<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 23.07.2018
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create</title>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
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
                    var content = '<option disabled selected>Выберите страну</option>';
                    for (var i = 0; i < countries.length; i++) {
                        content += '<option value="' + countries[i].id + '">' + countries[i].country + '</option>';
                    }
                    $('#country').empty().html(content);
                    $('#city').empty().html('<option disabled selected>Выберите город</option>');
                    var citySel = document.getElementById('country');
                    var selectorOfCities = $('select[name = "country"]');
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
<form name="createForm" action="create" method="post" id="createForm">
    <p>Введите имя пользователя : <input type="text" name="name"  class="form-control"></p>
    <p>Введите роль пользователя : <select name="role"  class="form-control">
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select></p>
    <p>Введите логин пользователя : <input type="text" name="login"  class="form-control"></p>
    <p>Введите пароль пользователя : <input type="text" name="password"  class="form-control"></p>
    <p>Введите e-mail пользователя : <input type="text" name="email" id="email"  class="form-control"></p>
    <c:if test="${error !=''}">
        <div style="background-color: antiquewhite">
            <c:out value="${error}"></c:out>
        </div>
    </c:if>
    <p>Введите страну пользователя : <select name="country" id="country"  class="form-control">
    </select></p>
    <p>Введите город пользователя : <select name="city" id="city" onchange="document.forms['createForm'].submit()"  class="form-control">
    </select></p>
    <input type="submit" value="Создать" name="create" onclick="return checkEmptyPlaces();" class="btn btn-default">
</form>
</body>
</html>