<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Feedback">
<jsp:attribute name="body">
    <my:a href="/feedback/remove/${feedback.id}" class="btn btn-danger">Remove</my:a>
    <my:a href="/feedback/list" class="btn btn-danger">Back</my:a>
    <table class="table">
        <tbody>
        <tr>
            <th>Author</th>
            <td><c:out value="${feedback.author}"/></td>
        </tr>

        <tr>
            <th>Date</th>
            <td><c:out value="${feedback.date}"/></td>
        </tr>

        <tr>
            <th>Wine</th>
            <td><c:out value="${feedback.wine.name}"/></td>
        </tr>

        <tr>
            <th>Rating</th>
            <td><c:out value="${feedback.rating}"/></td>
        </tr>

        <tr>
            <th>Content</th>
            <td><c:out value="${feedback.content}"/></td>
        </tr>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
