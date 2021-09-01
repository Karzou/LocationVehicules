<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification couleur</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification couleur</h2>

                <div>
                    <c:if test="${sessionScope.errMessage != null}">
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
                                <div>${errMessage}</div>
                            </div>
                        </div>
                        <c:remove var="errMessage" scope="session" />
                    </c:if>
                </div>

                <form name="modifCouleur" action="<c:url value="/gestionCouleur"/>" method="post" onsubmit="return validateModifCouleur()">
                    <div class="div-input-modif">
                        <label class="label-input">Nom couleur</label>
                        <input class="input-modif" type="text" name="nomCouleur" id="couleur-modif-input" value="${couleur.nomCouleur}">
                        <div class="span-error-div"><span class="span-error4" id="errorCouleurModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Status</label>
                        <input type="checkbox" name="actifCouleur" id="actifCouleur" value="ok" <c:if test="${couleur.actifCouleur}">checked</c:if>>
                        <label for="actifCouleur" class="label-checkbox">Actif</label>
                    </div>

                    <input type="hidden" name="idModif" value="${couleur.idCouleur}"/>
                    <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
                    <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionCouleur">
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
