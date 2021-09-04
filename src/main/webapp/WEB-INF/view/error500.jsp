<%--
  Created by IntelliJ IDEA.
  User: djkar
  Date: 20-08-21
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
<head>
    <title>500</title>
    <c:import url="head.jsp"/>
</head>

<body>
<div class="container">
    <c:import url="menu.jsp"/>

    <h1>OUPS ERREUR 500</h1>

    <h1>Un problème coté serveur est survenu. Veuillez réessayer ultérieurement ou contacter votre fournisseur. </h1>

    <c:import url="footer.jsp"/>
</div>
</body>
</html>
