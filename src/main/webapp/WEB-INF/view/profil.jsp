<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Profil</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h1>Mon profil</h1>

                <c:if test="${not empty succes}">
                    <div class="success-message-admin">
                        <div class="success-message-admin-title">
                            <div class="success-message-admin-title-img">
                                <img src="${pageContext.request.contextPath}/images/success.png" alt="success" />
                            </div>
                            <div class="success-message-admin-title-txt">
                                <p>Félicitation</p>
                            </div>
                        </div>
                        <div class="success-message-admin-body-txt">
                            <div>${succes}</div>
                        </div>
                    </div>
                    <c:remove var="succes" scope="session" />
                </c:if>

                <table class="table-custom">
                    <thead>
                        <th scope="col">Nom</th>
                        <th scope="col">Prenom</th>
                        <th scope="col">Email</th>
                        <th scope="col">Rue</th>
                        <th scope="col">Numéro</th>
                        <th scope="col">Boite</th>
                        <th scope="col">Code postal</th>
                        <th scope="col">Ville</th>
                        <th scope="col">Mes réservations</th>
                        <th scope="col">Mes contrats et factures</th>
                        <th scope="col">Modification</th>
                    </thead>

                    <tbody>
                        <td><c:out value="${utilisateur.nomUtilisateur}"/></td>
                        <td><c:out value="${utilisateur.prenomUtilisateur}"/></td>
                        <td><c:out value="${utilisateur.email}"></c:out> </td>
                        <td><c:out value="${utilisateur.adressesByIdAdresse.rue}"/></td>
                        <td><c:out value="${utilisateur.adressesByIdAdresse.numero}"/></td>
                        <td><c:out value="${utilisateur.adressesByIdAdresse.boite}"/></td>
                        <td><c:out value="${utilisateur.adressesByIdAdresse.villesByIdVille.codePostal}"/></td>
                        <td><c:out value="${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}"/></td>
                        <td>En cours (shahin)</td>
                        <td><form action="<c:url value="/facture"/>" method="get">
                            <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
                            <button class="btn-modif" name="idModif" type="submit">Mes contrats et factures</button>
                        </form></td>
                        <td>
                            <form action="<c:url value="/modifUtilisateur"/>" method="post">
                                <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
                                <button class="btn-modif" name="idModif" type="submit">Modifier</button>
                                <input type="hidden" name="profilFlag" value="ok">
                            </form>
                        </td>
                    </tbody>
                </table>
            </div>
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
