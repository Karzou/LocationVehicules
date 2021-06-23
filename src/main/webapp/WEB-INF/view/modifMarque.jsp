<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification marque</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification marque</h2>

                <form action="<c:url value="/gestionMarqueModele"/>" method="post">
                    <div class="div-input-modif">
                        <label class="label-input">Marque : </label>
                        <input class="input-modif" type="text" name="nomMarque" value="${marque.nomMarque}">
                    </div>

                    <input type="hidden" name="idModif" value="${marque.idMarque}">
                    <input type="hidden" name="flagModifMarque" value="true">
                    <button type="submit" class="btn-modif2" id="bouton-modif">Modifier</button>
                </form>
            </div>

            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
