<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Grapes">
<jsp:attribute name="body">
    <my:a href="/admin/grape/new" class="btn btn-success">New</my:a>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Grape</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${grapes}" var="grape">
                <tr>
                    <td><c:out value="${grape.id}"/></td>
                    <td><c:out value="${grape.name}"/></td>
                    <td align="right">
                        <my:a href="/admin/grape/view/${grape.id}" class="btn btn-primary">View</my:a>&emsp;
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <my:a href="/admin" class="btn btn-danger">Back</my:a>
</jsp:attribute>
</my:pagetemplate>
