<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Vehicule</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <c:choose>
                <c:when test="${fn:length(vehiculeList) == 0}">
                    <div class="content-vehicle-search">
                        <c:choose>
                            <c:when test="${erreurConnBdd == true}">
                                <h1>Une erreur est survenue, veuillez réessayer !!!</h1>
                            </c:when>

                            <c:otherwise>
                                <h1>Aucun véhicule disponible</h1>
                            </c:otherwise>
                        </c:choose>

                        <input type="button" class="btn-return" value="Retour" onclick=location.href="${pageContext.request.contextPath}/accueil">
                    </div>
                </c:when>

                <c:otherwise>
                    <c:forEach var="vehicule" items="${vehiculeList}">
                        <form name="selectedVehicle" action="<c:url value="/optionSup"/>" method="post">
                            <div class="content-vehicle-search">
                                <div class="search-vehicle-title">
                                    <h3><c:out value="${vehicule.modelesByIdModele.marquesByIdMarque.nomMarque} ${vehicule.modelesByIdModele.nomModele}"/></h3>
                                </div>

                                <div class="search-vehicle-content">
                                    <div class="search-vehicle-content-img"><img src="${pageContext.request.contextPath}/images/default-car.png" alt="car" /></div>
                                    <div class="search-vehicle-content-info">
                                        <div class="search-vehicle-content-info-box1">
                                            <p class="ageMinimum">Age minimum 18 ans</p>

                                            <p class="infoVehicule">Cylindrée: <c:out value="${vehicule.cylindree}"/> cc</p>
                                            <p class="infoVehicule">Puissance: <c:out value="${vehicule.puissance}"/> cv</p>
                                            <p class="infoVehicule">Couleur: <c:out value="${vehicule.couleursByIdCouleur.nomCouleur}"/></p>
                                        </div>
                                        <div class="search-vehicle-content-info-box2">
                                            <p class="optionTitle">Option:</p>
                                            <ul>
                                            <c:forEach var="contient" items="${vehicule.contientsByIdVehicule}">
                                                <li><c:out value="${contient.optionsVehiculesByIdOption.nomOption}"/></li>
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

                                        <input type="hidden" name="idLieuDepart" value="<c:out value="${idLieuDepart}"/>">
                                        <input type="hidden" name="idLieuRetour" value="<c:out value="${idLieuRetour}"/>">
                                        <input type="hidden" name="strDateDepart" value="<c:out value="${strDateDepart}"/>">
                                        <input type="hidden" name="strHeureDepart" value="<c:out value="${strHeureDepart}"/>">
                                        <input type="hidden" name="strDateRetour" value="<c:out value="${strDateRetour}"/>">
                                        <input type="hidden" name="strHeureRetour" value="<c:out value="${strHeureRetour}"/>">
                                        <input type="hidden" name="idVehicule" value="<c:out value="${vehicule.idVehicule}"/>">
                                        <input type="hidden" name="prixTotal" value="<c:out value="${prixTotal}"/>">
                                        <input type="submit" class="btn-select-vehicule" value="Choisir ce véhicule"/>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
