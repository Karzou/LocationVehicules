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

        <div>
            <c:if test="${(sessionScope.errMessage1 != null) or (sessionScope.errMessage2 != null)}">
                <div class="error-message-admin">
                    <div class="error-message-admin-title">
                        <div class="error-message-admin-title-img">
                            <img src="${pageContext.request.contextPath}/images/error.png" alt="error" />
                        </div>
                        <div class="error-message-admin-title-txt">
                            <p>Une erreur est survenue</p>
                        </div>
                    </div>
                    <div class="error-message-admin-body-txt">
                        <div>${sessionScope.errMessage1}</div>
                    </div>
                    <div class="error-message-admin-body-txt">
                        <div>${sessionScope.errMessage2}</div>
                    </div>

                </div>
                <c:remove var="errMessage1" scope="session" />
                <c:remove var="errMessage2" scope="session" />

            </c:if>
        </div>


        <form action="<c:url value="/gestionContrat"/>" method="post">

            <!-- Le champ numéro de facture est en readonly-->
            <fieldset disabled>
                <legend><b>   Données non modifiables   </b></legend>
                <br />
            <div class="div-input-modif">
                <label class="label-input">Numéro Contrat : </label>
                <input class="input-modif" type="int" name="idContrat" value="${contrat.getIdContrat()}" readonly>
            </div>
            <div class="div-input-modif">
                <label class="label-input">Nom et prénom : </label>
                <input class="input-modif" type="text" name="idFacture" value="${contrat.getReservationsByIdContrat().get(0).getUtilisateursByIdUtilisateur().getNomUtilisateur()} ${contrat.getReservationsByIdContrat().get(0).getUtilisateursByIdUtilisateur().getPrenomUtilisateur()}" readonly>
            </div>

            </fieldset>
            <br />
            <br />

            <fieldset>
                <legend><b>   Données modifiables   </b></legend>
                <br />
            <div class="div-input-modif">
                <label class="label-input">Acompte : </label>
                <input class="input-modif" type="text" name="acompte" value="${contrat.getAcompte()}">
            </div>

            <div class="div-input-modif">
                <label class="label-input">Caution : </label>
                <input class="input-modif" type="text" name="caution" value="${contrat.getCaution()}">
            </div>

            <div class="div-input-modif">
                <label class="label-input">Etat : </label>
                <select class="input-modif" type="text" name="etat">
                    <option value="signe" ${contrat.getEtat().toString()== "signe" ? ' selected="selected"' : ''} >signe</option>
                    <option value="paye" ${contrat.getEtat().toString()== "paye" ? ' selected="selected"' : ''}>paye</option>
                </select>
            </div>
            </fieldset>
            <br />
            <br />


            <input type="hidden" name="idModif" value="${contrat.idContrat}">
            <input type="hidden" name="flagModifFActure" value="true">
            <input type="submit" class="btn-modif2" value="Modifier" id="bouton-modif">
            <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/gestionContrat">
        </form>
    </div>

    <c:import url="footer.jsp"/>
</div>
</body>
</html>
