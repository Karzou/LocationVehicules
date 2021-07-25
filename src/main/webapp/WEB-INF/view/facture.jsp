<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <title>Facture</title>
    <c:import url="head.jsp"/>
</head>

<body class="body">
<div class="container">
    <c:import url="menu.jsp"/>

        <div class="content1">
    <div class="head">
        <img class="img-home" src="${pageContext.request.contextPath}/images/home.png" alt="home" />

        <div class="text">
            <h1>Vos factures</h1>
        </div>
    </div>
        <div class="content-global">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Factures</th>
                    <th scope="col">Date facture</th>
                    <th scope="col">Prix</th>

                    <th scope="col">Contrat</th>
                    <th scope="col">Date de départ</th>
                    <th scope="col">Date de retour</th>
                    <th scope="col">pdf</th>
                </tr>
                </thead>

                <tbody>
                <%--création d'une variable temporaire nommée "facture" (uniquement présente dans le foreach)--%>
                <%--items="${factureList} provient de FactureServlet => request.setAttribute("factureList", factureList);--%>
                <c:forEach var="facture" items="${factureList}">
                <tr>

                    <td><c:out value="${facture.idFacture}"/></td>

                </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

        </div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
