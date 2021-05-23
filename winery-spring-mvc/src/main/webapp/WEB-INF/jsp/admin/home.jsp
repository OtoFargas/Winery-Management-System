<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Winery">
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Winery management IS</h1>
        <my:a href="/admin/grape/list" class="btn btn-lg btn-success">Grapes</my:a>&emsp;
        <my:a href="/admin/wine/list" class="btn btn-lg btn-success">Wines</my:a>&emsp;
        <my:a href="/admin/harvest/list" class="btn btn-lg btn-success">Harvests</my:a>&emsp;
        <my:a href="/admin/feedback/list" class="btn btn-lg btn-success">Feedbacks</my:a>&emsp;
    </div>

</jsp:attribute>
</my:pagetemplate>