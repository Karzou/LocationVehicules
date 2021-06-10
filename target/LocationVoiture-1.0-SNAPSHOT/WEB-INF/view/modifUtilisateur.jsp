<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Modification utilisateur</title>
        <style><%@ include file="/css/style.css"%></style>
    </head>

    <body>
        <div class="content_modif">
            <jsp:include page="menu.jsp" />

            <div class = "modif">
                <form action="<c:url value="/gestionUtilisateur"/>" method="post">
                    <div class="form-group"id="form-modif">
                        <label>Nom : </label>
                        <input class="form-control" type="text" name="nom" value="${utilisateur.nomUtilisateur}">
                    </div>
                    <div class="form-group"id="form-modif2">
                        <label>Prenom : </label>
                        <input class="form-control" type="text" name="prenom" value="${utilisateur.prenomUtilisateur}">
                    </div>
                    <div class="form-group"id="form-modif11">
                        <label>Email : </label>
                        <input class="form-control" type="text" name="email" value="${utilisateur.email}">
                    </div>
                    <div class="form-group"id="form-modif12">
                        <label>Password : </label>
                        <input class="form-control" type="text" name="password" value="${utilisateur.motDePasse}">
                    </div>
                    <div class="form-group"id="form-modif9">
                        <label>Date de naissance : </label>
                        <input class="form-control" type="date" name="dateNaissance" value="${utilisateur.dateNaissance}">
                    </div>
                    <div class="form-group"id="form-modif10">
                        <label>Permis : </label>
                        <input class="form-control" type="date" name="datePermis" value="${utilisateur.datePermis}">
                    </div>
                    <div class="form-group"id="form-modif3">
                        <label>Statut actif : </label>
                        <input class="form-control" type="text" name="actif" value="${utilisateur.actifUtilisateur}">
                    </div>
                    <div class="form-group"id="form-modif4">
                        <label>Rue : </label>
                        <input class="form-control" type="text" name="rue" value="${utilisateur.adressesByIdAdresse.rue}">
                    </div>
                    <div class="form-group"id="form-modif5">
                        <label>Numero : </label>
                        <input class="form-control" type="text" name="numero" value="${utilisateur.adressesByIdAdresse.numero}">
                    </div>
                    <div class="form-group"id="form-modif6">
                        <label>Boite : </label>
                        <input class="form-control" type="text" name="boite" value="${utilisateur.adressesByIdAdresse.boite}">
                    </div>
                    <div class="form-group"id="form-modif7">
                        <label>Ville : </label>
                        <input class="form-control" type="text" name="ville" value="${utilisateur.adressesByIdAdresse.villesByIdVille.nomVille}">
                    </div>
                    <div class="form-group"id="form-modif8">
                        <label>Code postal : </label>
                        <input class="form-control" type="text" name="codepostal" value="${utilisateur.adressesByIdAdresse.villesByIdVille.codePostal}">
                    </div>

                    <c:if test="${sessionScope.role eq 'admin'}">
                        <div class="form-group"id="form-modif8">
                            <label>Role : </label>
                            <select class="form-control" name="role" id="pet-select">
                                <option value="${utilisateur.rolesByIdRole.idRole}">${utilisateur.rolesByIdRole.roleDescription}</option>
                                <option value="1">admin</option>
                                <option value="3">employe</option>
                                <option value="2">client</option>
                            </select>
                        </div>
                    </c:if>

                    <input type="hidden" name="idModif" value="${utilisateur.idUtilisateur}"></input>
                    <button type="submit" value="Envoyer"id="bouton-modif" class="btn btn-primary">Modifer</button>
                </form>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
