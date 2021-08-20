<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Gestion facture</title>
    <c:import url="head.jsp"/>
</head>

<body class="body">
<div class="container">
    <c:import url="menu.jsp"/>


    <div class="content-global">
        <h2>Liste des Factures</h2>

        <div>
            <table class="table-custom">
                <thead>
                <th scope="col">N° de facture</th>
                <th>Nom</th>
                <th scope="col">Date facture</th>
                <th scope="col">Prix facture</th>
                <th scope="col">Modification facture</th>

                </thead>

                <tbody>
                <%--création d'une variable temporaire nommée "facture" (uniquement présente dans le foreach)--%>
                <%--items="${factureList} provient de FactureServlet => request.setAttribute("factureList", factureList);--%>
                <c:forEach var="facture" items="${factureList}">
                <c:forEach var="contrat" items="${facture.getContratsByIdContrat().getReservationsByIdContrat()}">

                <tr>
                            <td><c:out value="${facture.idFacture}"/></td>

                            <td><c:out value="${contrat.getUtilisateursByIdUtilisateur().getNomUtilisateur()}"></c:out></td>
                            <td><c:out value="${facture.dateFacture}"/></td>
                            <td><c:out value="${facture.prixFacture}"/></td>
                            <td>
                                <form action="<c:url value="/modifFacture"/>" method="post">
                                    <input type="hidden" name="idModif" value="${facture.idFacture}"/>
                                    <button type="submit" class="btn-modif" name="idModif">Modifier</button>
                                </form>
                            </td>

                </tr>
                </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <c:import url="footer.jsp"/>
</div>
</body>
</html>