<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification Entrepot</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification entrepot</h2>

                <div>
                    <c:if test="${(sessionScope.errMessage1 != null) or (sessionScope.errMessage2 != null) or (sessionScope.errMessage3 != null) or (sessionScope.errMessage4 != null) or (sessionScope.errMessage5 != null)}">
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
                        </div>
                        <c:remove var="errMessage1" scope="session" />
                        <c:remove var="errMessage2" scope="session" />
                        <c:remove var="errMessage3" scope="session" />
                        <c:remove var="errMessage4" scope="session" />
                        <c:remove var="errMessage5" scope="session" />
                    </c:if>
                </div>

                <form action="<c:url value="/gestionEntrepot"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Nom entrep�t : </label>
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
                        <label class="label-input">Num�ro : </label>
                        <input class="input-modif" type="text" name="numero" value="${entrepot.adressesByIdAdresse.numero}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Boite : </label>
                        <input class="input-modif" type="text" name="boite" value="${entrepot.adressesByIdAdresse.boite}">
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Ville : </label>
                        <select class="select-modif" id="modif-ville" name="idVille" value="">
                            <c:forEach var="villeList" items="${villeList}">
                                <option value="${villeList.idVille}" <c:if test="${ville.idVille == villeList.idVille}">selected</c:if>>${villeList.codePostal} - ${villeList.nomVille}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Status : </label>
                        <input type="checkbox" name="actifEntrepot" id="actifEntrepot" value="ok" <c:if test="${entrepot.actifEntrepot}">checked</c:if>>
                        <label for="actifEntrepot" class="label-checkbox">Actif</label>
                    </div>

                    <input type="hidden" name="idModif" value="${entrepot.idEntrepot}">
                    <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
                    <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionEntrepot">
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
