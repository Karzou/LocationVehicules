<%--
  Created by IntelliJ IDEA.
  User: djkar
  Date: 06-08-21
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
    <title>Mot de passe oublié</title>
    <c:import url="head.jsp"/>
</head>
<body>
<form action="<c:url value="motDePasseOublie"/>" method="post">
    <div class="register-div">
        <label for="register-mail">Entrez votre email</label>
        <input type="mail" class="register-input" id="register-mail" name="mail"/>
    </div>
    <div class="login-div-error">
        <span style="color:red"><%=(request.getAttribute("erreurMail") == null) ? "" : request.getAttribute("erreurMail")%></span>
        </br>
    </div>
    <div class="register-button-div">
        <input type="submit" class="btn-register" value="Envoi du mail de changement de mot de passe">
    </div>
</form>

<c:import url="footer.jsp"/>
</body>
</html>
