<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Option suppl�mentaire</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-option-sup">
                <c:choose>
                    <c:when test="${erreurConnBdd == true}">
                        <h1>Une erreur est survenue, veuillez r�essayer !!!</h1>
                    </c:when>

                    <c:otherwise>
                        <h2>Option suppl�mentaire</h2>

                        <h3>Page en construction</h3>

                        <h4>Exemple envoi de donn�es</h4>

                        <p>ID lieu de d�part: ${idLieuDepart}</p>
                        <p>ID lieu de retour: ${idLieuRetour}</p>
                        <p>Date de d�part: ${DateDepart}</p>
                        <p>Heure de d�part: ${HeureDepart}</p>
                        <p>Date de retour: ${DateRetour}</p>
                        <p>Heure de retour: ${HeureRetour}</p>
                        <p>ID v�hicule: ${idVehicule}</p>
                        <p>Prix total: ${prixTotal}</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
