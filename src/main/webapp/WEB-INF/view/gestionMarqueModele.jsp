<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion marque et mod�le</title>
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
                <h2>Ajout de mod�le</h2>

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
                        <label class="label-input">Mod�le : </label>
                        <input class="input-modif" type="text" name="nomModele">
                    </div>

                    <button type="submit" value="Envoyer" class="btn-modif2">Ajouter</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Liste des marques et mod�les</h2>

                <div>
                    <table class="table-custom">
                        <thead>
                            <th scope="col">Marque</th>
                            <th scope="col">Mod�le</th>
                            <th scope="col">Modification Marque</th>
                            <th scope="col">Modification Mod�le</th>
                            <th scope="col">Supprimer Mod�le</th>
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
