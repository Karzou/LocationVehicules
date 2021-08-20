<%--
  Created by IntelliJ IDEA.
  User: jedescha
  Date: 10-08-21
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modification Facture</title>
    <c:import url="head.jsp"/>
</head>

<body class="body">
<div class="container">
    <c:import url="menu.jsp"/>

    <div class="content-global">
        <h2>Modification Facture</h2>


        <form action="<c:url value="/modifFacture"/>" method="post">

            <!-- Le champ numéro de facture est en readonly-->
            <div class="div-input-modif">
                <label class="label-input">Numéro Facture : </label>
                <input class="input-modif" type="int" name="idFacture" value="${facture.idFacture}" readonly>
            </div>
            <div class="div-input-modif">
                <label class="label-input">Date Facture : </label>
                <input class="input-modif" type="date" name="dateFacture" value="${facture.formatDateFacture()}">
            </div>
            <div class="div-input-modif">
                <label class="label-input">Prix Facture : </label>
                <input class="input-modif" type="text" name="prixFacture" value="${facture.prixFacture}">
            </div>

            <input type="hidden" name="idModif" value="${facture.idFacture}">
            <input type="hidden" name="flagModifFActure" value="true">
            <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
            <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionFacture">
        </form>
    </div>

    <c:import url="footer.jsp"/>
</div>
</body>
</html>

