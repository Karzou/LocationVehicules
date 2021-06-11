<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body>
        <div class="container">
            <jsp:include page="menu.jsp" />
            <div class="content1">
                <div class="head">
                    <img class="img-home" src="${pageContext.request.contextPath}/images/home.png" alt="home" />

                    <div class="text">
                        <h1>Vous souhaitez louer un véhicule ? Vous êtes au bon endroit !</h1>
                    </div>
                </div>

                <form method="post" action="accueil">

                    <div class="box">
                        <p>Veuillez complèter les informations ci-dessous</p>
                        <p>${ Tests }</p>
                        <br/>

                        <div class="boxSelect1">
                            <label for="LieuDepart">Lieu de départ</label>
                            <select name="LieuDepart" id="LieuDepart" oninput='style.color="black"'>
                                <option value="" style='display:none'>Lieu de départ</option>
                                <c:forEach var="entrepot" items="${entrepotList}">
                                    <optgroup label="${entrepot.nomEntrepot}">
                                    <option value="${entrepot.idEntrepot}">${entrepot.adressesByIdAdresse.rue} ${entrepot.adressesByIdAdresse.numero} - ${entrepot.adressesByIdAdresse.villesByIdVille.codePostal} ${entrepot.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                                    </optgroup>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="boxSelect2">
                            <label for="LieuRetour">Lieu de retour</label>
                            <select name="LieuRetour" id="LieuRetour" oninput='style.color="black"'>
                                <option value="" style='display:none'>Lieu de retour</option>
                                <c:forEach var="entrepot" items="${entrepotList}">
                                    <optgroup label="${entrepot.nomEntrepot}">
                                        <option value="${entrepot.idEntrepot}">${entrepot.adressesByIdAdresse.rue} ${entrepot.adressesByIdAdresse.numero} - ${entrepot.adressesByIdAdresse.villesByIdVille.codePostal} ${entrepot.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                                    </optgroup>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="boxDatetime1">
                            <label for="dateTimeDepart">Date et heure de départ</label>
                            <input type="datetime-local" id="dateTimeDepart"
                                   name="dateTimeDepart" value=""
                                   min="2021-01-01T00:00" max="2050-01-01T00:00" class="empty" />
                        </div>

                        <div class="boxDatetime2">
                            <label for="dateTimeRetour">Date et heure de retour</label>
                            <input type="datetime-local" id="dateTimeRetour"
                                   name="dateTimeRetour" value=""
                                   min="2021-01-01T00:00" max="2050-01-01T00:00" class="empty" step="100" />
                        </div>

                        <div class="submitButton ">
                            <input type="submit" value="Rechercher" class="searchButton" />
                        </div>
                    </div>
                </form>
            </div>

            <div class="content2">
                <div class="box1">
                    <img class="img-box1" src="${pageContext.request.contextPath}/images/car.png" alt="car" />
                    <h2>Renouvellement régulière de notre flotte</h2>
                    <p>Nous renouvellons notre flotte le plus régulièrement possible</p>
                </div>

                <div class="box2">
                    <img class="img-box2" src="${pageContext.request.contextPath}/images/calendar.png" alt="calendar" />
                    <h2>Quotidien, hebdomadaire ou mensuel</h2>
                    <p>Louez votre véhicule aussi longtemps que vous le souhaiter</p>
                </div>

                <div class="box3">
                    <img class="img-box3" src="${pageContext.request.contextPath}/images/card.png" alt="card" />
                    <h2>Paiement en ligne</h2>
                    <p>Nous vous proposons le mode de paiement en ligne via carte bancaire</p>
                </div>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
