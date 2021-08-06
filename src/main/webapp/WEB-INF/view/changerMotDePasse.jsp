<%--
  Created by IntelliJ IDEA.
  User: djkar
  Date: 06-08-21
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
    <title>Changer mot de passe</title>
    <c:import url="head.jsp"/>
</head>
<body class="body">
<div class="container">
    <c:import url="menu.jsp"/>

    <div class="content-global">
        <h2>Modification utilisateur</h2>

        <form action="<c:url value="/changerMotDePasse"/>" method="post">
            <div class="form-group"id="form-modif">
                <label>Password : </label>
                <input class="input-modif" name="oldPassword" value="${utilisateur.motDePasse}">
            </div>
            <div class="form-group"id="form-modif1">
                <label>Nouveau mot de passe : </label>
                <input class="input-modif" name="password" value="${utilisateur.motDePasse}">
            </div>
            <div class="form-group"id="form-modif2">
                <label>Confirmer le nouveau mot de passe : </label>
                <input class="input-modif" name="confirmPassword" value="${utilisateur.motDePasse}">
            </div>
            <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
            <button type="submit" value="Envoyer"id="bouton-modif" class="btn btn-primary">Modifier</button>
        </form>
    </div>
        <c:import url="footer.jsp"/>
</body>
</html>
