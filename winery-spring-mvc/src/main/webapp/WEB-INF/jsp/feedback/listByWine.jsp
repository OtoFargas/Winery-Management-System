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
            <th scope="col">Year</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${wine.name}"/></td>
            <td><c:out value="${wine.color}"/></td>
            <td><c:out value="${wine.taste}"/></td>
            <td><c:out value="${wine.harvests.iterator().next().harvestYear}"/></td>
        </tr>
    </table>
        <my:a href="/feedback/new/${wine.id}" class="btn btn-primary">Add Feedback</my:a>&emsp;
        <my:a href="/" class="btn btn-danger">Back</my:a>&emsp;
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Author</th>
            <th scope="col">Date</th>
            <th scope="col">Rating</th>
            <th scope="col">Content</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${feedbacks}" var="feedback">
                <tr>
                    <td><c:out value="${feedback.author}"/></td>
                    <td><c:out value="${feedback.date}"/></td>
                    <td><c:out value="${feedback.rating}"/></td>
                    <td><c:out value="${feedback.content}"/></td>
                    <td align="right">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
