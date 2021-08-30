<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <div class="content-global-overflow">
            <table class="table-custom">
                <thead>
                <th scope="col">N� de facture</th>
                <th scope="col">Nom</th>
                <th scope="col">Pr�nom</th>
                <th scope="col">Date facture</th>
                <th scope="col">Prix facture ( &euro; ) </th>
                <th scope="col">Pdf facture</th>
                <th scope="col">Modification facture</th>

                </thead>

                <tbody>
                <%--cr�ation d'une variable temporaire nomm�e "facture" (uniquement pr�sente dans le foreach)--%>
                <%--items="${factureList} provient de FactureServlet => request.setAttribute("factureList", factureList);--%>
                <c:forEach var="facture" items="${factureList}">

                <tr>
                            <td><c:out value="${facture.idFacture}"/></td>

                            <td><c:out value="${facture.getContratsByIdContrat().getReservation().getUtilisateursByIdUtilisateur().getNomUtilisateur()}"></c:out></td>
                            <td><c:out value="${facture.getContratsByIdContrat().getReservation().getUtilisateursByIdUtilisateur().getPrenomUtilisateur()}"></c:out></td>
                            <td><c:out value="${facture.dateFacture}"/></td>
                            <td><c:out value="${facture.prixFacture}"/></td>
                            <td class="align-center">
                                <a  target="_blank" href="${pageContext.request.contextPath}/pdf?type=facture&factureId=${facture.getIdFacture()}">
                                 <img class="pdfLogo-content-img" src="${pageContext.request.contextPath}/images/pdfLogo.png" alt="logo"/>
                                 </a>
                            </td>
                            <td class="align-center">
                                <form action="<c:url value="/modifFacture"/>" method="post">
                                   <input type="hidden" name="idModif" value="${facture.idFacture}"/>
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