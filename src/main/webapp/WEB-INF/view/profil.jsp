<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Profil</title>
        <c:import url="head.jsp"></c:import>
    </head>

    <body>
    <div class="container">
            <c:import url="menu.jsp"></c:import>

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
                            <td><c:out value="${utilisateur.nomUtilisateur}"></c:out> </td>
                            <td><c:out value="${utilisateur.prenomUtilisateur}"></c:out> </td>
                            <td><c:out value="${utilisateur.adressesByIdAdresse.rue}"></c:out> </td>
                            <td><c:out value="${utilisateur.adressesByIdAdresse.numero}"></c:out> </td>
                            <td><c:out value="${utilisateur.adressesByIdAdresse.boite}"></c:out> </td>
                            <td><c:out value="${utilisateur.adressesByIdAdresse.villesByIdVille.codePostal}"></c:out> </td>
                            <td><c:out value="${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}"></c:out></td>
                            <td>En cours (shahin)</td>
                            <td>En cours (Jerome)</td>
                            <td> En cours (Jerome)</td>
                            <td>
                                <form action="<c:url value="/modifUtilisateur"/>" method="post">
                                    <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}"></input>
                                    <button class="btn-modif" name="idModif" type="submit" value="modifer">Modifer</button>
                                    <input type="hidden" name="profilFlag" value="ok">
                                </form>
                            </td>
                        </tbody>


            <c:import url="footer.jsp"></c:import>
        </div>
    </body>
</html>
