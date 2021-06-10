<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <style><%@ include file="/css/style.css"%></style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    </head>

    <body class="body">
        <div class="container-login" id="container-login">
            <div class="logo-login">
                <h3>LOCACAR</h3>
            </div>
            <div class="logo-login2"></div>

            <div class="content-login">
                <form name="form" action="<c:url value="/login"/>" method="post">
                    <div class="username-login-div">
                        <input type="text" class="username-login" name="username" id="username" placeholder="Email"/>
                       <!-- <span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>-->
                    </div>

                    <div class="password-login-div">
                        <input type="password" class="password-login" name="password" id="password" placeholder="Mot de passe"/>
                    	<!--<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>-->
                    </div>
                    
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
                    </div>

                    <div class="login-div-success">
                        <span style="color:green"><%=(request.getAttribute("succes") == null) ? "" : request.getAttribute("succes")%></span>
                    </div>

                    <div>
                        <input type="submit" class="btn-login" value="Connexion"></input>
                    </div>

                    <div>
                        <p>Vous êtes nouveau ici ? Cliquez ci-dessous pour vous enregistrer !</p>
                    </div>

                    <div>
                        <input type="button" class="btn-register-form" value="Créer un compte" onclick="showRegisterForm()"></input>
                    </div>
                </form>
            </div>
        </div>

        <div class="container-register" id="container-register">
            <div class="content-register">
                <form name="createUser" action="<c:url value="/creationUtilisateur"/>" method="post">
                    <div class="register-div">
                        <label for="register-nom">Nom</label>
                        <input type="text" class="register-input" id="register-nom" name="nom" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-prenom">Prénom</label>
                        <input type="text" class="register-input" id="register-prenom" name="prenom" class="form-control"/>
                    </div>

                    <div class="register-div">
                        <label for="register-mail">Email</label>
                        <input type="mail" class="register-input" id="register-mail" name="mail" class="form-control"/>
                    </div>

                    <div class="register-div">
                        <label for="register-password">Mot de passe</label>
                        <input type="password" class="register-input" id="register-password" name="password" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-confirmPassword">Confirmation</label>
                        <input type="password" class="register-input" id="register-confirmPassword" name="confirmPassword" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-telephone">Téléphone</label>
                        <input type="text" class="register-input" id="register-telephone" name="telephone" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-rue">Rue</label>
                        <input type="text" class="register-input" id="register-rue" name="rue" class="form-control"/>
                    </div>

                    <div class="register-div">
                        <label for="register-numero">Numéro</label>
                        <input type="text" class="register-input" id="register-numero" name="numero" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-boite">Boite</label>
                        <input type="text" class="register-input" id="register-boite" name="boite" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-ville">Ville</label>
                        <input type="text" class="register-input" id="register-ville" name="ville" class="form-control" />
                    </div>

                    <div class="register-div">
                        <label for="register-codepostal">Code postal</label>
                        <input type="text" class="register-input" id="register-codepostal" name="codepostal" class="form-control"/>
                    </div>

                    <div class="register-div">
                        <label for="register-dateNaissance">Date de naissance</label>
                        <input type="date" class="register-input" id="register-dateNaissance" name="dateNaissance" class="form-control"/>
                    </div>

                    <div class="register-div">
                        <label for="register-datePermis">Date de permis</label>
                        <input type="date" class="register-input" id="register-datePermis" name="datePermis" class="form-control"/>
                    </div>

                    <div class="register-button-div">
                        <input type="submit" class="btn-register" value="Enregistrer"></input>
                        <input type="reset"class="btn-reset" value="Reset"></input>
                    </div>

					<div class="login-div-error">
                    	<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
