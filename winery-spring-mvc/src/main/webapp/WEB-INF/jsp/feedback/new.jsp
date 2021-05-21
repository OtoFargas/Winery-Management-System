<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="New feedback">
<jsp:attribute name="body">

<form:form method="post" action="${pageContext.request.contextPath}/feedback/create"
           modelAttribute="feedbackCreate" cssClass="form-horizontal">

        <div class="form-group">
            <form:label path="author" cssClass="col-sm-2 control-label">Author</form:label>
            <div class="col-sm-10">
                <form:input path="author" cssClass="form-control"/>
                <form:errors path="author" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
            <div class="col-sm-10">
                <form:input path="date" cssClass="form-control"/>
                <form:errors path="date" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="wineId" cssClass="col-sm-2 control-label">Wine</form:label>
            <div class="col-sm-10">
                <form:select path="wineId" cssClass="form-control">
                    <c:forEach items="${wines}" var="wine">
                        <form:option value="${wine.id}">${wine.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="wineId" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="rating" cssClass="col-sm-2 control-label">Rating</form:label>
            <div class="col-sm-10">
                <form:select path="rating" cssClass="form-control">
                    <c:forEach items="${rating}" var="r">
                        <form:option value="${r}">${r}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="rating" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="content" cssClass="col-sm-2 control-label">Content</form:label>
            <div class="col-sm-10">
                <form:textarea cols="80" rows="10" path="content" cssClass="form-control"/>
                <form:errors path="content" cssClass="help-block"/>
            </div>
        </div>

    <button class="btn btn-success" type="submit">Create</button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
