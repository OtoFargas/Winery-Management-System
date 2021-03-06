<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Grape ${grape.id} Edit">
<jsp:attribute name="body">

    <table class="table">
        <tbody>
            <tr>
                <th>Diseases</th>
                <td>
                    <c:forEach items="${grape.diseases}" var="disease" varStatus="loopStatus">
                        <c:out value="${disease}" />
                        <c:if test="${!loopStatus.last}">, </c:if>
                    </c:forEach>
                </td>
            </tr>
        </tbody>
    </table>

    <form:form method="POST"
               action="${pageContext.request.contextPath}/admin/grape/setDisease/${grape.id}" modelAttribute="setDisease">
         <div class="form-group">
             <form:label path="disease" cssClass="col-sm-2 control-label">Select a disease:</form:label>
             <div class="col-sm-4">
                    <form:select path="disease" cssClass="form-control">
                        <c:forEach items="${diseases}" var="disease">
                            <form:option value="${disease}">${disease.toString()}</form:option>
                        </c:forEach>
                    </form:select>
                 <form:errors path="disease" cssClass="error"/>
             </div>&emsp;
             <button class="btn btn-primary" type="submit" name="cure">Cure</button>&emsp;
             <button class="btn btn-danger" type="submit" name="add">Add</button> &emsp;
             <my:a href="/admin/grape/cureAllDiseases/${grape.id}" class="btn btn-success">Cure All Diseases</my:a>
         </div>
    </form:form>

    <table class="table">
        <tbody>
            <tr>
                <th>Quantity</th>
                <td><c:out value="${grape.quantity}"/></td>
            </tr>
        </tbody>
    </table>

    <form:form method="POST"
               action="${pageContext.request.contextPath}/admin/grape/changeQuantity/${grape.id}" modelAttribute="changeQuantity">
         <div class="form-group">
             <form:label path="quantity" cssClass="col-sm-2 control-label">Enter new quantity:</form:label>
             <div class="col-sm-2">
                 <form:input path="quantity" cssClass="form-control"/>
                 <form:errors path="quantity" cssClass="help-block"/>
             </div>&emsp;
             <button class="btn btn-success" type="submit">Change</button>
         </div>
    </form:form>
    <my:a href="/admin/grape/view/${grape.id}" class="btn btn-danger">Back</my:a>
</jsp:attribute>
</my:pagetemplate>
