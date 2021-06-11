<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion Vehicule</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body class="body">
        <div class="container">
            <jsp:include page="menu.jsp" />

            <div class="content-vehicle">
                <h1>Liste des véhicules</h1>

                <div>
                    <table class="table-custom">
                        <thead>
                            <th scope="col">Marque</th>
                            <th scope="col">Modèle</th>
                            <th scope="col">Cylindrée</th>
                            <th scope="col">Puissance</th>
                            <th scope="col">Num Chassis</th>
                            <th scope="col">Immatriculation</th>
                            <th scope="col">Date achat</th>
                            <th scope="col">Prix journalier</th>
                            <th scope="col">Actif</th>
                            <th scope="col">Modification</th>
                            <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                            <c:forEach var="vehicule" items="${vehiculeList}">
                            <tr>
                                <td><c:out value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque}"></c:out></td>
                                <td><c:out value="${vehicule.modelesByIdModele.nomModele}"></c:out></td>
                                <td><c:out value="${vehicule.cylindree}"></c:out></td>
                                <td><c:out value="${vehicule.puissance}"></c:out></td>
                                <td><c:out value="${vehicule.numChassis}"></c:out></td>
                                <td><c:out value="${vehicule.immatriculation}"></c:out></td>
                                <td><c:out value="${vehicule.dateAchat}"></c:out></td>
                                <td><c:out value="${vehicule.prixJournalier}"></c:out></td>
                                <td><c:out value="${vehicule.actifVehicule}"></c:out></td>
                                <td>
                                    <form action="<c:url value="/modifVehicule"/>" method="post">
                                        <input type="hidden" name="idModif" value="${vehicule.idVehicule}"></input>
                                        <button class="btn-modif" name="idModif" type="submit" value="modifer">Modifer</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supVehicule"/>" method="post">
                                        <input type="hidden" name="idSup" value="${vehicule.idVehicule}"></input>
                                        <button class="btn-sup" name="idSup" type="submit" value="supprimer">Supprimer</button>
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
