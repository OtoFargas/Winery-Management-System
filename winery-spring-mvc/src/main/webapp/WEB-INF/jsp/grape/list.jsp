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
    <my:a href="/grape/new" class="btn btn-success">New</my:a>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Grape</th>
            <th scope="col">Color</th>
            <th scope="col">Diseases</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${grapes}" var="grape">
                <tr>
                    <td><c:out value="${grape.name}"/></td>
                    <td><c:out value="${grape.color}"/></td>
                    <td><c:out value="${grape.diseases}"/></td>
                    <td>
                        <my:a href="/grape/view/${grape.id}" class="btn btn-primary">View</my:a>
                        <my:a href="/grape/remove/${grape.id}" class="btn btn-danger">Remove</my:a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
