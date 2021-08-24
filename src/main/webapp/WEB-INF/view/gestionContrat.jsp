<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
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
                <th scope="col">Acompte</th>
                <th scope="col">Caution</th>
                <th scope="col">Etat</th>
                <th scope="col">Modification Contrat</th>

                </thead>

                <tbody>
                <%--création d'une variable temporaire nommée "facture" (uniquement présente dans le foreach)--%>
                <%--items="${factureList} provient de FactureServlet => request.setAttribute("factureList", factureList);--%>
                <c:forEach var="contrat" items="${contratList}">

                    <tr>
                        <td><c:out value="${contrat.getIdContrat()}"/></td>
                        <td><c:out value="${contrat.getAcompte()}"/></td>
                        <td><c:out value="${contrat.getCaution()}"/></td>
                        <td><c:out value="${contrat.getEtat().toString()}"/></td>
                        <td>
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
