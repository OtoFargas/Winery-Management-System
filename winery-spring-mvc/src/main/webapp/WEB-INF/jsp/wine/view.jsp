<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Wine ${wine.id}">
<jsp:attribute name="body">

    <my:a href="/admin/wine/remove/${wine.id}" class="btn btn-danger">Remove</my:a>&emsp;
    <my:a href="/admin/wine/list" class="btn btn-danger">Back</my:a>&emsp;
    <p><br></p>

    <table class="table">
        <tbody>
            <tr>
                <th>Name</th>
                <td><c:out value="${wine.name}"/></td>
            </tr>
            <tr>
                <th>Color</th>
                <td><c:out value="${wine.color}"/></td>
            </tr>
            <tr>
                <th>Stocked</th>
                <td><c:out value="${wine.stocked}"/></td>
            </tr>
            <tr>
                <th>Sold</th>
                <td><c:out value="${wine.sold}"/></td>
            </tr>
            <tr>
                <th>Taste</th>
                <td><c:out value="${wine.taste}"/></td>
            </tr>
            <tr>
                <th>Ingredients</th>
                <td>
                    <c:forEach items="${wine.ingredients}" var="ingredient" varStatus="loopStatus">
                        <c:out value="${ingredient}" />
                        <c:if test="${!loopStatus.last}">, </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>Feedbacks</th>
                <td>
                    <c:forEach items="${wine.feedbacks}" var="feedback" varStatus="loopStatus">
                        <c:out value="Feedback: ${feedback.id}, Author: ${feedback.author}, Rating: ${feedback.rating}"/>
                        <c:if test="${!loopStatus.last}"> | </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>Harvests</th>
                <td>
                    <c:forEach items="${wine.harvests}" var="harvest" varStatus="loopStatus">
                        <c:out value="Harvest: ${harvest.id}, Year: ${harvest.harvestYear}" />
                        <c:if test="${!loopStatus.last}"> | </c:if>
                    </c:forEach>
                </td>
            </tr>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
