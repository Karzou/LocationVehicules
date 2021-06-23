<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification couleur</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification couleur</h2>

                <form action="<c:url value="/gestionCouleur"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Nom entrepôt : </label>
                        <input class="input-modif" type="text" name="nomCouleur" value="${couleur.nomCouleur}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Status : </label>
                        <input type="checkbox" name="actifCouleur" id="actifCouleur" value="ok" <c:if test="${couleur.actifCouleur}">checked</c:if>>
                        <label for="actifCouleur" class="label-checkbox">Actif</label>
                    </div>

                    <input type="hidden" name="idModif" value="${couleur.idCouleur}"/>
                    <button type="submit" class="btn-modif2" value="Envoyer" id="bouton-modif">Modifier</button>
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
