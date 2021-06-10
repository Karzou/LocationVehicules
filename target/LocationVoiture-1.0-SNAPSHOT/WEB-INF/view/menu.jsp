<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="header">
    <div class="banner1">
        <div class="panel">
            <div class="logo">
                <a class="btn-link-home" href="${pageContext.request.contextPath}/accueil"><h3>LOCACAR</h3></a>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Menu<i class="fa fa-caret-down"></i></button>
                <div class="dropdown-content">
                    <a href="">Mon profil</a>
                    <c:if test="${sessionScope.role == 'admin'}">
                        <a href="${pageContext.request.contextPath}/gestionUtilisateur">Gestion utilisateur</a>
                        <a href="${pageContext.request.contextPath}/gestionVehicule">Gestion véhicule</a>
                        <a href="${pageContext.request.contextPath}/gestionEntrepot">Gestion entrepot</a>
                        <a href="">Gestion réservation</a>
                        <a href="">Gestion facture</a>
                        <a href="">Gestion contrat</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
                </div>
            </div>
        </div>
    </div>

    <div class="banner2"></div>
</div>