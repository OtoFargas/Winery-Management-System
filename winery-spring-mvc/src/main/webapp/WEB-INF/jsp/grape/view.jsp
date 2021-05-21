<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Grape">
<jsp:attribute name="body">

    <form:form method="POST"
       action="${pageContext.request.contextPath}/grape/cureDisease/${grape.id}" modelAttribute="cureDisease">
         <div class="form-group">
             <form:label path="disease" cssClass="col-sm-2 control-label">Select a disease</form:label>
             <div class="col-sm-10">
                    <form:select path="disease" cssClass="form-control">
                        <c:forEach items="${diseases}" var="disease">
                            <form:option value="${disease.name()}">${disease}</form:option>
                        </c:forEach>
                    </form:select>
                 <form:errors path="disease" cssClass="error"/>
             </div>
         </div>
        <button class="btn btn-success" type="submit">Cure</button>
    </form:form>

    <my:a href="/grape/view/${grape.id}" class="btn btn-primary">Change Quantity</my:a>&emsp;
    <my:a href="/grape/addHarvest/${grape.id}/${harvest.id}" class="btn btn-primary">Add Harvest</my:a>&emsp;
    <my:a href="/grape/view/${grape.id}" class="btn btn-primary">Add Disease</my:a>&emsp;
    <my:a href="/grape/view/${grape.id}" class="btn btn-primary">Cure Disease</my:a>&emsp;
    <my:a href="/grape/cureAllDiseases/${grape.id}" class="btn btn-primary">Cure All Diseases</my:a>&emsp;
    <my:a href="/grape/remove/${grape.id}" class="btn btn-danger">Remove</my:a>&emsp;
    <my:a href="/grape/list" class="btn btn-danger">Back</my:a>&emsp;
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
            <td><c:out value="${grape.diseases}"/></td>
        </tr>

        <tr>
            <th>Harvests</th>
            <td><c:out value="${grape.harvests}"/></td>
        </tr>

        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
