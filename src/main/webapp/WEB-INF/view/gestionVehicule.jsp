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

                <form action="<c:url value="/ajoutVehicule"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Marque : </label>
                        <select class="select-modif" name="idMarque" onChange="location.href='${pageContext.request.contextPath}/gestionVehicule?idMarque='+this.value+'&newVehicleFlag=true';">
                            <option value="" <c:if test="${newVehicleFlag == false}">selected</c:if> disabled>Choisissez une marque</option>
                            <c:forEach var="marqueList" items="${marqueList}">
                                <option value="${marqueList.idMarque}" <c:if test="${newVehicleFlag == true && marqueList.idMarque == marque.idMarque}">selected</c:if>>${marqueList.nomMarque}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Modèle : </label>
                        <select class="select-modif" name="idModele">
                            <c:forEach var="modeleList" items="${modeleList}">
                                <c:choose>
                                    <c:when test="${modifFlag == true}">
                                        <c:if test="${vehicule.modelesByIdModele.marquesByIdMarque.idMarque == modeleList.marquesByIdMarque.idMarque}">
                                            <option value="${modeleList.idModele}" <c:if test="${vehicule.modelesByIdModele.idModele == modeleList.idModele}">selected</c:if>>${modeleList.nomModele}</option>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${marque.idMarque == modeleList.marquesByIdMarque.idMarque}">
                                            <option value="${modeleList.idModele}" <c:if test="${vehicule.modelesByIdModele.idModele == modeleList.idModele}">selected</c:if>>${modeleList.nomModele}</option>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Cylindrée : </label>
                        <input class="input-modif" type="text" name="cylindree" value="${vehicule.cylindree}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Puissance: </label>
                        <input class="input-modif" type="text" name="puissance" value="${vehicule.puissance}">
                    </div>
                    <div class="div-input-modif2">
                        <div class="div-input-left"><label class="label-input">Option: </label></div>
                        <div class="div-input-right">
                            <c:forEach var="optionVehiculesList" items="${optionVehiculesList}">
                                <div class="div-input-checkbox">
                                    <input type="checkbox" class="input-checkbox" id="${optionVehiculesList.idOption}" value="${optionVehiculesList.idOption}" name="option${optionVehiculesList.idOption}"/>
                                    <label class="input-checkbox" for="${optionVehiculesList.idOption}">${optionVehiculesList.nomOption}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Numéro de chassis: </label>
                        <input class="input-modif" type="text" name="numChassis" value="${vehicule.numChassis}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Immatriculation : </label>
                        <input class="input-modif" type="text" name="immatriculation" value="${vehicule.immatriculation}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Date achat : </label>
                        <input class="input-modif" type="date" name="dateAchat" value="${vehicule.dateAchat}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Prix journalier : </label>
                        <input class="input-modif" type="text" name="prixJournalier" value="${vehicule.prixJournalier}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Couleur : </label>
                        <select class="select-modif" name="idCouleur">
                            <c:forEach var="couleurList" items="${couleurList}">
                                <option value="${couleurList.idCouleur}">${couleurList.nomCouleur}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Entrepot : </label>
                        <select class="select-modif" name="idEntrepot">
                            <c:forEach var="entrepotList" items="${entrepotList}">
                                <c:if test="${entrepotList.actifEntrepot}">
                                    <option value="${entrepotList.idEntrepot}">${entrepotList.nomEntrepot} >> ${entrepotList.adressesByIdAdresse.rue} ${entrepotList.adressesByIdAdresse.numero} - ${entrepotList.adressesByIdAdresse.villesByIdVille.codePostal} ${entrepotList.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn-modif2" value="Envoyer" id="bouton-modif">Enregistrer</button>
                </form>
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
