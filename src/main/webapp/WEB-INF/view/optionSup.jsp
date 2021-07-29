<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Option supplémentaire</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-option-sup">
                <c:choose>
                    <c:when test="${erreurConnBdd == true}">
                        <h1>Une erreur est survenue, veuillez réessayer !!!</h1>
                    </c:when>

                    <c:otherwise>
                        <h2>Option supplémentaire</h2>

                        <h3>Page en construction</h3>

                        <h4>Exemple envoi de données</h4>

                        <p>ID lieu de départ: ${idLieuDepart}</p>
                        <p>ID lieu de retour: ${idLieuRetour}</p>
                        <p>Date de départ: ${DateDepart}</p>
                        <p>Heure de départ: ${HeureDepart}</p>
                        <p>Date de retour: ${DateRetour}</p>
                        <p>Heure de retour: ${HeureRetour}</p>
                        <p>ID véhicule: ${idVehicule}</p>
                        <p>Prix total: ${prixTotal}</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
