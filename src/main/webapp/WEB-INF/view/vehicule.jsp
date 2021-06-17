<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Vehicule</title>
        <c:import url="head.jsp"></c:import>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"></c:import>

            <c:forEach var="vehicule" items="${vehiculeList}">
            <div class="content-vehicle-search">
                <div class="search-vehicle-title">
                    <h3><c:out value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque} ${vehicule.modelesByIdModele.nomModele}"></c:out></h3>
                </div>

                <div class="search-vehicle-content">
                    <div class="search-vehicle-content-img"><img src="${pageContext.request.contextPath}/images/default-car.png" alt="car" /></div>
                    <div class="search-vehicle-content-info">
                        <div class="search-vehicle-content-info-box1">
                            <p class="ageMinimum">Age minimum 18 ans</p>

                            <p class="infoVehicule">Cylindrée: <c:out value="${vehicule.cylindree}"></c:out> cc</p>
                            <p class="infoVehicule">Puissance: <c:out value="${vehicule.puissance}"></c:out> cv</p>
                            <p class="infoVehicule">Couleur: <c:out value="${vehicule.couleursByIdCouleur.nomCouleur}"></c:out></p>
                        </div>
                        <div class="search-vehicle-content-info-box2">
                            <p class="optionTitle">Option:</p>
                            <ul>
                            <c:forEach var="contient" items="${vehicule.contientsByIdVehicule}">
                                <li><c:out value="${contient.optionsVehiculesByIdOption.nomOption}"></c:out></li>
                            </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="search-vehicle-content-button">
                        <div class="calculPrix">
                            <p>Prix journalier: ${vehicule.prixJournalier} EUR</p>
                            <p>Calcul: ${periodeLocation} jours x ${vehicule.prixJournalier} euros</p>
                            <fmt:formatNumber var="prixTotal" type="number" value="${periodeLocation*vehicule.prixJournalier}" minFractionDigits="2" maxFractionDigits="2"/>
                            <h2>Total: ${prixTotal} EUR</h2>
                        </div>
                        <input type="hidden" name="idVehicule" value="<c:out value="${vehicule.idVehicule}"></c:out>">
                        <input type="hidden" name="prixTotal" value="">
                        <input type="button" class="btn-select-vehicule" value="Choisir ce véhicule"/>
                    </div>
                </div>
            </div>
            </c:forEach>

            <c:import url="footer.jsp"></c:import>
        </div>
    </body>
</html>
