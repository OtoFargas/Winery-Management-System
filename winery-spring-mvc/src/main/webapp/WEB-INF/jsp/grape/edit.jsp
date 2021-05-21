<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Grape Edit">
<jsp:attribute name="body">

    <table class="table">
        <tbody>
            <tr>
                <th>Diseases</th>
                <td><c:out value="${grape.diseases}"/></td>
            </tr>
        </tbody>
    </table>

    <form:form method="POST"
               action="${pageContext.request.contextPath}/grape/setDisease/${grape.id}" modelAttribute="setDisease">
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
        <button class="btn btn-success" type="submit" name="cure">Cure</button>
        <button class="btn btn-danger" type="submit" name="add">Add</button>
    </form:form>

    <table class="table">
        <tbody>
            <tr>
                <th>Quantity</th>
                <td><c:out value="${grape.quantity}"/></td>
            </tr>
        </tbody>
    </table>
    <my:a href="/grape/view/${grape.id}" class="btn btn-primary">Back</my:a>&emsp;
</jsp:attribute>
</my:pagetemplate>
