<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<div class="header">
    <div class="banner1">
        <div class="panel">
            <div class="logo">
                <a class="btn-link-home" href="${pageContext.request.contextPath}/accueil"><h3>LOCACAR</h3></a>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Menu<i class="fa fa-caret-down"></i></button>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/profil">Mon profil</a>
                    <a href="${pageContext.request.contextPath}/facture">Mes contrats et factures</a>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'admin'}">
                            <a href="${pageContext.request.contextPath}/gestionUtilisateur">Gestion utilisateur</a>
                            <a href="${pageContext.request.contextPath}/gestionRolePermission">Gestion roles et permissions</a>
                            <a href="${pageContext.request.contextPath}/gestionMarqueModele">Gestion marque et mod�le</a>
                            <a href="${pageContext.request.contextPath}/gestionVehicule">Gestion v�hicule</a>
                            <a href="${pageContext.request.contextPath}/gestionEntrepot">Gestion entrepot</a>
                            <a href="${pageContext.request.contextPath}/gestionReservation">Gestion r�servation</a>
                            <a href="${pageContext.request.contextPath}/gestionFacture">Gestion facture</a>
                            <a href="${pageContext.request.contextPath}/gestionContrat">Gestion contrat</a>
                    </c:when>
                        <c:when test="${sessionScope.role == 'employe'}">
                            <a href="${pageContext.request.contextPath}/gestionUtilisateur">Gestion utilisateur</a>
                            <a href="">Gestion r�servation</a>
                            <a href="${pageContext.request.contextPath}/gestionFacture">Gestion facture</a>
                            <a href="${pageContext.request.contextPath}/gestionContrat">Gestion contrat</a>
                        </c:when>
                    </c:choose>
                    <a href="${pageContext.request.contextPath}/logout">Se d�connecter</a>
                </div>
            </div>
        </div>
    </div>

    <div class="banner2"></div>
</div>