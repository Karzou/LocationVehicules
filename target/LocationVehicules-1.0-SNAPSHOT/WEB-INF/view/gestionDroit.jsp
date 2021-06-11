<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Title</title>
        <style><%@ include file="/css/style.css" %></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body>
        <div class="container">
            <jsp:include page="menu.jsp" />

            <h1>Gestion des droits utilisateur</h1>

            <div>
                <table class="table-custom">
                    <thead>
                        <th scope="col">Nom</th>
                        <th scope="col">Prenom</th>
                        <th scope="col">Role</th>
                        <th scope="col">Permissions</th>
                    </thead>

                    <tbody>
                    <form action="<c:url value="/gestionDroit"/>" method="post">
                        <td><c:out value="${utilisateur.nomUtilisateur}"></c:out></td>
                            <input type="hidden" name="idSup" value="${utilisateur.idUtilisateur}"></input>
                        <td><c:out value="${utilisateur.prenomUtilisateur}"></c:out></td>
                        <td>
                                <label>Role : </label>
                                <select class="form-control" name="idRole" id="pet-select">
                                    <option value="${utilisateur.rolesByIdRole.idRole}">${utilisateur.rolesByIdRole.roleDescription}</option>
                                    <option value="1">admin</option>
                                    <option value="3">employe</option>
                                    <option value="2">client</option>
                                </select>

                        </td>
                        <td>
                            <c:forEach var="autorise" items="${autorise}">
                                <c:out value="${autorise.permissionsByIdPermission.nomPermission}"></c:out>
                            </c:forEach>
                        </td>
                        <td>
                            <button type="submit">Modifer</button>
                        </td>
                    </form>
                    </tbody>
                </table>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
