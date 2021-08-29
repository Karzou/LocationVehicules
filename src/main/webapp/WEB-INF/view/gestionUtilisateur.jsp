<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
    <head>
        <title>Gestion utilisateur</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Liste des utilisateurs actifs</h2>

                <c:if test="${not empty succes}">
                    <div class="login-div-success">
                        <span style="color:green">${succes}</span>
                    </div>
                </c:if>

                <span style="color:#ff0000">${sessionScope.erreur}</span>

                    <table class="table-custom">
                        <thead>
                            <th scope="col">Nom</th>
                            <th scope="col">Prenom</th>
                            <th scope="col">Email</th>
                            <th scope="col">Telephone</th>
                            <th scope="col">Rue</th>
                            <th scope="col">Num�ro</th>
                            <th scope="col">Boite</th>
                            <th scope="col">Code postal</th>
                            <th scope="col">Ville</th>
                            <th scope="col">R�les</th>
                            <th scope="col">Autorisation</th>
                            <th scope="col">R�servation</th>
                            <th scope="col">Modification</th>
                            <th scope="col">Activation</th>
                        </thead>

                        <tbody>
                            <c:forEach var="user" items="${utilisateurList}">
                            <tr>
                                <c:if test="${user.actifUtilisateur}">
                                    <td><c:out value="${user.nomUtilisateur}"/></td>
                                    <td><c:out value="${user.prenomUtilisateur}"/></td>
                                    <td><c:out value="${user.email}"/></td>
                                    <td>
                                    <c:forEach var="tel" items="${user.telephonesByIdUtilisateur}">
                                        <c:out value="${tel.numero}"></c:out>
                                    </c:forEach>
                                    </td>
                                    <td><c:out value="${user.adressesByIdAdresse.rue}"/></td>
                                    <td><c:out value="${user.adressesByIdAdresse.numero}"/></td>
                                    <td><c:out value="${user.adressesByIdAdresse.boite}"/></td>
                                    <td><c:out value="${user.adressesByIdAdresse.villesByIdVille.codePostal}"/></td>
                                    <td><c:out value="${user.adressesByIdAdresse.villesByIdVille.nomVille}"/></td>
                                    <td><c:out value="${user.rolesByIdRole.roleDescription}"/></td>
                                    <c:if test="${(sessionScope.role == 'admin') || (sessionScope.role == 'employe')}">
                                        <td>
                                            <c:if test="${(sessionScope.role == 'admin')}">
                                                <form action="<c:url value="/gestionDroit"/>" method="get">
                                                    <input type="hidden" name="idSup" value="${user.idUtilisateur}"/>
                                                    <input type="hidden" name="idRole" value="${user.rolesByIdRole.idRole}"/>
                                                    <button type="submit" class="btn-right" name="idSup" value="droits">Gestion des droits</button>
                                                </form>
                                            </c:if>
                                        </td>
                                        <td>
                                            <form action="<c:url value="/reservation"/>" method="post">
                                                <input type="hidden" name="idSup" value="${user.idUtilisateur}"/>
                                                <input type="submit" class="btn-reserv" value="R�servations client"/>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="<c:url value="/modifUtilisateur"/>" method="post">
                                                <input type="hidden" name="idModif" value="${user.idUtilisateur}"/>
                                                <input type="submit" class="btn-modif" value="Modifier"/>
                                            </form>
                                        </td>
                                        <td>
                                            <c:if test="${(sessionScope.role == 'admin')}">
                                                <form action="<c:url value="/supUtilisateur"/>" method="post">
                                                    <input type="hidden" name="idSup" value="${user.idUtilisateur}"/>
                                                    <input type="submit" class="btn-sup" value="D�sactiver"/>
                                                </form>
                                            </c:if>
                                        </td>
                                    </c:if>
                                </c:if>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${sessionScope.menu eq 'admin'}">
                        <h2>Liste des utilisateurs inactifs </h2>

                        <table class="table-custom">
                            <thead>
                                <th scope="col">Nom</th>
                                <th scope="col">Prenom</th>
                                <th scope="col">Email</th>
                                <th scope="col">Telephone</th>
                                <th scope="col">Rue</th>
                                <th scope="col">Num�ro</th>
                                <th scope="col">Boite</th>
                                <th scope="col">Code postal</th>
                                <th scope="col">Ville</th>
                                <th scope="col">R�les</th>
                                <th scope="col">Autorisation</th>
                                <th scope="col">R�servation</th>
                                <th scope="col">Modification</th>
                                <th scope="col">Activation</th>
                            </thead>

                            <tbody>
                            <c:forEach var="user" items="${utilisateurList}">
                                <tr>
                                    <c:if test="${! user.actifUtilisateur}">
                                        <td><c:out value="${user.nomUtilisateur}"/></td>
                                        <td><c:out value="${user.prenomUtilisateur}"/></td>
                                        <td><c:out value="${user.email}"/></td>
                                        <td>
                                        <c:forEach var="tel" items="${user.telephonesByIdUtilisateur}">
                                            <c:out value="${tel.numero}"></c:out>
                                        </c:forEach>
                                        </td>
                                        <td><c:out value="${user.adressesByIdAdresse.rue}"/>
                                        <td><c:out value="${user.adressesByIdAdresse.numero}"/></td>
                                        <td><c:out value="${user.adressesByIdAdresse.boite}"/></td>
                                        <td><c:out value="${user.adressesByIdAdresse.villesByIdVille.codePostal}"/></td>
                                        <td><c:out value="${user.adressesByIdAdresse.villesByIdVille.nomVille}"/></td>
                                        <td><c:out value="${user.rolesByIdRole.roleDescription}"/></td>
                                        <c:if test="${(sessionScope.role == 'admin') || (sessionScope.role == 'employe') }">
                                            <td>
                                                <c:if test="${(sessionScope.role == 'admin')}">
                                                    <form action="<c:url value="/gestionDroit"/>" method="get">
                                                        <input type="hidden" name="idSup" value="${user.idUtilisateur}">
                                                        <input type="hidden" name="idRole" value="${user.rolesByIdRole.idRole}">
                                                        <input type="submit" class="btn-right" value="Gestion des droits"/>
                                                    </form>
                                                </c:if>
                                            </td>
                                            <td>
                                                <form action="<c:url value="/reservation"/>" method="post">
                                                    <input type="hidden" name="idSup" value="${user.idUtilisateur}">
                                                    <input type="submit" class="btn-reserv" value="R�servations client"/>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="<c:url value="/modifUtilisateur"/>" method="post">
                                                    <input type="hidden" name="idModif" value="${user.idUtilisateur}">
                                                    <input type="submit" class="btn-modif" value="Modifier"/>
                                                </form>
                                            </td>
                                            <td>
                                                <c:if test="${(sessionScope.role == 'admin')}">
                                                    <form action="<c:url value="/supUtilisateur"/>" method="post">
                                                        <input type="hidden" name="idSup" value="${user.idUtilisateur}"/>
                                                        <input type="submit" class="btn-modif" value="R�activer"/>
                                                    </form>
                                                </c:if>
                                            </td>
                                        </c:if>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>

                <c:import url="footer.jsp"/>
            </div>
        </div>
    </body>
</html>
