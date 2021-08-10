<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
    <head>
        <title>Profil</title>
        <c:import url="head.jsp"/>
    </head>

    <body>
    <div class="container">
            <c:import url="menu.jsp"/>

            <h1>Mes données.</h1>
                <div>
                    <c:if test="${not empty succes}">
                        <div class="login-div-success">
                            <span style="color:green">${succes}</span>
                        </div>
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
                        <th scope="col">Mes contrats</th>
                        <th scope="col">Mes factures</th>
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
                            <td>En cours (Jerome)</td>
                            <td> En cours (Jerome)</td>
                            <td>
                                <form action="<c:url value="/modifUtilisateur"/>" method="post">
                                    <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
                                    <button class="btn-modif" name="idModif" type="submit">Modifier</button>
                                    <input type="hidden" name="profilFlag" value="ok">
                                </form>
                            </td>
                        </tbody>
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
