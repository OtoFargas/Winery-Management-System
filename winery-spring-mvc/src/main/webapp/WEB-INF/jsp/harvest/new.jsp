<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="New harvest">
<jsp:attribute name="body">

<form:form method="post" action="${pageContext.request.contextPath}/admin/harvest/create"
           modelAttribute="harvestCreate" cssClass="form-horizontal">

        <div class="form-group">
            <form:label path="harvestYear" cssClass="col-sm-2 control-label">Harvest year</form:label>
            <div class="col-sm-6">
                <form:input path="harvestYear" cssClass="form-control" form:type="number"/>
                <form:errors path="harvestYear" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="grapeId" cssClass="col-sm-2 control-label">Grape</form:label>
            <div class="col-sm-6">
                <form:select path="grapeId" cssClass="form-control">
                    <c:forEach items="${grapes}" var="grape">
                        <form:option value="${grape.id}">${grape.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="grapeId" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="quality" cssClass="col-sm-2 control-label">Quality</form:label>
            <div class="col-sm-6">
                <form:select path="quality" cssClass="form-control">
                    <form:option value="LOW">LOW</form:option>
                    <form:option value="MEDIUM">MEDIUM</form:option>
                    <form:option value="HIGH">HIGH</form:option>
                </form:select>
                <form:errors path="quality" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="quantity" cssClass="col-sm-2 control-label">Quantity</form:label>
            <div class="col-sm-6">
                <form:input path="quantity" cssClass="form-control" form:type="number"/>
                <form:errors path="quantity" cssClass="help-block"/>
            </div>
        </div>

    <button class="btn btn-success" type="submit">Create</button>&emsp;
    <my:a href="/admin/harvest/list" class="btn btn-danger">Back</my:a>
    </form:form>
</jsp:attribute>
</my:pagetemplate>