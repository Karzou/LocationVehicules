<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <c:import url="head.jsp"/>
        <title>Connexion / Enregistrement</title>
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
                    </div>

                    <div class="password-login-div">
                        <input type="password" class="password-login" name="password" id="password" placeholder="Mot de passe"/>
                    </div>
                    <c:if test="${empty succes}">
                        <div class="login-div-error">
                            <span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
                        </div>
                    </c:if>

                    <div class="login-div-success">
                        <span style="color:green"><%=(request.getAttribute("succes") == null) ? "" : request.getAttribute("succes")%></span>
                    </div>

                    <div>
                        <input type="submit" class="btn-login" value="Connexion">
                    </div>

                    <div>
                        <a href="motDePasseOublie" >Mot de passe oublié ? </a>
                    </div>


                    <div>
                        <p>Vous êtes nouveau ici ? Cliquez ci-dessous pour vous enregistrer !</p>
                    </div>

                    <div>
                        <input type="button" class="btn-register-form" value="Créer un compte" onclick="showRegisterForm()">
                    </div>
                </form>
            </div>
        </div>

        <div class="container-register" id="container-register" style="display: ${(empty erreurFlag) ? 'none': 'block'};">
            <div class="content-register">
                <form name="createUser" action="<c:url value="/creationUtilisateur"/>" method="post">
                    <div class="register-div">
                        <label for="register-nom">Nom</label>
                        <input type="text" class="register-input" id="register-nom" name="nom"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("erreurNom") == null) ? "" : request.getAttribute("erreurNom")%></span>
                    </br>
                    </div>


                    <div class="register-div">
                        <label for="register-prenom">Prénom</label>
                        <input type="text" class="register-input" id="register-prenom" name="prenom"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("erreurPrenom") == null) ? "" : request.getAttribute("erreurPrenom")%></span>
                        </br>
                    </div>

                    <div class="register-div">
                        <label for="register-mail">Email</label>
                        <input type="mail" class="register-input" id="register-mail" name="mail"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("erreurMail") == null) ? "" : request.getAttribute("erreurMail")%></span>
                        </br>
                    </div>

                    <div class="register-div">
                        <label for="register-password">Mot de passe</label>
                        <input type="password" class="register-input" id="register-password" name="password"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("erreurPassword") == null) ? "" : request.getAttribute("erreurPassword")%></span>
                        </br>
                    </div>

                    <div class="register-div">
                        <label for="register-confirmPassword">Confirmation</label>
                        <input type="password" class="register-input" id="register-confirmPassword" name="confirmPassword"/>
                    </div>

                    <div class="register-div">
                        <label for="register-telephone">Téléphone</label>
                        <input type="text" class="register-input" id="register-telephone" name="telephone"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("erreurTelephone") == null) ? "" : request.getAttribute("erreurTelephone")%></span>
                        </br>
                    </div>

                    <div class="register-div">
                        <label for="register-rue">Rue</label>
                        <input type="text" class="register-input" id="register-rue" name="rue"/>
                    </div>
                    <div class="login-div-error">
                        <span style="color:red"><%=(request.getAttribute("erreurAdresse") == null) ? "" : request.getAttribute("erreurAdresse")%></span>
                        </br>
                    </div>

                    <div class="register-div">
                        <label for="register-numero">Numéro</label>
                        <input type="text" class="register-input" id="register-numero" name="numero"/>
                    </div>

                    <div class="register-div">
                        <label for="register-boite">Boite</label>
                        <input type="text" class="register-input" id="register-boite" name="boite"/>
                    </div>

                    <div class="register-div">
                        <label for="register-ville">Ville</label>
                        <select class="register-input" id="register-ville" name="ville">
                            <c:forEach items="${villes}" var="ville">
                                <option value="${ville.idVille}">${ville.nomVille}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="register-div">
                        <label for="register-dateNaissance">Date de naissance</label>
                        <input type="date" class="register-input" id="register-dateNaissance" name="dateNaissance"/>
                    </div>

                    <div class="register-div">
                        <label for="register-datePermis">Date de permis</label>
                        <input type="date" class="register-input" id="register-datePermis" name="datePermis"/>
                    </div>

                    <div class="register-button-div">
                        <input type="submit" class="btn-register" value="Enregistrer">
                        <input type="reset"class="btn-reset" value="Reset">
                    </div>
                <c:if test="${empty succes}">
					<div class="login-div-error">
                    	<span style="color:#ff0000"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
                    </div>
                </c:if>
                </form>
            </div>
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
