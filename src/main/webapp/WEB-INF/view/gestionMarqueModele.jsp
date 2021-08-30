<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion marque et modèle</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Ajout de marque</h2>

                <div>
                    <c:if test="${sessionScope.errMessage1 != null}">
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
                        </div>
                        <c:remove var="errMessage1" scope="session" />
                    </c:if>
                </div>

                <div>
                    <c:if test="${sessionScope.succMessage1 != null}">
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
                                <div>${succMessage1}</div>
                            </div>
                        </div>
                        <c:remove var="succMessage1" scope="session" />
                    </c:if>
                </div>

                <form action="<c:url value="/ajoutMarque"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Marque : </label>
                        <input class="input-modif" type="text" name="nomMarque">
                    </div>

                    <button type="submit" value="Envoyer" class="btn-modif2">Ajouter</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Ajout de modèle</h2>

                <div>
                    <c:if test="${(sessionScope.errMessage2 != null) or (sessionScope.errMessage3 != null)}">
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
                                <div>${errMessage2}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage3}</div>
                            </div>
                        </div>
                        <c:remove var="errMessage2" scope="session" />
                        <c:remove var="errMessage3" scope="session" />
                    </c:if>
                </div>

                <div>
                    <c:if test="${sessionScope.succMessage2 != null}">
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
                                <div>${succMessage2}</div>
                            </div>
                        </div>
                        <c:remove var="succMessage2" scope="session" />
                    </c:if>
                </div>

                <form action="<c:url value="/ajoutModele"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Marque : </label>
                        <select class="select-modif" name="idMarque">
                            <option value="" disabled selected>Choisissez une marque</option>
                            <c:forEach var="marqueList" items="${marqueList}">
                                <option value="${marqueList.idMarque}">${marqueList.nomMarque}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Modèle : </label>
                        <input class="input-modif" type="text" name="nomModele">
                    </div>

                    <button type="submit" value="Envoyer" class="btn-modif2">Ajouter</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Liste des marques et modèles</h2>

                <div>
                    <c:if test="${sessionScope.errMessage4 != null}">
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
                                <div>${errMessage4}</div>
                            </div>
                        </div>
                        <c:remove var="errMessage4" scope="session" />
                    </c:if>
                </div>

                <div>
                    <c:if test="${sessionScope.succMessage3 != null}">
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
                                <div>${succMessage3}</div>
                            </div>
                        </div>
                        <c:remove var="succMessage3" scope="session" />
                    </c:if>
                </div>

                <div class="content-global-overflow">
                    <table class="table-custom">
                        <thead>
                            <th scope="col">Marque</th>
                            <th scope="col">Modèle</th>
                            <th scope="col">Modification Marque</th>
                            <th scope="col">Modification Modèle</th>
                            <th scope="col">Supprimer Modèle</th>
                        </thead>

                        <tbody>
                            <c:forEach var="marque" items="${marqueList}">
                            <tr>
                                <td><c:out value="${marque.nomMarque}"/></td>
                                <td>
                                    <c:forEach var="modele" items="${modeleList}">
                                        <c:if test="${modele.marquesByIdMarque.idMarque == marque.idMarque}">
                                            <c:out value="${modele.nomModele}"/>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <form action="<c:url value="/modifMarque"/>" method="post">
                                        <input type="hidden" name="idModif" value="${marque.idMarque}"/>
                                        <button type="submit" class="btn-modif" name="idModif">Modifier</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/modifModele"/>" method="post">
                                        <input type="hidden" name="idModif" value="${marque.idMarque}"/>
                                        <button type="submit" class="btn-modif" name="idModif">Modifier</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supModele"/>" method="post">
                                        <select class="select-table" name="idSup" value="">
                                            <c:set var="count" value="0"/>
                                            <c:forEach var="modele" items="${modeleList}">
                                                <c:if test="${modele.marquesByIdMarque.idMarque == marque.idMarque}">
                                                    <option value="${modele.idModele}">${modele.nomModele}</option>
                                                    <c:set var="count" value="${count + 1}"/>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                        <button type="submit" class="btn-modif" name="idModif" <c:if test="${count == 0}">disabled</c:if>>Supprimer</button>
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
