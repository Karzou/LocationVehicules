<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion couleur</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Ajout de couleur</h2>

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

                <form action="<c:url value="/ajoutCouleur"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Nom couleur : </label>
                        <input class="input-modif" type="text" name="nomCouleur" value="">
                    </div>

                    <button type="submit" value="Envoyer" id="bouton-modif" class="btn-modif2">Ajouter</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Liste des couleurs</h2>

                <div class="content-global-overflow">
                    <table class="table-custom">
                        <thead>
                            <th scope="col">Nom couleur</th>
                            <th scope="col">Actif</th>
                            <th scope="col">Modification</th>
                            <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                            <c:forEach var="couleur" items="${couleurList}">
                            <tr>
                                <td><c:out value="${couleur.nomCouleur}"/></td>
                                <td><c:out value="${couleur.actifCouleur}"/></td>

                                <td>
                                    <form action="<c:url value="/modifCouleur"/>" method="post">
                                        <input type="hidden" name="idModif" value="${couleur.idCouleur}"/>
                                        <button class="btn-modif" name="idModif" type="submit">Modifier</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supCouleur"/>" method="post">
                                        <input type="hidden" name="idSup" value="${couleur.idCouleur}"/>
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
