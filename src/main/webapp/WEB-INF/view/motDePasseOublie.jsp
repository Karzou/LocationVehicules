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

                <p>Si vous avez oublié votre mot de passe, complètez le champs<br />
                    ci-dessous avec votre email et appuyé sur le bouton envoyer.<br />
                    Vous recevrez alors votre nouveau mot de passe par email.</p>

                <div>
                    <c:if test="${sessionScope.errMessage != null}">
                        <div class="error-message-admin">
                            <div class="error-message-admin-title">
                                <div class="error-message-admin-title-img">
                                    <img src="${pageContext.request.contextPath}/images/error.png" alt="error" />
                                </div>
                                <div class="error-message-admin-title-txt">
                                    <p>Une erreur est survenue</p>
                                </div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errMessage}</div>
                            </div>
                        </div>
                        <c:remove var="errMessage" scope="session" />
                    </c:if>
                </div>

                <div>
                    <c:if test="${sessionScope.succMessage != null}">
                        <div class="success-message-admin">
                            <div class="success-message-admin-title">
                                <div class="success-message-admin-title-img">
                                    <img src="${pageContext.request.contextPath}/images/success.png" alt="success" />
                                </div>
                                <div class="success-message-admin-title-txt">
                                    <p>Félicitation</p>
                                </div>
                            </div>
                            <div class="success-message-admin-body-txt">
                                <div>${succMessage}</div>
                            </div>
                        </div>
                        <c:remove var="succMessage" scope="session" />
                    </c:if>
                </div>

                <form name="motDePasseOublie" action="<c:url value="/motDePasseOublie"/>" method="post" onsubmit="return validateModifMotDePasse()">
                    <div><label class="reset-password-label">Veuillez entrer votre email</label></div>
                    <div class="register-div">
                        <input type="mail" class="reset-password-input" id="reset-password-input" name="mail"/>
                        <span class="span-error" id="errorMotDePasseModif"></span>
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
