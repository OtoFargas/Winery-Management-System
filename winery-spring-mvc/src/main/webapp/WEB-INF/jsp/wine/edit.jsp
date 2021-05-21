<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="Wine Edit">
<jsp:attribute name="body">

    <table class="table">
        <tbody>
            <tr>
                <th>Ingredients</th>
                <td><c:out value="${wine.ingredients}"/></td>
            </tr>
        </tbody>
    </table>

<%--    <form:form method="POST"--%>
<%--               action="${pageContext.request.contextPath}/wine/setDisease/${wine.id}" modelAttribute="setDisease">--%>
<%--         <div class="form-group">--%>
<%--             <form:label path="disease" cssClass="col-sm-2 control-label">Select a disease:</form:label>--%>
<%--             <div class="col-sm-10">--%>
<%--                    <form:select path="disease" cssClass="form-control">--%>
<%--                        <c:forEach items="${diseases}" var="disease">--%>
<%--                            <form:option value="${disease.name()}">${disease}</form:option>--%>
<%--                        </c:forEach>--%>
<%--                    </form:select>--%>
<%--                 <form:errors path="disease" cssClass="error"/>--%>
<%--             </div>--%>
<%--         </div>--%>
<%--        <button class="btn btn-primary" type="submit" name="cure">Cure</button>--%>
<%--        <button class="btn btn-danger" type="submit" name="add">Add</button>--%>
<%--    </form:form>--%>
<%--    <my:a href="/wine/cureAllDiseases/${wine.id}" class="btn btn-success">Cure All Diseases</my:a>&emsp;--%>

<%--    <table class="table">--%>
<%--        <tbody>--%>
<%--            <tr>--%>
<%--                <th>Stocked</th>--%>
<%--                <td><c:out value="${wine.stocked}"/></td>--%>
<%--            </tr>--%>
<%--        </tbody>--%>
<%--    </table>--%>

<%--    <form:form method="POST"--%>
<%--               action="${pageContext.request.contextPath}/wine/changeQuantity/${wine.id}" modelAttribute="changeQuantity">--%>
<%--         <div class="form-group">--%>
<%--             <form:label path="quantity" cssClass="col-sm-2 control-label">Enter new quantity:</form:label>--%>
<%--             <div class="col-sm-10">--%>
<%--                 <form:input path="quantity" cssClass="form-control"/>--%>
<%--                 <form:errors path="quantity" cssClass="help-block"/>--%>
<%--             </div>--%>
<%--         </div>--%>
<%--        <button class="btn btn-success" type="submit">Change</button>--%>
<%--    </form:form>--%>

    <my:a href="/wine/view/${wine.id}" class="btn btn-primary">Back</my:a>&emsp;
</jsp:attribute>
</my:pagetemplate>
