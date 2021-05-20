<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="New grape">
<jsp:attribute name="body">

<form:form method="post" action="${pageContext.request.contextPath}/product/create"
           modelAttribute="grapeCreate" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
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

<%--        <div class="form-group ${price_error?'has-error':''}" >--%>
<%--            <form:label path="price" cssClass="col-sm-2 control-label">Quantity</form:label>--%>
<%--            <div class="col-sm-10">--%>
<%--                <form:input path="price" cssClass="form-control"/>--%>
<%--                <form:errors path="price" cssClass="help-block"/>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    --%>
<%--        <div class="form-group">--%>
<%--            <form:label path="currency" cssClass="col-sm-2 control-label">Disease</form:label>--%>
<%--            <div class="col-sm-10">--%>
<%--                <form:select path="currency" cssClass="form-control">--%>
<%--                    <c:forEach items="${currencies}" var="c">--%>
<%--                        <form:option value="${c}">${c}</form:option>--%>
<%--                    </c:forEach>--%>
<%--                </form:select>--%>
<%--                <form:errors path="currency" cssClass="error"/>--%>
<%--            </div>--%>
<%--        </div>--%>

    <button class="btn btn-success" type="submit">Create grape</button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>