<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="New wine">
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/admin/wine/create"
           modelAttribute="wineCreate" cssClass="form-horizontal">

        <div class="form-group">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="color" cssClass="col-sm-2 control-label">Color</form:label>
            <div class="col-sm-10">
                <form:select path="color" cssClass="form-control">
                    <c:forEach items="${colors}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="color" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="taste" cssClass="col-sm-2 control-label">Taste</form:label>
            <div class="col-sm-10">
                <form:select path="taste" cssClass="form-control">
                    <c:forEach items="${taste}" var="t">
                        <form:option value="${t}">${t}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="taste" cssClass="error"/>
            </div>
        </div>

        <div class="form-group" >
            <form:label path="stocked" cssClass="col-sm-2 control-label">Stocked</form:label>
            <div class="col-sm-10">
                <form:input path="stocked" cssClass="form-control"/>
                <form:errors path="stocked" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group" >
            <form:label path="sold" cssClass="col-sm-2 control-label">Sold</form:label>
            <div class="col-sm-10">
                <form:input path="sold" cssClass="form-control"/>
                <form:errors path="sold" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="ingredients" cssClass="col-sm-2 control-label">Ingredient</form:label>
            <div class="col-sm-10">
                <form:select path="ingredients" cssClass="form-control">
                    <c:forEach items="${ingredients}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="ingredients" cssClass="error"/>
            </div>
        </div>

        <button class="btn btn-success" type="submit">Create wine</button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
