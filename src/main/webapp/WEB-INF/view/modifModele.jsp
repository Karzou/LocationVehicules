<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification mod�le</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification mod�le</h2>

                <form action="<c:url value="/gestionMarqueModele"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Mod�le � modifer : </label>
                        <select class="select-modif" id="modif-modele" name="idModif" value="">
                            <c:forEach var="modele" items="${modeleList}">
                                <c:if test="${modele.marquesByIdMarque.idMarque == marque.idMarque}">
                                    <option value="${modele.idModele}">${modele.nomModele}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="div-input-modif">
                        <label class="label-input">Remplacer par : </label>
                        <input class="input-modif" type="text" name="nomModele">
                    </div>

                    <button type="submit" class="btn-modif2" value="Envoyer" id="bouton-modif">Modifier</button>
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
