<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Harvests">
<jsp:attribute name="body">
    <my:a href="/harvest/new" class="btn btn-success">New</my:a>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Harvest year</th>
            <th scope="col">Grape</th>
            <th scope="col">Quantity</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${harvests}" var="harvest">
                <tr>
                    <td><c:out value="${harvest.harvestYear}"/></td>
                    <td><c:out value="${harvest.grape.name}"/></td>
                    <td><c:out value="${harvest.quantity}"/></td>
                    <td align="right">
                        <my:a href="/harvest/view/${harvest.id}" class="btn btn-primary">View</my:a>
                        <my:a href="/harvest/remove/${harvest.id}" class="btn btn-danger">Remove</my:a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
