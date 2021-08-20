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
    <title>Modification Contrat</title>
    <c:import url="head.jsp"/>
</head>

<body class="body">
<div class="container">
    <c:import url="menu.jsp"/>

    <div class="content-global">
        <h2>Modification Contrat</h2>


        <form action="<c:url value="/modifContrat"/>" method="post">

            <!-- Le champ numéro de facture est en readonly-->
            <div class="div-input-modif">
                <label class="label-input">Numéro Contrat : </label>
                <input class="input-modif" type="int" name="idContrat" value="${contract.getIdContrat()}" readonly>
            </div>

            <div class="div-input-modif">
                <label class="label-input">Acompte : </label>
                <input class="input-modif" type="text" name="acompte" value="${contract.getAcompte()}">
            </div>


            <input type="hidden" name="idModif" value="${contract.idContrat}">
            <input type="hidden" name="flagModifFActure" value="true">
            <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
            <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionContrat">
        </form>
    </div>

    <c:import url="footer.jsp"/>
</div>
</body>
</html>
