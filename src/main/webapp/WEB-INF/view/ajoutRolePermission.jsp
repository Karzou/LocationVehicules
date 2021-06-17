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

    <div>
        <table class="table-custom">
            <thead>
            <th scope="col">Role</th>
            <th></th>
            <th scope="col">Permission</th>
            <th></th>
            </thead>

            <tbody>
            <c:forEach items="roleList" var="role">
                <td><c:out value="${role.roleDescription}"></c:out></td>
                <form method="post" action="/supRole">
                    <td><button type="submit" name="idRole" value="${role.idRole}">Supprimer ce role</button></td>
                </form>
                <td>
                    <c:forEach var="autorise" items="${role.autorisesByIdRole}"></c:forEach>
                    <c:out value="${autorise.permissionsByIdPermission.nomPermission}"></c:out>
                </td>
                <td>
                    <form name="ajouterPermission" action="/ajoutRolePermission" method="post">

                    </form>
                </td>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <c:import url="footer.jsp"></c:import>
</div>
</body>
</html>
