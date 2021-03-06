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
            <th scope="col">Year</th>
            <th scope="col">Stocked</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${wines}" var="wine">
                <tr>
                    <td><c:out value="${wine.name}"/></td>
                    <td><c:out value="${wine.color}"/></td>
                    <td><c:out value="${wine.taste}"/></td>
                    <td><c:out value="${wine.wineYear}"/></td>
                    <td><c:out value="${wine.stocked}"/></td>
                    <td align="right">
                        <my:a href="/user/wine/buy/${wine.id}" class="btn btn-success">Buy Wine</my:a>&emsp;
                        <my:a href="/user/feedback/listByWine/${wine.id}" class="btn btn-primary">View Feedbacks</my:a>&emsp;
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>