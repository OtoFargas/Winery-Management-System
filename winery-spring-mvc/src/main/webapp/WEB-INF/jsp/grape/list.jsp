<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Grape list:</title>
</head>

<body>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${grapes}" var="grape">
            <tr>
                <td>${grape.id}</td>
                <td><c:out value="${grape.name}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>

</html>
