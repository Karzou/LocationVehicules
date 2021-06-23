<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
    <head>
        <title>Erreur</title>
        <c:import url="head.jsp"></c:import>
    </head>

    <body>
    <div class="container">
        <c:import url="menu.jsp"></c:import>
        <h1><c:out value="${sessionScope.erreur}"></c:out></h1>
        <a clas="btn btn-primary" href="${pageContext.request.contextPath}${sessionScope.retour}">Liste utilisateur</a>
    </div>
    <c:import url="footer.jsp"></c:import>
    </body>
</html>
