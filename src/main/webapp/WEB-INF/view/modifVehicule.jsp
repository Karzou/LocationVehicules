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

                <div>
                    <c:if test="${(sessionScope.errMessage1 != null) or (sessionScope.errMessage2 != null) or (sessionScope.errMessage3 != null) or (sessionScope.errMessage4 != null) or (sessionScope.errMessage5 != null) or (sessionScope.errMessage6 != null)}">
                        <div class="error-message-admin">
                            <div class="error-message-admin-title">
                                <div class="error-message-admin-title-img">
                                    <img src="${pageContext.request.contextPath}/images/error.png" alt="error" />
                                </div>
                                <div class="error-message-admin-title-txt">
                                    <p>Une erreur est survenue</p>
                                </div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage1}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage2}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage3}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage4}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage5}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage6}</div>
                            </div>
                        </div>
                        <c:remove var="errMessage1" scope="session" />
                        <c:remove var="errMessage2" scope="session" />
                        <c:remove var="errMessage3" scope="session" />
                        <c:remove var="errMessage4" scope="session" />
                        <c:remove var="errMessage5" scope="session" />
                        <c:remove var="errMessage6" scope="session" />
                    </c:if>
                </div>

                <form name="modifVehicule" action="<c:url value="/gestionVehicule"/>" method="post" onsubmit="return validateModifVehicule()">
                    <div class="div-input-modif">
                        <label class="label-input">Marque</label>
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
                        <label class="label-input">Modèle</label>
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
                        <label class="label-input">Cylindrée</label>
                        <input class="input-modif" type="text" name="cylindree" id="cylindree-modif-input" value="${vehicule.cylindree}">
                        <div class="span-error-div"><span class="span-error4" id="errorCylindreeModif"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Puissance</label>
                        <input class="input-modif" type="text" name="puissance" id="puissance-modif-input" value="${vehicule.puissance}">
                        <div class="span-error-div"><span class="span-error4" id="errorPuissanceModif"></span></div>
                    </div>
                    <div class="div-input-modif2">
                        <div class="div-input-left"><label class="label-input">Option</label></div>
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
                        <label class="label-input">Numéro de chassis</label>
                        <input class="input-modif" type="text" name="numChassis" id="numChassis-modif-input" value="${vehicule.numChassis}">
                        <div class="span-error-div"><span class="span-error4" id="errorNumChassisModif"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Immatriculation</label>
                        <input class="input-modif" type="text" name="immatriculation" id="immatriculation-modif-input" value="${vehicule.immatriculation}">
                        <div class="span-error-div"><span class="span-error4" id="errorImmatriculationModif"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Date achat</label>
                        <input class="input-modif" type="date" name="dateAchat" id="dateAchat-modif-input" value="${vehicule.dateAchat}">
                        <div class="span-error-div"><span class="span-error4" id="errorDateAchatModif"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Prix journalier</label>
                        <input class="input-modif" type="text" name="prixJournalier" id="prixJournalier-modif-input" value="${vehicule.prixJournalier}">
                        <div class="span-error-div"><span class="span-error4" id="errorPrixJournalierModif"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Couleur</label>
                        <select class="select-modif" name="idCouleur">
                            <c:forEach var="couleurList" items="${couleurList}">
                                <option value="${couleurList.idCouleur}" <c:if test="${vehicule.couleursByIdCouleur.idCouleur == couleurList.idCouleur}">selected</c:if>>${couleurList.nomCouleur}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Entrepot</label>
                        <select class="select-modif" name="idEntrepot">
                            <c:forEach var="entrepotList" items="${entrepotList}">
                                <c:if test="${entrepotList.actifEntrepot}">
                                    <option value="${entrepotList.idEntrepot}" <c:if test="${vehicule.entrepotsByIdEntrepot.idEntrepot == entrepotList.idEntrepot}">selected</c:if>>${entrepotList.nomEntrepot} >> ${entrepotList.adressesByIdAdresse.rue} ${entrepotList.adressesByIdAdresse.numero} - ${entrepotList.adressesByIdAdresse.villesByIdVille.codePostal} ${entrepotList.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Status</label>
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
