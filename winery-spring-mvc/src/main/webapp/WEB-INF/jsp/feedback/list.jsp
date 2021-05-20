<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Feedbacks ">
<jsp:attribute name="body">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Wine</th>
            <th scope="col">Color</th>
            <th scope="col">Taste</th>
            <th scope="col">Ingredients</th>
            <th scope="col">Stocked</th>
            <th scope="col">Sold</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${feedbacks}" var="feedback">
                <tr>
                    <td><c:out value="${feedback.author}"/></td>
                    <td><c:out value="${feedback.rating}"/></td>
                    <td><c:out value="${feedback.content}"/></td>
                    <td><c:out value="${feedback.date}"/></td>
                    <td><c:out value="${feedback.wine}"/></td>
                    <td>
                        <my:a href="/wine/remove/${feedback.id}" class="btn btn-danger">Delete</my:a>
                        <my:a href="/wine/edit/${feedback.id}" class="btn btn-secondary">Edit</my:a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
