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

                <form action="<c:url value="/ajoutModele"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Marque : </label>
                        <select class="select-modif" id="modif-modele" name="idMarque" value="">
                            <c:forEach var="marque" items="${marqueList}">
                                <option value="${marque.idMarque}">${marque.nomMarque}</option>
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
                                        <button class="btn-modif" name="idModif" type="submit">Modifier</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/modifModele"/>" method="post">
                                        <input type="hidden" name="idModif" value="${marque.idMarque}"/>
                                        <button class="btn-modif" name="idModif" type="submit">Modifier</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supModele"/>" method="post">
                                        <select class="select-table" name="idSup" value="">
                                            <c:forEach var="modele" items="${modeleList}">
                                                <c:if test="${modele.marquesByIdMarque.idMarque == marque.idMarque}">
                                                    <option value="${modele.idModele}">${modele.nomModele}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                        <button class="btn-modif" name="idModif" type="submit">Supprimer</button>
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
