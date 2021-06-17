<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <c:import url="head.jsp"/>
        <title>Accueil</title>
    </head>

    <body>
        <div class="container">
            <c:import url="menu.jsp"/>
            <div class="content1">
                <div class="head">
                    <img class="img-home" src="${pageContext.request.contextPath}/images/home.png" alt="home" />

                    <div class="text">
                        <h1>Vous souhaitez louer un véhicule ? Vous êtes au bon endroit !</h1>
                    </div>
                </div>

                <form name="searchVehicle" action="<c:url value="/vehicule"/>" method="post">
                    <div class="box">
                        <p>Veuillez complèter les informations ci-dessous</p>
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

                        <div class="boxDate1">
                            <label for="dateTimeDepart">Date et heure de départ</label>
                            <input type="date" id="dateTimeDepart" name="dateTimeDepart" value="" class="empty" />
                        </div>

                        <div class="boxtime1">
                            <input type="time" id="heureDepart" name="heureDepart" value="" class="empty" />
                        </div>

                        <div class="boxDate2">
                            <label for="dateTimeRetour">Date et heure de retour</label>
                            <input type="date" id="dateTimeRetour" name="dateTimeRetour" class="empty" />
                        </div>

                        <div class="boxtime2">
                            <input type="time" id="heureRetour" name="heureRetour" value="" class="empty" />
                        </div>

                        <div class="submitButton ">
                            <input type="hidden" name="dateFin" value="2021-06-24">
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

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
