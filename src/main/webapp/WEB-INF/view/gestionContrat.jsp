<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Gestion contrat</title>
    <c:import url="head.jsp"/>
</head>

<body class="body">
<div class="container">
    <c:import url="menu.jsp"/>


    <div class="content-global">
        <h2>Liste des Contrats</h2>

        <div>
            <table class="table-custom">
                <thead>
                <th scope="col">N° de contrat</th>
                <th scope="col">Nom</th>
                <th scope="col">Prénom</th>
                <th scope="col">Acompte</th>
                <th scope="col">Caution</th>
                <th scope="col">Etat</th>
                <th scope="col">Pdf Contrat</th>
                <th scope="col">Modification Contrat</th>

                </thead>

                <tbody>
                <%--création d'une variable temporaire nommée "contrat" (uniquement présente dans le foreach)--%>
                <%--items="${contratList} provient de ContratServlet => request.setAttribute("contratList", contratList);--%>
                <c:forEach var="contrat" items="${contratList}">
                    <tr>
                        <td><c:out value="${contrat.getIdContrat()}"/></td>
                        <td><c:out
                                value="${contrat.getReservation().getUtilisateursByIdUtilisateur().getNomUtilisateur()}"></c:out></td>
                        <td><c:out
                                value="${contrat.getReservation().getUtilisateursByIdUtilisateur().getPrenomUtilisateur()}"></c:out></td>
                        <td><c:out value="${contrat.getAcompte()}"/></td>
                        <td><c:out value="${contrat.getCaution()}"/></td>
                        <td><c:out value="${contrat.getEtat().toString()}"/></td>
                        <td class="align-center">
                            <a target="_blank"
                               href="${pageContext.request.contextPath}/pdf?type=contrat&contratId=${contrat.getIdContrat()}">
                                <img class="pdfLogo-content-img"
                                     src="${pageContext.request.contextPath}/images/pdfLogo.png" alt="logo"/>
                            </a>
                        </td>
                        <td class="align-center">
                            <form action="<c:url value="/modifContrat"/>" method="post">
                                <input type="hidden" name="idModif" value="${contrat.getIdContrat()}"/>
                                <button type="submit" class="btn-modif" name="idModif">Modifier</button>
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
