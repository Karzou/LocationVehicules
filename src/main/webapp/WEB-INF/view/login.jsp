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
                <div>
                    <c:if test="${(empty sessionScope.success and (empty sessionScope.forgotFlag)) and ((sessionScope.errLogin != null) or (sessionScope.errLogin1 != null) or (sessionScope.errLogin2 != null))}">
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
                                <div>${errLogin}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errLogin1}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errLogin2}</div>
                            </div>
                        </div>
                        <c:remove var="errLogin" scope="session" />
                        <c:remove var="errLogin1" scope="session" />
                        <c:remove var="errLogin2" scope="session" />
                    </c:if>
                </div>

                <c:if test="${not empty sessionScope.forgotFlag}">
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
                            <div>${sessionScope.forgotFlag}</div>
                        </div>
                    </div>
                    <c:remove var="forgotFlag" scope="session" />
                </c:if>


                <div>
                    <c:if test="${sessionScope.success != null}">
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
                                <div>${success}</div>
                            </div>
                        </div>
                        <c:remove var="success" scope="session" />
                    </c:if>
                </div>

                <form name="form" action="<c:url value="/login"/>" method="post" onsubmit="return validateLogin()">
                    <div class="username-login-div">
                        <input type="text" class="username-login" name="username" id="username" placeholder="Email"/>
                        <span class="span-error" id="errorMailLogin"></span>
                    </div>

                    <div class="password-login-div">
                        <input type="password" class="password-login" name="password" id="password" placeholder="Mot de passe"/>
                        <span class="span-error3" id="errorPasswordLogin"></span>
                        <a href="motDePasseOublie" >Mot de passe oublié ?</a>
                    </div>

                    <div class="password-login-button-div">
                        <input type="submit" class="btn-login" value="Connexion">
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

        <div class="container-register" id="container-register"
        <c:choose>
            <c:when test="${!sessionScope.erreurFlag}">style="display: none"</c:when>
            <c:otherwise>style="display: block"</c:otherwise>
        </c:choose>>
            <div class="content-register">
                <h2>Formulaire d'enregistrement</h2>

                <div>
                    <c:if test="${(sessionScope.errRegister1 != null) or (sessionScope.errRegister2 != null) or (sessionScope.errRegister3 != null)
                                or (sessionScope.errRegister4 != null) or (sessionScope.errRegister5 != null) or (sessionScope.errRegister6 != null)
                                or (sessionScope.errRegister7 != null) or (sessionScope.errRegister8 != null) or (sessionScope.errRegister9 != null)
                                or (sessionScope.errRegister10 != null) or (sessionScope.errRegister11 != null)}">
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
                                <div>${errRegister1}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister2}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister3}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister4}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister5}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister6}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister7}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister8}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister9}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister10}</div>
                            </div>
                            <div class="error-message-admin-body-txt">
                                <div>${errRegister11}</div>
                            </div>
                        </div>
                        <c:remove var="errRegister1" scope="session" />
                        <c:remove var="errRegister2" scope="session" />
                        <c:remove var="errRegister3" scope="session" />
                        <c:remove var="errRegister4" scope="session" />
                        <c:remove var="errRegister5" scope="session" />
                        <c:remove var="errRegister6" scope="session" />
                        <c:remove var="errRegister7" scope="session" />
                        <c:remove var="errRegister8" scope="session" />
                        <c:remove var="errRegister9" scope="session" />
                        <c:remove var="errRegister10" scope="session" />
                        <c:remove var="errRegister11" scope="session" />
                    </c:if>
                </div>

                <form name="createUser" action="<c:url value="/creationUtilisateur"/>" method="post" onsubmit="return validateCreationUtilisateur()">
                    <div class="register-div">
                        <label for="register-nom">Nom</label>
                        <input type="text" class="register-input" id="register-nom" name="nom" value="<c:out value="${nom}"></c:out>"/>
                    </div>
                    <span class="error" id="errorNomCreate"></span></p>

                    <div class="register-div">
                        <label for="register-prenom">Prénom</label>
                        <input type="text" class="register-input" id="register-prenom" name="prenom"value="<c:out value="${prenom}"></c:out>" />
                    </div>
                    <span class="error" id="errorPrenomCreate"></span></p>

                    <div class="register-div">
                        <label for="register-mail">Email</label>
                        <input type="mail" class="register-input" id="register-mail" name="mail" value="<c:out value="${mail}"></c:out>"/>
                    </div>
                    <span class="error" id="errorMailCreate"></span></p>
                    <div class="register-div">
                        <label for="register-password">Mot de passe</label>
                        <input type="password" class="register-input" id="register-password" name="password" value="<c:out value="${password}"></c:out>"/>
                    </div>
                    <span class="error" id="errorPasswordCreate"></span></p>

                    <div class="register-div">
                        <label for="register-confirmPassword">Confirmation</label>
                        <input type="password" class="register-input" id="register-confirmPassword" name="confirmPassword"/>
                    </div>

                    <div class="register-div">
                        <label for="register-telephone">Téléphone</label>
                        <input type="text" class="register-input" id="register-telephone" name="telephone" value="<c:out value="${telephone}"></c:out>"/>
                    </div>
                    <span class="error" id="errorTelephoneCreate"></span></p>
                    <div class="register-div">
                        <label for="register-rue">Rue</label>
                        <input type="text" class="register-input" id="register-rue" name="rue" value="<c:out value="${rue}"></c:out>"/>
                    </div>
                    <span class="error" id="errorRueCreate"></span></p>
                    <div class="register-div">
                        <label for="register-numero">Numéro</label>
                        <input type="text" class="register-input" id="register-numero" name="numero" value="<c:out value="${numero}"></c:out>"/>
                    </div>
                    <span class="error" id="errorNumeroCreate"></span></p>
                    <div class="register-div">
                        <label for="register-boite">Boite</label>
                        <input type="text" class="register-input" id="register-boite" name="boite" value="<c:out value="${boite}"></c:out>"/>
                    </div>
                    <span class="error" id="errorBoiteCreate"></span></p>
                    <div class="register-div">
                        <label for="register-ville">Ville</label>
                        <select class="register-input" id="register-ville" name="ville">
                            <option value="" disabled selected>Choisissez une ville</option>
                            <c:forEach items="${villes}" var="ville">
                                <option value="${ville.idVille}">${ville.nomVille}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <span class="error" id="errorVilleCreate"></span></p>
                    <div class="register-div">
                        <label for="register-dateNaissance">Date de naissance</label>
                        <input type="date" class="register-input" id="register-dateNaissance" name="dateNaissance"/>
                    </div>
                    <span class="error" id="errorDateCreate"></span></p>
                    <div class="register-div">
                        <label for="register-datePermis">Date de permis</label>
                        <input type="date" class="register-input" id="register-datePermis" name="datePermis"/>
                    </div>
                    <span class="error" id="errorPermisCreate"></span></p>
                    <div class="register-button-div">
                        <input type="submit" class="btn-register" value="S'enregistrer">
                    </div>

                    <div class="register-button-div">
                        <input type="reset"class="btn-reset" value="Réinitialiser">
                    </div>
                </form>
            </div>
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
