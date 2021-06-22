<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <table class="table-custom">
                        <thead>
                        <th scope="col">Nom</th>
                        <th scope="col">Prenom</th>
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
