<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
    <head>
        <title>Erreur</title>
        <c:import url="head.jsp"/>
    </head>

    <body>
        <div class="container">
            <c:import url="menu.jsp"/>
            <h1><c:out value="${sessionScope.erreur}"/></h1>
            <a clas="btn btn-primary" href="${pageContext.request.contextPath}${sessionScope.retour}">Liste utilisateur</a>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
