<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Grape ${grape.id}">
<jsp:attribute name="body">

    <my:a href="/admin/grape/edit/${grape.id}" class="btn btn-primary">Edit</my:a>&emsp;
    <my:a href="/admin/grape/remove/${grape.id}" class="btn btn-danger">Remove</my:a>&emsp;
    <my:a href="/admin/grape/list" class="btn btn-danger">Back</my:a>&emsp;
    <p><br></p>

    <table class="table">
        <tbody>
        <tr>
            <th>Name</th>
            <td><c:out value="${grape.name}"/></td>
        </tr>

        <tr>
            <th>Color</th>
            <td><c:out value="${grape.color}"/></td>
        </tr>

        <tr>
            <th>Quantity</th>
            <td><c:out value="${grape.quantity}"/></td>
        </tr>

        <tr>
            <th>Diseases</th>
            <td>
                <c:forEach items="${grape.diseases}" var="disease" varStatus="loopStatus">
                    <c:out value="${disease}" />
                    <c:if test="${!loopStatus.last}">, </c:if>
                </c:forEach>
            </td>
        </tr>

        <tr>
            <th>Harvests</th>
            <td>
                <c:forEach items="${grape.harvests}" var="harvest" varStatus="loopStatus">
                    <c:out value="Harvest: ${harvest.id}, Year: ${harvest.harvestYear}, Quality: ${harvest.quality.toString()}, Quantity: ${harvest.quantity}"/>
                    <c:if test="${!loopStatus.last}"> | </c:if>
                </c:forEach>
            </td>
        </tr>

        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
