<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<my:pagetemplate title="About">
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Winery management IS</h1>

        <p>Winery management system is an information system that will allow a person 
            to manage its own winery in a better way. The main user role can manage the 
            yearly wine harvest, annotating the quality, quantity of different types of 
            grapes. Then he will manage the wines that can be produced depending on the 
            type of grapes available and other ingredients. The user can also manage the 
            list of wine bottles sold and stocked and available to buyers. Buyers can 
            consult the system to buy quantities of wine bottles, also leaving feedback 
            about the quality of the wine they have bought.
        </p>
        
    </div>

</jsp:attribute>
</my:pagetemplate>