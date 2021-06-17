<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kevin Vanconingsloo
  Date: 11-06-21
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion permissions</title>
    <c:import url="head.jsp"></c:import>
</head>
<body>
<div class="container">
    <c:import url="menu.jsp"></c:import>

    <div class="login-div-error">
        <span style="color:red"><%=(session.getAttribute("erreur") == null) ? "" : session.getAttribute("erreur")%></span>
        </br>
    </div>

    <div>
        <table class="table-custom">
            <thead>
            <th scope="col">Role</th>
            <th></th>
            <th scope="col">Permission</th>
            <th></th>
            </thead>

            <tbody>
                <c:forEach items="${roleList}" var="role">
                    <tr>
                    <td><c:out value="${role.roleDescription}"></c:out></td>
                    <form method="post" action="<c:url value="/gestionRolePermission"/>">
                        <input type="hidden" name="supRoleFlag" value="ok">
                        <td><button type="submit" name="idRole" value="${role.idRole}">Supprimer ce role</button></td>
                    </form>
                    <td>
                        <c:forEach var="autorise" items="${autoriseList}">
                            <c:if test="${autorise.rolesByIdRole.idRole eq role.idRole}">
                                <c:out value="${autorise.permissionsByIdPermission.nomPermission}"></c:out></br>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <form name="ajouterPermission" action="<c:url value="/gestionRolePermission"/>" method="post">
                            <input type="hidden" name="ajoutPermissionFlag" value="ok">
                            <select class="input-modif" name="idPermission" id="pet-select">
                                <c:forEach var="permission" items="${permissionList}">
                                    <option value="${permission.idPermission}" >${permission.nomPermission}${permission.idPermission}</option>
                                </c:forEach>
                                <input type="hidden" name="ajoutIdRole" value="${role.idRole}">
                            </select>
                            <button>Ajouter permission sur ce role</button>
                        </form>
                    </td>
                    </tr>
                </c:forEach>
            </tbody>
            <form action="<c:url value="/gestionRolePermission"/>" method="post">
                <input type="hidden" name="ajoutRoleFlag" name="ok">
                <label>Role : </label>
                <input type="text" name="nomRole">

                <button type="submit">Ajouter un role</button>
            </form>
        </table>
    </div>
</div>
</body>
</html>
