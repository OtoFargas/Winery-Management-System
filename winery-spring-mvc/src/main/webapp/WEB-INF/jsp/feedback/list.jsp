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
            <th scope="col">ID</th>
            <th scope="col">Author</th>
            <th scope="col">Wine</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${feedbacks}" var="feedback">
                <tr>
                    <td><c:out value="${feedback.id}"/></td>
                    <td><c:out value="${feedback.author}"/></td>
                    <td><c:out value="${feedback.wine.id}: ${feedback.wine.name}"/></td>
                    <td align="right">
                        <my:a href="/admin/feedback/view/${feedback.id}" class="btn btn-primary">View</my:a>&emsp;
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <my:a href="/admin" class="btn btn-danger">Back</my:a>
</jsp:attribute>
</my:pagetemplate>
