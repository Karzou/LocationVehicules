<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification modèle</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification modèle</h2>

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

                <form name="modifModele" action="<c:url value="/gestionMarqueModele"/>" method="post" onsubmit="return validateModifModele()">
                    <div class="div-input-modif">
                        <label class="label-input">Modèle à modifier</label>
                        <select class="select-modif" id="modif-modele" name="idModif" value="">
                            <c:forEach var="modele" items="${modeleList}">
                                <c:if test="${modele.marquesByIdMarque.idMarque == marque.idMarque}">
                                    <option value="${modele.idModele}">${modele.nomModele}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Remplacer par</label>
                        <input class="input-modif" type="text" name="nomModele" id="modele-modif-input">
                        <div class="span-error-div"><span class="span-error4" id="errorModeleModif"></span></div>
                    </div>

                    <input type="hidden" name="idMarque" value="${marque.idMarque}">
                    <input type="hidden" name="flagModifMarque" value="false">
                    <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
                    <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionMarqueModele">
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
