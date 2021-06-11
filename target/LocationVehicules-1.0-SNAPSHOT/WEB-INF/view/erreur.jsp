<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Title</title>
    </head>

    <body>
        <h1><c:out value="${errorMessage}"></c:out></h1>
        <a clas="btn btn-primary" href="${pageContext.request.contextPath}${retour}">Liste utilisateur</a>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
