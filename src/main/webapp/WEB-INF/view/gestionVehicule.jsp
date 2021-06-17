<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Gestion véhicule</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-vehicle">
                <h2>Ajout de véhicule</h2>


            </div>

            <div class="content-vehicle">
                <h2>Liste des véhicules</h2>

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
                                <td><c:out value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque}"/></td>
                                <td><c:out value="${vehicule.modelesByIdModele.nomModele}"/></td>
                                <td><c:out value="${vehicule.cylindree}"/></td>
                                <td><c:out value="${vehicule.puissance}"/></td>
                                <td><c:out value="${vehicule.numChassis}"/></td>
                                <td><c:out value="${vehicule.immatriculation}"/></td>
                                <td><c:out value="${vehicule.dateAchat}"/></td>
                                <td><c:out value="${vehicule.prixJournalier}"/></td>
                                <td><c:out value="${vehicule.actifVehicule}"/></td>
                                <td>
                                    <form action="<c:url value="/modifVehicule"/>" method="post">
                                        <input type="hidden" name="idModif" value="${vehicule.idVehicule}"/>
                                        <input type="hidden" name="idMarque" value="${vehicule.modelesByIdModele.marquesByIdMarque.idMarque}"/>
                                        <input type="hidden" name="modifFlag" value="true"/>
                                        <button class="btn-modif" name="idModif" type="submit" value="modifer">Modifer</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supVehicule"/>" method="post">
                                        <input type="hidden" name="idSup" value="${vehicule.idVehicule}"/>
                                        <button class="btn-sup" name="idSup" type="submit" value="supprimer">Supprimer</button>
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
