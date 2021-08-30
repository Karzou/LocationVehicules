<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>

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

                <form action="<c:url value="/gestionUtilisateur"/>" id="modifUser" name="modifUser" method="post" onsubmit="return validateModifUser()">
                    <div class="form-group"id="form-modif">
                        <label>Nom : </label>
                        <input class="input-modif" type="text" name="nom" id="nom" value="${utilisateur.nomUtilisateur}">
                        <span class="error" id="errorNomModif"></span></p>
                    </div>
                    <div class="form-group"id="form-modif2">
                        <label>Prenom : </label>
                        <input class="input-modif" type="text" id="prenom" name="prenom" value="${utilisateur.prenomUtilisateur}">
                        <span class="error" id="errorPrenomModif"></span></p>
                    </div>

                    <c:forEach items="${utilisateur.telephonesByIdUtilisateur}" var="telephone">
                        <div class="form-group"id="form-modif11">
                            <label>Telephone : </label>
                            <input class="input-modif" type="text" id="telephone" name="telephone" value="${telephone.numero}">
                            <span class="error" id="errorTelephoneModif"></span></p>
                        </div>
                    </c:forEach>

                    <div class="form-group"id="form-modif12">
                        <label>Password : </label>
                        <select class="input-modif" name="password">
                            <option value="${utilisateur.motDePasse}">${utilisateur.motDePasse}</option>
                            <option value="0000">Reset</option>
                        </select>
                        <c:if test="${sessionScope.idUtilisateur == utilisateur.idUtilisateur}">
                            <a href="changerMotDePasse">Changer mot de passe</a>
                        </c:if>
                    </div>
                    <div class="form-group"id="form-modif9">
                        <label>Date de naissance : </label>
                        <input class="input-modif" type="date" name="dateNaissance" value="${utilisateur.dateNaissance}">
                    </div>
                    <div class="form-group"id="form-modif10">
                        <label>Permis : </label>
                        <input class="input-modif" type="date" name="datePermis" value="${utilisateur.datePermis}">
                    </div>

                    <div class="form-group"id="form-modif4">
                        <label>Rue : </label>
                        <input class="input-modif" type="text" id="rue" name="rue" value="${utilisateur.adressesByIdAdresse.rue}">
                        <span class="error" id="errorRueModif"></span></p>
                    </div>
                    <div class="form-group"id="form-modif5">
                        <label>Numero : </label>
                        <input class="input-modif" type="text" id="numero" name="numero" value="${utilisateur.adressesByIdAdresse.numero}">
                        <span class="error" id="errorNumModif"></span></p>
                    </div>
                    <div class="form-group"id="form-modif6">
                        <label>Boite : </label>
                        <input class="input-modif" type="text" id="boite" name="boite" value="${utilisateur.adressesByIdAdresse.boite}">
                        <span class="error" id="errorBoiteModif"></span></p>
                    </div>

                    <div class="register-div">
                        <label for="register-ville">Ville : </label>
                        <select class="input-modif" id="register-ville" name="ville" value="${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}">
                            <option value="${utilisateur.adressesByIdAdresse.villesByIdVille.idVille}">${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}</option>
                            <c:forEach items="${villes}" var="ville">
                                <option value="${ville.idVille}">${ville.nomVille}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">

                    <c:if test="${sessionScope.role eq 'employe'}">
                        <input type="hidden" name="role" value="${utilisateur.rolesByIdRole.idRole}">
                    </c:if>
                    <c:if test="${!empty profilFlag }">
                        <input type="hidden" name="profilFlag" value="ok">
                    </c:if>
                    <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}">
                    <button type="submit" value="Envoyer"id="bouton-modif" class="btn btn-primary">Modifier</button>
                </form>
            </div>
        </div>

        <c:import url="footer.jsp"/>
    </body>
</html>
