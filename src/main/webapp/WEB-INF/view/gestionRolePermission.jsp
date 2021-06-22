<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Gestion permissions</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Ajout de rôle</h2>

                <span style="color:red"><%=(session.getAttribute("erreur") == null) ? "" : session.getAttribute("erreur")%></span>

                <form action="<c:url value="/gestionRolePermission"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Role : </label>
                        <input class="input-modif" type="text" name="nomRole">
                    </div>

                    <input type="hidden" name="ajoutRoleFlag" value="ok">
                    <button class="btn-modif2" type="submit">Ajouter un role</button>
                </form>
            </div>

            <div class="content-global">
                <h2>Liste des rôles et permissions</h2>

                <div>
                    <table class="table-custom">
                        <thead>
                        <th scope="col">Role</th>
                        <th scope="col">Supprimer</th>
                        <th scope="col">Permission</th>
                        <th scope="col">Ajout permission</th>
                        </thead>

                        <tbody>
                            <c:forEach items="${roleList}" var="role">
                                <tr>
                                    <td><c:out value="${role.roleDescription}"/></td>
                                    <td>
                                        <form action="<c:url value="/gestionRolePermission"/>" method="post">
                                            <input type="hidden" name="supRoleFlag" value="ok">
                                            <input type="hidden" name="idRole" value="${role.idRole}">
                                            <button class="btn-table-role" type="submit">Supprimer ce role</button>
                                        </form>
                                    </td>
                                    <td>
                                        <c:forEach var="autorise" items="${autoriseList}">
                                            <c:if test="${autorise.rolesByIdRole.idRole eq role.idRole}">
                                                <c:out value="${autorise.permissionsByIdPermission.nomPermission}"/></br>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/gestionRolePermission"/>" method="post">
                                            <select class="select-table" name="idPermission">
                                                <c:forEach var="permission" items="${permissionList}">
                                                    <option value="${permission.idPermission}" >${permission.nomPermission}${permission.idPermission}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="ajoutIdRole" value="${role.idRole}">
                                            <input type="hidden" name="ajoutPermissionFlag" value="ok">
                                            <button class="btn-table-perm" type="submit">Ajouter permission sur ce role</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
