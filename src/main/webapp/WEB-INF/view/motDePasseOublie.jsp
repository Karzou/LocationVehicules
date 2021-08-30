<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<html>
    <head>
        <title>Mot de passe oublié</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <div class="content-global">
                <h2>Vous avez oublié votre mot de passe ?</h2>

                <form action="<c:url value="/motDePasseOublie"/>" method="post">
                    <div class="register-div">
                        <label for="reset-password-input">Veuillez entrer votre email</label>
                        <input type="mail" class="reset-password-input" id="reset-password-input" name="mail"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red">${erreurMail}</span>
                    </div>
                    <div class="reset-password-button-div">
                        <input type="submit" class="btn-reset-password" value="Envoyer"/>
                        <input type="button" class="btn-retour-password" value="Retour" onclick=location.href="${pageContext.request.contextPath}/login">
                    </div>
                </form>
            </div>
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
