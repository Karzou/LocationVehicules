<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Gestion utilisateur</title>
        <style><%@ include file="/css/style.css"%></style>
    </head>

    <body class="body">
        <div class="container">
            <jsp:include page="menu.jsp" />

            <div class="content-utilisateur">
                <h1>Liste des utilisateurs</h1>

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
                            <th scope="col">Rôles</th>
                            <th scope="col">Autorisation</th>
                            <th scope="col">Réservation</th>
                            <th scope="col">Modification</th>
                            <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                        <c:forEach var="user" items="${utilisateurList}">
                            <tr>
                                <c:if test="${user.actifUtilisateur}">
                                    <td><c:out value="${user.nomUtilisateur}"></c:out></td>
                                    <td><c:out value="${user.prenomUtilisateur}"></c:out></td>
                                    <td><c:out value="${user.adressesByIdAdresse.rue}"></c:out>
                                    <td><c:out value="${user.adressesByIdAdresse.numero}"></c:out></td>
                                    <td><c:out value="${user.adressesByIdAdresse.boite}"></c:out></td>
                                    <td><c:out value="${user.adressesByIdAdresse.villesByIdVille.codePostal}"></c:out></td>
                                    <td><c:out value="${user.adressesByIdAdresse.villesByIdVille.nomVille}"></c:out></td>
                                    <td><c:out value="${user.rolesByIdRole.roleDescription}"></c:out></td>
                                    <c:if test="${sessionScope.role == 'admin'}">
                                    <td>
                                        <form action="<c:url value="/gestionDroit"/>" method="get">
                                            <input type="hidden" name="idSup" value="${user.idUtilisateur}"></input>
                                            <input type="hidden" name="idRole" value="${user.rolesByIdRole.idRole}"></input>
                                            <button class="btn-right" name="idSup" type="submit" value="supprimer">Gestion des droits</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/accueil"/>" method="post">
                                            <input type="hidden" name="idSup" value="${user.idUtilisateur}"></input>
                                            <button class="btn-reserv" name="idSup" type="submit" value="supprimer">Réservations client</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/modifUtilisateur"/>" method="post">
                                            <input type="hidden" name="idModif" value="${user.idUtilisateur}"></input>
                                            <button class="btn-modif" name="idModif" type="submit" value="modifer">Modifer</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/supUtilisateur"/>" method="post">
                                            <input type="hidden" name="idSup" value="${user.idUtilisateur}"></input>
                                            <button class="btn-sup" name="idSup" type="submit" value="supprimer">Supprimer</button>
                                        </form>
                                    </td>
                                    </c:if>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>
    </body>
</html>
