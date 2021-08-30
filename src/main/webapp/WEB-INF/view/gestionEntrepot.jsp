<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion entrepot</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Ajout d'entrepot</h2>

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

                <form action="<c:url value="/ajoutEntrepot"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Nom entrepôt : </label>
                        <input class="input-modif" type="text" name="nomEntrepot" value="${entrepot.nomEntrepot}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Nombre de place : </label>
                        <input class="input-modif" type="text" name="nombrePlace" value="${entrepot.nombrePlace}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Rue : </label>
                        <input class="input-modif" type="text" name="rue" value="${entrepot.adressesByIdAdresse.rue}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Numéro : </label>
                        <input class="input-modif" type="text" name="numero" value="${entrepot.adressesByIdAdresse.numero}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Boite : </label>
                        <input class="input-modif" type="text" name="boite" value="${entrepot.adressesByIdAdresse.boite}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Ville : </label>
                        <select class="select-modif" id="modif-ville" name="idVille" value="">
                            <option value="" disabled selected>Choisissez une ville</option>
                            <c:forEach var="villeList" items="${villeList}">
                                <option value="${villeList.idVille}" <c:if test="${ville.idVille == villeList.idVille}">selected</c:if>>${villeList.codePostal} - ${villeList.nomVille}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" value="Envoyer" id="bouton-modif" class="btn-modif2">Ajouter</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Liste des entrepots</h2>

                <div class="content-global-overflow">
                    <table class="table-custom">
                        <thead>
                            <th scope="col">Nom entrepot</th>
                            <th scope="col">Nombre place</th>
                            <th scope="col">Rue</th>
                            <th scope="col">Numéro</th>
                            <th scope="col">Boite</th>
                            <th scope="col">Code postal</th>
                            <th scope="col">Ville</th>
                            <th scope="col">Actif</th>
                            <th scope="col">Modification</th>
                            <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                            <c:forEach var="entrepot" items="${entrepotList}">
                            <tr>
                                <td><c:out value="${entrepot.nomEntrepot}"/></td>
                                <td><c:out value="${entrepot.nombrePlace}"/></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.rue}"/></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.numero}"/></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.boite}"/></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.villesByIdVille.codePostal}"/></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.villesByIdVille.nomVille}"/></td>
                                <td><c:out value="${entrepot.actifEntrepot}"/></td>

                                <td>
                                    <form action="<c:url value="/modifEntrepot"/>" method="post">
                                        <input type="hidden" name="idModif" value="${entrepot.idEntrepot}"/>
                                        <input type="hidden" name="idVille" value="${entrepot.adressesByIdAdresse.villesByIdVille.idVille}"/>
                                        <button class="btn-modif" name="idModif" type="submit">Modifier</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supEntrepot"/>" method="post">
                                        <input type="hidden" name="idSup" value="${entrepot.idEntrepot}"/>
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
