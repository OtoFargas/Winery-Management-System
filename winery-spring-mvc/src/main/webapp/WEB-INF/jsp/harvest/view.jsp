<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Harvest">
<jsp:attribute name="body">
    <my:a href="/harvest/remove/${harvest.id}" class="btn btn-danger">Remove</my:a>
    <my:a href="/harvest/list" class="btn btn-danger">Back</my:a>
    <p><br></p>

    <table class="table">
        <tbody>

        <tr>
            <th>Harvest year</th>
            <td><c:out value="${harvest.harvestYear}"/></td>
        </tr>

        <tr>
            <th>Grape</th>
            <td><c:out value="${harvest.grape.name}"/></td>
        </tr>

        <tr>
            <th>Quality</th>
            <td><c:out value="${harvest.quality}"/></td>
        </tr>

        <tr>
            <th>Quantity</th>
            <td><c:out value="${harvest.quantity}"/></td>
        </tr>

        <tr>
            <th>Wine</th>
            <td><c:out value="${harvest.wine.name}"/></td>
        </tr>

        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
