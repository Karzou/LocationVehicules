<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
    <head>
        <title>Gestion des droits</title>
        <c:import url="head.jsp"/>
    </head>

    <body>
        <div class="container">
            <c:import url="menu.jsp"/>

            <h2>Gestion des droits utilisateur</h2>

            <div class="content-global-overflow">
                <table class="table-custom">
                    <thead>
                        <th scope="col">Nom</th>
                        <th scope="col">Prenom</th>
                        <th scope="col">Role</th>
                        <th scope="col">Permissions</th>
                        <th></th>
                    </thead>

                    <tbody>
                        <form action="<c:url value="/gestionDroit"/>" method="post">
                            <td><c:out value="${utilisateur.nomUtilisateur}"/></td>
                                <input type="hidden" name="idSup" value="${utilisateur.idUtilisateur}">
                            <td><c:out value="${utilisateur.prenomUtilisateur}"/></td>
                            <td>
                                <label>Role : </label>
                                <select class="form-control" name="idRole" id="pet-select">
                                    <option value="${utilisateur.rolesByIdRole.idRole}">${utilisateur.rolesByIdRole.roleDescription}</option>
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="${role.idRole}">${role.roleDescription}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <c:forEach var="autorise" items="${autorise}">
                                    <c:out value="${autorise.permissionsByIdPermission.nomPermission}"/>
                                    </br>
                                </c:forEach>
                            </td>
                            <td>
                                <button type="submit">Modifier</button>
                            </td>
                        </form>
                    </tbody>
                </table>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
