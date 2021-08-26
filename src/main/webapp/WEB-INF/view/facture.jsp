<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <title>Facture</title>
    <c:import url="head.jsp"/>
    <script type="text/javascript" src="<c:url value="/js/facture.js"/>"></script>
</head>

<body class="body">
    <div class="container">
        <c:import url="menu.jsp"/>

        <div class="content-global">
            <h2>Mes contrats et factures</h2>

            <table class="table-custom">
                <thead>
                    <tr>
                        <th scope="col">Date de d�part</th>
                        <th scope="col">Date de retour</th>
                        <th scope="col">v�hicule</th>
                        <th scope="col">Prix</th>
                        <th scope="col">Etat</th>
                        <th scope="col">N� contrat</th>
                        <th scope="col">Pdf Contrat</th>
                        <th scope="col">N� facture</th>
                        <th scope="col">Pdf facture</th>
                    </tr>
                </thead>

                <tbody>
                <%--cr�ation d'une variable temporaire nomm�e "facture" (uniquement pr�sente dans le foreach)--%>
                <%--items="${factureList} provient de FactureServlet => request.setAttribute("factureList", factureList);--%>
                <c:forEach var="facture" items="${factureList}">
                    <c:choose>
                        <c:when test="${facture.getContratsByIdContrat().getReservationsByIdContrat().size()>0}">
                            <c:forEach var="reservation" items="${facture.getContratsByIdContrat().getReservationsByIdContrat()}">
                                <tr>
                                    <td><c:out value="${reservation.getDateDebutLocation().toString()}"/></td>
                                    <td><c:out value="${reservation.getDateFinLocation().toString()}"/></td>
                                    <td><c:out value="${reservation.getVehiculesByIdVehicule().getModelesByIdModele().getNomModele()}"/></td>
                                    <td><c:out value="${facture.prixFacture}"/></td>
                                    <td><c:out value="${facture.getContratsByIdContrat().getEtat().toString()}"/></td>
                                    <td><c:out value="${facture.getContratsByIdContrat().getIdContrat()}"/></td>
                                    <td class="align-center">
                                        <a target="_blank" href="${pageContext.request.contextPath}/pdf?type=contrat&contratId=${facture.getContratsByIdContrat().getIdContrat()}">
                                        <img class="pdfLogo-content-img" src="${pageContext.request.contextPath}/images/pdfLogo.png" alt="logo"/>
                                        </a>
                                    </td>
                                    <td><c:out value="${facture.idFacture}"/></td>
                                    <td class="align-center">
                                        <a target="_blank" href="${pageContext.request.contextPath}/pdf?type=facture&factureId=${facture.getIdFacture()}">
                                            <img class="pdfLogo-content-img" src="${pageContext.request.contextPath}/images/pdfLogo.png" alt="logo"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><c:out value="${facture.prixFacture}"/></td>
                                <td><c:out value="${facture.getContratsByIdContrat().getEtat().toString()}"/></td>
                                <td><c:out value="${facture.getContratsByIdContrat().getIdContrat()}"/></td>
                                <td><a target="_blank" href="${pageContext.request.contextPath}/pdf?type=contrat&contratId=${facture.getContratsByIdContrat().getIdContrat()}">pdf</a></td>
                                <td><c:out value="${facture.idFacture}"/></td>
                                <td > <a target="_blank" href="${pageContext.request.contextPath}/pdf?type=facture&factureId=${facture.getIdFacture()}">pdf</a></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
    </div>
    <c:import url="footer.jsp"/>
    </body>
</html>
