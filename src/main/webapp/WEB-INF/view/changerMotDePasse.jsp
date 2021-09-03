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
            <h2>Modification de votre mot de passe</h2>

            <h3>Veuillez encoder votre nouveau mot de passe.</h3>

            <form action="<c:url value="/changerMotDePasse"/>" method="post">
                <div class="div-input-modif">
                    <label class="label-input2">Nouveau mot de passe</label>
                    <input class="input-modif" name="password" value="${utilisateur.motDePasse}">
                </div>

                <div class="div-input-modif">
                    <label class="label-input2">Confirmer nouveau mot de passe</label>
                    <input class="input-modif" name="confirmPassword" value="${utilisateur.motDePasse}">
                </div>

                <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
                <input type="submit" class="btn-modif2" value="Modifier">
                <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/profil">
            </form>
        </div>
        <c:import url="footer.jsp"/>
    </body>
</html>

