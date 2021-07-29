<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification véhicule</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification véhicule</h2>

                <form action="<c:url value="/gestionVehicule"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Marque : </label>
                        <select class="select-modif" name="idMarque" onChange="location.href='${pageContext.request.contextPath}/modifVehicule?idMarque='+this.value+'&idModif='+${vehicule.idVehicule};">
                            <c:forEach var="marqueList" items="${marqueList}">
                                <c:choose>
                                    <c:when test="${modifFlag == true}">
                                        <option value="${marqueList.idMarque}" <c:if test="${vehicule.modelesByIdModele.marquesByIdMarque.idMarque == marqueList.idMarque}">selected</c:if>>${marqueList.nomMarque}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${marqueList.idMarque}" <c:if test="${marque.idMarque == marqueList.idMarque}">selected</c:if>>${marqueList.nomMarque}</option>
                                    </c:otherwise>
                                </c:choose>
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
                                <input type="checkbox" class="input-checkbox" id="${optionVehiculesList.idOption}" value="${optionVehiculesList.idOption}" name="option${optionVehiculesList.idOption}" <c:forEach var="contientList" items="${contientList}"><c:if test="${optionVehiculesList.idOption == contientList.optionsVehiculesByIdOption.idOption && vehicule.idVehicule == contientList.vehiculesByIdVehicule.idVehicule}">checked</c:if></c:forEach>/>
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
                                <option value="${couleurList.idCouleur}" <c:if test="${vehicule.couleursByIdCouleur.idCouleur == couleurList.idCouleur}">selected</c:if>>${couleurList.nomCouleur}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Entrepot : </label>
                        <select class="select-modif" name="idEntrepot">
                            <c:forEach var="entrepotList" items="${entrepotList}">
                                <c:if test="${entrepotList.actifEntrepot}">
                                    <option value="${entrepotList.idEntrepot}" <c:if test="${vehicule.entrepotsByIdEntrepot.idEntrepot == entrepotList.idEntrepot}">selected</c:if>>${entrepotList.nomEntrepot} >> ${entrepotList.adressesByIdAdresse.rue} ${entrepotList.adressesByIdAdresse.numero} - ${entrepotList.adressesByIdAdresse.villesByIdVille.codePostal} ${entrepotList.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Status : </label>
                        <input type="checkbox" name="actifVehicule" id="actifVehicule" value="ok" <c:if test="${vehicule.actifVehicule}">checked</c:if>>
                        <label for="actifVehicule" class="label-checkbox">Actif</label>
                    </div>

                    <input type="hidden" name="idModif" value="${vehicule.idVehicule}"/>
                    <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
                    <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionVehicule">
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
