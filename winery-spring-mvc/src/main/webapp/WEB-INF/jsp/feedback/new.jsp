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

<form:form method="post" action="${pageContext.request.contextPath}/feedback/create/${wine.id}"
           modelAttribute="feedbackCreate" cssClass="form-horizontal">

        <div class="form-group">
            <form:label path="author" cssClass="col-sm-2 control-label">Author</form:label>
            <div class="col-sm-6">
                <form:input path="author" cssClass="form-control"/>
                <form:errors path="author" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="date" cssClass="col-sm-2 control-label">Date (dd.mm.yyyy)</form:label>
            <div class="col-sm-6">
                <form:input path="date" cssClass="form-control"/>
                <form:errors path="date" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="rating" cssClass="col-sm-2 control-label">Rating</form:label>
            <div class="col-sm-6">
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
            <div class="col-sm-6">
                <form:textarea cols="80" rows="10" path="content" cssClass="form-control"/>
                <form:errors path="content" cssClass="help-block"/>
            </div>
        </div>

    <button class="btn btn-success" type="submit">Create</button>&emsp;
    <my:a href="/feedback/listByWine/${wine.id}" class="btn btn-danger">Back</my:a>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
