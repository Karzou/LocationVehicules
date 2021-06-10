<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Vehicule</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body class="body">
        <div class="container">
            <jsp:include page="menu.jsp"></jsp:include>

            <c:forEach var="vehicule" items="${vehiculeList}">
            <div class="content-vehicle-search">
                <div><h2>${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque} ${vehicule.modelesByIdModele.nomModele}</h2></div>
                <p>${vehicule.numChassis}</p>
                <p>${vehicule.cylindree}cc</p>
            </div>
            </c:forEach>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
