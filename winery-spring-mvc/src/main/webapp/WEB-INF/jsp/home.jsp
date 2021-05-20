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
        <p>
            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/grape/list"
              role="button">Grapes</a>
        </p>
        <p>
            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/wine/list"
              role="button">Wines</a>
        </p>
        <p>
            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/harvest/list"
               role="button">Harvests</a>
        </p>
        <p>
            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/feedback/list"
               role="button">Feedbacks</a>
        </p>
    </div>

</jsp:attribute>
</my:pagetemplate>