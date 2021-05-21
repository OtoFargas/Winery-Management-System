<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Wines">
<jsp:attribute name="body">
    <my:a href="/wine/new/" class="btn btn-success">New</my:a>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Wine</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${wines}" var="wine">
                <tr>
                    <td><c:out value="${wine.name}"/></td>
                    <td align="right">
                        <my:a href="/wine/view/${wine.id}" class="btn btn-primary">View</my:a>&emsp;
                        <my:a href="/wine/remove/${wine.id}" class="btn btn-danger">Remove</my:a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
