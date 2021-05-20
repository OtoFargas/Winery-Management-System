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
            <c:forEach items="${wines}" var="wine">
                <tr>
                    <td><c:out value="${wine.name}"/></td>
                    <td><c:out value="${wine.color}"/></td>
                    <td><c:out value="${wine.taste}"/></td>
                    <td><c:out value="${wine.ingredients}"/></td>
                    <td><c:out value="${wine.stocked}"/></td>
                    <td><c:out value="${wine.sold}"/></td>
                    <td>
                        <my:a href="/wine/delete/${wine.id}" class="btn btn-danger">Delete</my:a>
                        <my:a href="/wine/edit/${wine.id}" class="btn btn-secondary">Edit</my:a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
