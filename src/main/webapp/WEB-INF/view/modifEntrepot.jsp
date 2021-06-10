<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion Vehicle</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body class="body">
        <div class="container">
            <jsp:include page="menu.jsp" />

            <div class="modif">
                <form action="gestionEntrepot" method="post">
                    <div class="form-group" id="form-modif">
                        <label>Nom entrepôt : </label>
                        <input class="form-control" type="text" name="marque" value="${entrepot.nomEntrepot}">
                    </div>
                    <div class="form-group" id="form-modif2">
                        <label>Nombre de place : </label>
                        <input class="form-control" type="text" name="modele" value="${entrepot.nombrePlace}">
                    </div>
                    <div class="form-group" id="form-modif9">
                        <label>Rue : </label>
                        <input class="form-control" type="text" name="cylindree" value="${entrepot.adressesByIdAdresse.rue}">
                    </div>
                    <div class="form-group" id="form-modif10">
                        <label>Numéro : </label>
                        <input class="form-control" type="text" name="puissance" value="${entrepot.adressesByIdAdresse.numero}">
                    </div>
                    <div class="form-group" id="form-modif3">
                        <label>Boite : </label>
                        <input class="form-control" type="text" name="numChassis" value="${entrepot.adressesByIdAdresse.boite}">
                    </div>
                    <div class="form-group" id="form-modif4">
                        <label>Code postal : </label>
                        <input class="form-control" type="text" name="immatriculation" value="${entrepot.adressesByIdAdresse.villesByIdVille.codePostal}">
                    </div>
                    <div class="form-group" id="form-modif5">
                        <label>Ville : </label>
                        <input class="form-control" type="text" name="dateAchat" value="${entrepot.adressesByIdAdresse.villesByIdVille.nomVille}">
                    </div>

                    <input type="hidden" name="idModif" value="${entrepot.idEntrepot}"></input>
                    <button type="submit" value="Envoyer" id="bouton-modif" class="btn btn-primary">Modifer</button>
                </form>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
