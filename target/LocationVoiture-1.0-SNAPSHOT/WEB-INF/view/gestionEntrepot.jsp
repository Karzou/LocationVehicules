<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion Vehicule</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body class="body">
        <div class="container">
            <jsp:include page="menu.jsp" />

            <div class="content-vehicle">
                <h1>Liste des entrepots</h1>

                <div>
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
                                <td><c:out value="${entrepot.nomEntrepot}"></c:out></td>
                                <td><c:out value="${entrepot.nombrePlace}"></c:out></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.rue}"></c:out></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.numero}"></c:out></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.boite}"></c:out></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.villesByIdVille.codePostal}"></c:out></td>
                                <td><c:out value="${entrepot.adressesByIdAdresse.villesByIdVille.nomVille}"></c:out></td>
                                <td><c:out value="${entrepot.actifEntrepot}"></c:out></td>

                                <td>
                                    <form action="<c:url value="/modifEntrepot"/>" method="post">
                                        <input type="hidden" name="idModif" value="${entrepot.idEntrepot}"></input>
                                        <button class="btn-modif" name="idModif" type="submit" value="modifer">Modifer</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="<c:url value="/supEntrepot"/>" method="post">
                                        <input type="hidden" name="idSup" value="${entrepot.idEntrepot}"></input>
                                        <button class="btn-sup" name="idSup" type="submit" value="supprimer">Supprimer</button>
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
