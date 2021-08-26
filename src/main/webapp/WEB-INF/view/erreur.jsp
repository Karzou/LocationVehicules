<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Erreur</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h1><c:out value="${sessionScope.erreur}"/></h1>

                <input type="button" class="btn-return" value="Retour" onclick=location.href="${pageContext.request.contextPath}${sessionScope.retour}">
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
