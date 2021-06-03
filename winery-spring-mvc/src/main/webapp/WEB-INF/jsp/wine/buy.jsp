<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Buy wine">
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
        <tr>
            <td><c:out value="${wine.name}"/></td>
            <td><c:out value="${wine.color}"/></td>
            <td><c:out value="${wine.taste}"/></td>
            <td><c:out value="${wine.harvests.iterator().next().harvestYear}"/></td>
            <td><c:out value="${wine.stocked}"/></td>
        </tr>
    </table>

    <form:form method="POST"
               action="${pageContext.request.contextPath}/user/wine/buyAmount/${wine.id}" modelAttribute="wineBuy">
         <div class="form-group">
             <form:label path="amount" cssClass="col-sm-2 control-label">Enter desired amount:</form:label>
             <div class="col-sm-2">
                 <form:input path="amount" cssClass="form-control"/>
                 <form:errors path="amount" cssClass="help-block"/>
             </div>
             <button class="btn btn-success" type="submit">Buy</button>
         </div>

        <my:a href="/user" class="btn btn-danger">Back</my:a>&emsp;
    </form:form>
</jsp:attribute>
</my:pagetemplate>
