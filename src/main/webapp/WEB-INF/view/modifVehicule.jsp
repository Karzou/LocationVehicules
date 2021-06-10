<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Modification Vehicule</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body class="body">
        <div class="container">
            <jsp:include page="menu.jsp" />

            <div class="modif">
                <form action="<c:url value="/gestionVehicule"/>" method="post">
                    <div class="form-group" id="form-modif">
                        <label>Marque : </label>
                        <input class="form-control" type="text" name="marque" value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque}">
                    </div>
                    <div class="form-group" id="form-modif2">
                        <label>Modèle : </label>
                        <input class="form-control" type="text" name="modele" value="${vehicule.modelesByIdModele.nomModele}">
                    </div>
                    <div class="form-group" id="form-modif9">
                        <label>Cylindrée : </label>
                        <input class="form-control" type="text" name="cylindree" value="${vehicule.cylindree}">
                    </div>
                    <div class="form-group" id="form-modif10">
                        <label>Puissance: </label>
                        <input class="form-control" type="text" name="puissance" value="${vehicule.puissance}">
                    </div>
                    <div class="form-group" id="form-modif3">
                        <label>Numéro de chassis: </label>
                        <input class="form-control" type="text" name="numChassis" value="${vehicule.numChassis}">
                    </div>
                    <div class="form-group" id="form-modif4">
                        <label>Immatriculation : </label>
                        <input class="form-control" type="text" name="immatriculation" value="${vehicule.immatriculation}">
                    </div>
                    <div class="form-group" id="form-modif5">
                        <label>Date achat : </label>
                        <input class="form-control" type="text" name="dateAchat" value="${vehicule.dateAchat}">
                    </div>
                    <div class="form-group" id="form-modif6">
                        <label>Prix journalier : </label>
                        <input class="form-control" type="text" name="prixJournalier" value="${vehicule.prixJournalier}">
                    </div>
                    <div class="form-group" id="form-modif7">
                        <label>Status : </label>
                        <input class="form-control" type="text" name="status" value="${vehicule.actifVehicule}">
                    </div>

                    <input type="hidden" name="idModif" value="${vehicule.idVehicule}"></input>
                    <button type="submit" value="Envoyer" id="bouton-modif" class="btn btn-primary">Modifer</button>
                </form>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
