<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modification utilisateur</title>
        <c:import url="head.jsp"/>
    </head>

    <body class="body">
        <div class="container">
            <c:import url="menu.jsp"/>

            <div class="content-global">
                <h2>Modification utilisateur</h2>

                <form name="modifUser" action="<c:url value="/gestionUtilisateur"/>" method="post" onsubmit="return validateModifUtilisateur()">
                    <div class="div-input-modif">
                        <label class="label-input">Nom</label>
                        <input class="input-modif" type="text" id="modif-nom" name="nom" value="${utilisateur.nomUtilisateur}">
                        <div class="span-error-div"><span class="span-error4" id="errorNomModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Prenom</label>
                        <input class="input-modif" type="text" id="modif-prenom" name="prenom" value="${utilisateur.prenomUtilisateur}">
                        <div class="span-error-div"><span class="span-error4" id="errorPrenomModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Telephone</label>
                        <input class="input-modif" type="text" id="modif-telephone" name="telephone" value="${utilisateur.telephone}">
                        <div class="span-error-div"><span class="span-error4" id="errorTelephoneModif"></span></div>
                    </div>


                    <div class="div-input-modif">
                        <label class="label-input">Password</label>
                        <select class="input-modif" id="modif-password" name="password">
                            <option value="${utilisateur.motDePasse}">${utilisateur.motDePasse}</option>
                            <option value="0000">Reset</option>
                        </select>
                        <c:if test="${sessionScope.idUtilisateur == utilisateur.idUtilisateur}">
                            <a href="changerMotDePasse">Changer mot de passe</a>
                        </c:if>
                        <div class="span-error-div"><span class="span-error4" id="errorPasswordModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Date de naissance</label>
                        <input class="input-modif" type="date" id="modif-dateNaissance" name="dateNaissance" value="${utilisateur.dateNaissance}">
                        <div class="span-error-div"><span class="span-error4" id="errorDateNaissanceModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Date de permis</label>
                        <input class="input-modif" type="date" id="modif-datePermis" name="datePermis" value="${utilisateur.datePermis}">
                        <div class="span-error-div"><span class="span-error4" id="errorDatePermisModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Rue</label>
                        <input class="input-modif" type="text" id="modif-rue" name="rue" value="${utilisateur.adressesByIdAdresse.rue}">
                        <div class="span-error-div"><span class="span-error4" id="errorRueModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Numero</label>
                        <input class="input-modif" type="text" id="modif-numero" name="numero" value="${utilisateur.adressesByIdAdresse.numero}">
                        <div class="span-error-div"><span class="span-error4" id="errorNumModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Boite</label>
                        <input class="input-modif" type="text" id="modif-boite" name="boite" value="${utilisateur.adressesByIdAdresse.boite}">
                        <div class="span-error-div"><span class="span-error4" id="errorBoiteModif"></span></div>
                    </div>

                    <div class="div-input-modif">
                        <label class="label-input">Ville</label>
                        <select class="input-modif" id="modif-ville" name="ville" value="${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}">
                            <option value="${utilisateur.adressesByIdAdresse.villesByIdVille.idVille}">${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                            <c:forEach items="${villes}" var="ville">
                                <option value="${ville.idVille}">${ville.nomVille}</option>
                            </c:forEach>
                        </select>
                        <div class="span-error-div"><span class="span-error4" id="errorVilleModif"></span></div>
                    </div>

                    <c:if test="${sessionScope.role eq 'employe'}">
                        <input type="hidden" name="role" value="${utilisateur.rolesByIdRole.idRole}">
                    </c:if>
                    <c:if test="${!empty profilFlag }">
                        <input type="hidden" name="profilFlag" value="ok">
                    </c:if>
                    <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
                    <input type="submit" class="btn-modif2" value="Modifier">
                    <input type="button" class="btn-modif2" value="Retour" onclick=location.href="${pageContext.request.contextPath}/accueil">
                </form>
            </div>
        </div>

        <c:import url="footer.jsp"/>
    </body>
</html>
