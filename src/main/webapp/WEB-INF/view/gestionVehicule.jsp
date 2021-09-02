<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Gestion véhicule</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Ajout de véhicule</h2>

                <div>
                    <c:if test="${(sessionScope.errMessage1 != null) or (sessionScope.errMessage2 != null) or (sessionScope.errMessage3 != null) or (sessionScope.errMessage4 != null) or (sessionScope.errMessage5 != null) or (sessionScope.errMessage6 != null) or (sessionScope.errMessage7 != null) or (sessionScope.errMessage8 != null) or (sessionScope.errMessage9 != null) or (sessionScope.errMessage10 != null)}">
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
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage7}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage8}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage9}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage10}</div>
                            </div>
                        </div>
                        <c:remove var="errMessage1" scope="session" />
                        <c:remove var="errMessage2" scope="session" />
                        <c:remove var="errMessage3" scope="session" />
                        <c:remove var="errMessage4" scope="session" />
                        <c:remove var="errMessage5" scope="session" />
                        <c:remove var="errMessage6" scope="session" />
                        <c:remove var="errMessage7" scope="session" />
                        <c:remove var="errMessage8" scope="session" />
                        <c:remove var="errMessage9" scope="session" />
                        <c:remove var="errMessage10" scope="session" />
                    </c:if>
                </div>

                <div>
                    <c:if test="${sessionScope.succMessage != null}">
                        <div class="success-message-admin">
                            <div class="success-message-admin-title">
                                <div class="success-message-admin-title-img">
                                    <img src="${pageContext.request.contextPath}/images/success.png" alt="success" />
                                </div>
                                <div class="success-message-admin-title-txt">
                                    <p>Félicitation</p>
                                </div>
                            </div>
                            <div class="success-message-admin-body-txt">
                                <div>${succMessage}</div>
                            </div>
                        </div>
                        <c:remove var="succMessage" scope="session" />
                    </c:if>
                </div>

                <form name="ajoutVehicule" action="<c:url value="/ajoutVehicule"/>" method="post" onsubmit="return validateAjoutVehicule()">
                    <div class="div-input-modif">
                        <div class="div-input-modif-select">
                            <label class="label-input">Marque</label>
                            <select class="select-modif" name="idMarque" id="marque-ajout-input" onChange="location.href='${pageContext.request.contextPath}/gestionVehicule?idMarque='+this.value+'&newVehicleFlag=true';">
                                <option value="" <c:if test="${newVehicleFlag == false}">selected</c:if> disabled>Choisissez une marque</option>
                                <c:forEach var="marqueList" items="${marqueList}">
                                    <option value="${marqueList.idMarque}" <c:if test="${newVehicleFlag == true && marqueList.idMarque == marque.idMarque}">selected</c:if>>${marqueList.nomMarque}</option>
                                </c:forEach>
                            </select>
                            <div class="span-error-div"><span class="span-error4" id="errorMarqueAjout"></span></div>
                        </div>
                        <div class="div-input-modif-button">
                            <input type="button" class="btn-add" value="Ajout marque" onclick=location.href="${pageContext.request.contextPath}/gestionMarqueModele">
                        </div>
                    </div>
                    <div class="div-input-modif">
                        <div class="div-input-modif-select">
                            <label class="label-input">Modèle</label>
                            <select class="select-modif" name="idModele" id="modele-ajout-input">
                                <option value="" disabled selected>Choisissez un modèle</option>
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
                            <div class="span-error-div"><span class="span-error4" id="errorModeleAjout"></span></div>
                        </div>
                        <div class="div-input-modif-button">
                            <input type="button" class="btn-add" value="Ajout modèle" onclick=location.href="${pageContext.request.contextPath}/gestionMarqueModele">
                        </div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Cylindrée</label>
                        <input class="input-modif" type="text" name="cylindree" id="cylindree-ajout-input" value="${vehicule.cylindree}">
                        <div class="span-error-div"><span class="span-error4" id="errorCylindreeAjout"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Puissance</label>
                        <input class="input-modif" type="text" name="puissance" id="puissance-ajout-input" value="${vehicule.puissance}">
                        <div class="span-error-div"><span class="span-error4" id="errorPuissanceAjout"></span></div>
                    </div>
                    <div class="div-input-modif2">
                        <div class="div-input-left"><label class="label-input">Option</label></div>
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
                        <label class="label-input">Numéro de chassis</label>
                        <input class="input-modif" type="text" name="numChassis" id="numChassis-ajout-input" value="${vehicule.numChassis}">
                        <div class="span-error-div"><span class="span-error4" id="errorNumChassisAjout"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Immatriculation</label>
                        <input class="input-modif" type="text" name="immatriculation" id="immatriculation-ajout-input" value="${vehicule.immatriculation}">
                        <div class="span-error-div"><span class="span-error4" id="errorImmatriculationAjout"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Date achat</label>
                        <input class="input-modif" type="date" name="dateAchat" id="dateAchat-ajout-input" value="${vehicule.dateAchat}">
                        <div class="span-error-div"><span class="span-error4" id="errorDateAchatAjout"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Prix journalier</label>
                        <input class="input-modif" type="text" name="prixJournalier" id="prixJournalier-ajout-input" value="${vehicule.prixJournalier}">
                        <div class="span-error-div"><span class="span-error4" id="errorPrixJournalierAjout"></span></div>
                    </div>
                    <div class="div-input-modif">
                        <div class="div-input-modif-select">
                            <label class="label-input">Couleur</label>
                            <select class="select-modif" name="idCouleur" id="couleur-ajout-input">
                                <option value="" disabled selected>Choisissez une couleur</option>
                                <c:forEach var="couleurList" items="${couleurList}">
                                    <option value="${couleurList.idCouleur}">${couleurList.nomCouleur}</option>
                                </c:forEach>
                            </select>
                            <div class="span-error-div"><span class="span-error4" id="errorCouleurAjout"></span></div>
                        </div>
                        <div class="div-input-modif-button">
                            <input type="button" class="btn-add" value="Ajout couleur" onclick=location.href="${pageContext.request.contextPath}/gestionCouleur">
                        </div>
                    </div>
                    <div class="div-input-modif">
                        <div class="div-input-modif-select">
                            <label class="label-input">Entrepot</label>
                            <select class="select-modif" name="idEntrepot" id="entrepot-ajout-input">
                                <option value="" disabled selected>Choisissez un entrepôt</option>
                                <c:forEach var="entrepotList" items="${entrepotList}">
                                    <c:if test="${entrepotList.actifEntrepot}">
                                        <option value="${entrepotList.idEntrepot}">${entrepotList.nomEntrepot} >> ${entrepotList.adressesByIdAdresse.rue} ${entrepotList.adressesByIdAdresse.numero} - ${entrepotList.adressesByIdAdresse.villesByIdVille.codePostal} ${entrepotList.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <div class="span-error-div"><span class="span-error4" id="errorEntrepotAjout"></span></div>
                        </div>
                        <div class="div-input-modif-button">
                            <input type="button" class="btn-add" value="Ajout entrepot" onclick=location.href="${pageContext.request.contextPath}/gestionEntrepot">
                        </div>
                    </div>

                    <button type="submit" class="btn-modif2" value="Envoyer" id="bouton-modif">Enregistrer</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Liste des véhicules actif</h2>

                <div class="content-global-overflow">
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
                            <th scope="col">Modification</th>
                            <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                            <c:forEach var="vehicule" items="${vehiculeList}">
                            <tr>
                                <c:if test="${vehicule.actifVehicule}">
                                <td><c:out value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque}"/></td>
                                <td><c:out value="${vehicule.modelesByIdModele.nomModele}"/></td>
                                <td><c:out value="${vehicule.cylindree}"/></td>
                                <td><c:out value="${vehicule.puissance}"/></td>
                                <td><c:out value="${vehicule.numChassis}"/></td>
                                <td><c:out value="${vehicule.immatriculation}"/></td>
                                <td><c:out value="${vehicule.dateAchat}"/></td>
                                <td><c:out value="${vehicule.prixJournalier}"/></td>
                                <td>
                                    <form action="<c:url value="/modifVehicule"/>" method="post">
                                        <input type="hidden" name="idModif" value="${vehicule.idVehicule}"/>
                                        <input type="hidden" name="idMarque" value="${vehicule.modelesByIdModele.marquesByIdMarque.idMarque}"/>
                                        <input type="hidden" name="modifFlag" value="true"/>
                                        <input type="submit" class="btn-modif" value="Modifier"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supVehicule"/>" method="post">
                                        <input type="hidden" name="idSup" value="${vehicule.idVehicule}"/>
                                        <input type="submit" class="btn-sup" value="Désactiver"/>
                                    </form>
                                </td>
                                </c:if>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <c:if test="${sessionScope.menu eq 'admin'}">
                <h2>Liste des véhicules inactif</h2>

                <div class="content-global-overflow">
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
                        <th scope="col">Modification</th>
                        <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                        <c:forEach var="vehicule" items="${vehiculeList}">
                            <tr>
                                <c:if test="${!vehicule.actifVehicule}">
                                <td><c:out value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque}"/></td>
                                <td><c:out value="${vehicule.modelesByIdModele.nomModele}"/></td>
                                <td><c:out value="${vehicule.cylindree}"/></td>
                                <td><c:out value="${vehicule.puissance}"/></td>
                                <td><c:out value="${vehicule.numChassis}"/></td>
                                <td><c:out value="${vehicule.immatriculation}"/></td>
                                <td><c:out value="${vehicule.dateAchat}"/></td>
                                <td><c:out value="${vehicule.prixJournalier}"/></td>
                                <td>
                                    <form action="<c:url value="/modifVehicule"/>" method="post">
                                        <input type="hidden" name="idModif" value="${vehicule.idVehicule}"/>
                                        <input type="hidden" name="idMarque" value="${vehicule.modelesByIdModele.marquesByIdMarque.idMarque}"/>
                                        <input type="hidden" name="modifFlag" value="true"/>
                                        <input type="submit" class="btn-modif" value="Modifier"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/reactiveVehicule"/>" method="post">
                                        <input type="hidden" name="idSup" value="${vehicule.idVehicule}"/>
                                        <input type="submit" class="btn-sup" value="Réactiver"/>
                                    </form>
                                </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
