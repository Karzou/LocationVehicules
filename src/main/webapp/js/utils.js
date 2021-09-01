function defineMinDate() {

    const dtToday = new Date();

    let month = dtToday.getMonth() + 1;
    let day = dtToday.getDate();
    const year = dtToday.getFullYear();

    if (month < 10)
        month = '0' + month.toString();
    if (day < 10)
        day = '0' + day.toString();

    let minDate = year + '-' + month + '-' + day;

    document.getElementById('dateDepart').setAttribute('min', minDate);
    document.getElementById('dateRetour').setAttribute('min', minDate);
}

function colorOnLoadHome() {

    if (document.getElementById('LieuDepart').value == "") {

        document.getElementById('LieuDepart').style.color = "#999";
    } else {

        document.getElementById('LieuDepart').style.color = "#000";
    }

    if (document.getElementById('LieuRetour').value == "") {

        document.getElementById('LieuRetour').style.color = "#999";
    } else {

        document.getElementById('LieuRetour').style.color = "#000";
    }

    if (document.getElementById('dateDepart').value == "") {

        document.getElementById('dateDepart').style.color = "#999";
    } else {

        document.getElementById('dateDepart').style.color = "#000";
    }

    if (document.getElementById('heureDepart').value == "") {

        document.getElementById('heureDepart').style.color = "#999";
    } else {

        document.getElementById('heureDepart').style.color = "#000";
    }

    if (document.getElementById('dateRetour').value == "") {

        document.getElementById('dateRetour').style.color = "#999";
    } else {

        document.getElementById('dateRetour').style.color = "#000";
    }

    if (document.getElementById('heureRetour').value == "") {

        document.getElementById('heureRetour').style.color = "#999";
    } else {

        document.getElementById('heureRetour').style.color = "#000";
    }
}

function showRegisterForm() {

    if(document.getElementById('container-register').style.display == 'block'){
        document.getElementById('container-register').style.display='none';
    }else{
        document.getElementById('container-register').style.display='block';
    }
}

function changeColor(element) {

    if (document.getElementById(element).value == '') {
        document.getElementById(element).style.color = "#999";
    } else {
        document.getElementById(element).style.color = "black";
    }
}

function resetSelectLieuDepart() {

    const lieuDepart = document.forms["searchVehicle"]["LieuDepart"];

    if (lieuDepart.value != "" || lieuDepart.value != null) {

        lieuDepart.removeAttribute('style');
        document.getElementById('lieu-depart-error').innerHTML = "";
    }
}

function resetSelectLieuRetour() {

    const lieuRetour = document.forms["searchVehicle"]["LieuRetour"];

    if (lieuRetour.value != "" || lieuRetour.value != null) {

        lieuRetour.removeAttribute('style');
        document.getElementById('lieu-retour-error').innerHTML = "";
    }
}

function resetInputDateDepart() {

    const dateDepart = document.forms["searchVehicle"]["dateDepart"];
    const heureDepart = document.forms["searchVehicle"]["heureDepart"];
    const error = document.getElementById('date-time-depart-error');

    if (((dateDepart.value != "" || dateDepart.value != null) && (heureDepart.value == "" || heureDepart.value == null)) && error.innerHTML != "") {

        dateDepart.removeAttribute('style');
        error.innerHTML = "Veuillez choisir une heure de d�part";
    } else if (((dateDepart.value != "" || dateDepart.value != null) && (heureDepart.value != "" || heureDepart.value != null)) && error.innerHTML != "") {

        dateDepart.removeAttribute('style');
        error.innerHTML = "";
    }
}

function resetInputHeureDepart() {

    const dateDepart = document.forms["searchVehicle"]["dateDepart"];
    const heureDepart = document.forms["searchVehicle"]["heureDepart"];
    const error = document.getElementById('date-time-depart-error');

    if (((dateDepart.value == "" || dateDepart.value == null) && (heureDepart.value != "" || heureDepart.value != null)) && error.innerHTML != "") {

        heureDepart.removeAttribute('style');
        error.innerHTML = "Veuillez choisir une date de d�part";
    } else if (((dateDepart.value != "" || dateDepart.value != null) && (heureDepart.value != "" || heureDepart.value != null)) && error.innerHTML != "") {

        heureDepart.removeAttribute('style');
        error.innerHTML = "";
    }
}

function resetInputDateRetour() {

    const dateRetour = document.forms["searchVehicle"]["dateRetour"];
    const heureRetour = document.forms["searchVehicle"]["heureRetour"];
    const error = document.getElementById('date-time-retour-error');

    if (((dateRetour.value != "" || dateRetour.value != null) && (heureRetour.value == "" || heureRetour.value == null)) && error.innerHTML != "") {

        dateRetour.removeAttribute('style');
        error.innerHTML = "Veuillez choisir une heure de retour";
    } else if (((dateRetour.value != "" || dateRetour.value != null) && (heureRetour.value != "" || heureRetour.value != null)) && error.innerHTML != "") {

        dateRetour.removeAttribute('style');
        error.innerHTML = "";
    }
}

function resetInputHeureRetour() {

    const dateRetour = document.forms["searchVehicle"]["dateRetour"];
    const heureRetour = document.forms["searchVehicle"]["heureRetour"];
    const error = document.getElementById('date-time-retour-error');

    if (((dateRetour.value == "" || dateRetour.value == null) && (heureRetour.value != "" || heureRetour.value != null)) && error.innerHTML != "") {

        heureRetour.removeAttribute('style');
        error.innerHTML = "Veuillez choisir une date de retour";
    } else if (((dateRetour.value != "" || dateRetour.value != null) && (heureRetour.value != "" || heureRetour.value != null)) && error.innerHTML != "") {

        heureRetour.removeAttribute('style');
        error.innerHTML = "";
    }
}

function validateSearchVehicle() {

    let result = true;

    const lieuDepart = document.forms["searchVehicle"]["LieuDepart"];
    const lieuRetour = document.forms["searchVehicle"]["LieuRetour"];
    const dateDepart = document.forms["searchVehicle"]["dateDepart"];
    const heureDepart = document.forms["searchVehicle"]["heureDepart"];
    const dateRetour = document.forms["searchVehicle"]["dateRetour"];
    const heureRetour = document.forms["searchVehicle"]["heureRetour"];

    if (lieuDepart.value == "" || lieuDepart.value == null) {
        document.getElementById('lieu-depart-error').innerHTML = "Veuillez selectionner un lieu de d�part";
        LieuDepart.style.border = "1px solid red";
        LieuDepart.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('lieu-depart-error').innerHTML = "";
        LieuDepart.removeAttribute('style');
    }

    if (lieuRetour.value == "" || lieuRetour.value == null) {
        document.getElementById('lieu-retour-error').innerHTML = "Veuillez selectionner un lieu de retour";
        lieuRetour.style.border = "1px solid red";
        lieuRetour.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('lieu-retour-error').innerHTML = "";
        lieuRetour.removeAttribute('style');
    }

    if ((dateDepart.value == "" || dateDepart.value == null) && (heureDepart.value == "" || heureDepart.value == null)) {
        document.getElementById('date-time-depart-error').innerHTML = "Veuillez choisir une date et heure de d�part";
        dateDepart.style.border = "1px solid red";
        dateDepart.style.boxShadow = "0 0 1px 2px red";
        heureDepart.style.border = "1px solid red";
        heureDepart.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (dateDepart.value == "" || dateDepart.value == null) {
        document.getElementById('date-time-depart-error').innerHTML = "Veuillez choisir une date de d�part";
        dateDepart.style.border = "1px solid red";
        dateDepart.style.boxShadow = "0 0 1px 2px red";
        heureDepart.removeAttribute('style');

        result = false;
    } else if (heureDepart.value == "" || heureDepart.value == null) {
        document.getElementById('date-time-depart-error').innerHTML = "Veuillez choisir une heure de d�part";
        heureDepart.style.border = "1px solid red";
        heureDepart.style.boxShadow = "0 0 1px 2px red";
        dateDepart.removeAttribute('style');

        result = false;
    } else {
        document.getElementById('date-time-depart-error').innerHTML = "";
        dateDepart.removeAttribute('style');
        heureDepart.removeAttribute('style');
    }

    if ((dateRetour.value == "" || dateRetour.value == null) && (heureRetour.value == "" || heureRetour.value == null)) {
        document.getElementById('date-time-retour-error').innerHTML = "Veuillez choisir une date et heure de retour";
        dateRetour.style.border = "1px solid red";
        dateRetour.style.boxShadow = "0 0 1px 2px red";
        heureRetour.style.border = "1px solid red";
        heureRetour.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (dateRetour.value == "" || dateRetour.value == null) {
        document.getElementById('date-time-retour-error').innerHTML = "Veuillez choisir une date de retour";
        dateRetour.style.border = "1px solid red";
        dateRetour.style.boxShadow = "0 0 1px 2px red";
        heureRetour.removeAttribute('style');

        result = false;
    } else if (heureRetour.value == "" || heureRetour.value == null) {
        document.getElementById('date-time-retour-error').innerHTML = "Veuillez choisir une heure de retour";
        heureRetour.style.border = "1px solid red";
        heureRetour.style.boxShadow = "0 0 1px 2px red";
        dateRetour.removeAttribute('style');

        result = false;
    } else {
        document.getElementById('date-time-retour-error').innerHTML = "";
        dateRetour.removeAttribute('style');
        heureRetour.removeAttribute('style');
    }

    return result;
}

function ValidateEmail(inputText)
{
    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if(inputText.value.match(mailformat))
    {
        return true;
    }
    else
    {
        return false;
    }
}

function validateLogin() {

    let result = true;

    const mail = document.forms["form"]["username"];
    const password = document.forms["form"]["password"];

    if (mail.value == "" || mail.value == null) {
        document.getElementById('errorMailLogin').innerHTML = "Veuillez entrer une adresse email";
        mail.style.border = "1px solid red";
        mail.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (mail.value.length > 100 || !ValidateEmail(mail)) {
        document.getElementById('errorMailLogin').innerHTML = "Veuillez entrer un email valide";
        mail.style.border = "1px solid red";
        mail.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('errorMailLogin').innerHTML = "";
        mail.removeAttribute('style');
    }

    if (password.value == "" || password.value == null) {
        document.getElementById('errorPasswordLogin').innerHTML = "Veuillez entrer un mot de passe";
        password.style.border = "1px solid red";
        password.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (password.value == "" || password.value.length < 4 || password.value.length > 256 ) {
        document.getElementById('errorPasswordLogin').innerHTML = "Veuillez entrer un mot de passe valide entre 4 et 256 caracteres";
        password.style.border = "1px solid red";
        password.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('errorPasswordLogin').innerHTML = "";
        password.removeAttribute('style');
    }

    return result;
}

function validateCreationUtilisateur() {

    let result = true;

    const nom = document.forms["createUser"]["register-nom"];
    const prenom = document.forms["createUser"]["register-prenom"];
    const mail = document.forms["createUser"]["register-mail"];
    const password = document.forms["createUser"]["register-password"];
    const confirmPassword = document.forms["createUser"]["register-confirmPassword"];
    const telephone = document.forms["createUser"]["register-telephone"];
    const rue = document.forms["createUser"]["register-rue"];
    const numero = document.forms["createUser"]["register-numero"];
    const boite = document.forms["createUser"]["register-boite"];
    const ville = document.forms["createUser"]["register-ville"];
    const dateNaissance = document.forms["createUser"]["register-dateNaissance"];
    const datePermis = document.forms["createUser"]["register-datePermis"];

    if (nom.value == "" || nom.value == null) {
        document.getElementById("errorNomCreate").innerHTML = "Veuillez entrer un nom";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (nom.value.length < 2 || nom.value.length > 100) {
        document.getElementById("errorNomCreate").innerHTML = "Veuillez entrer un nom valide entre 2 et 100 caract�res";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNomCreate").innerHTML = "";
        nom.removeAttribute('style');
    }

    if (prenom.value == "" || prenom.value == null) {
        document.getElementById("errorPrenomCreate").innerHTML = "Veuillez entrer un pr�nom";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (prenom.value.length < 2 || prenom.value.length > 100) {
        document.getElementById("errorPrenomCreate").innerHTML = "Veuillez entrer un pr�nom valide entre 2 et 100 caract�res";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPrenomCreate").innerHTML = "";
        prenom.removeAttribute('style');
    }

    if (mail.value == "" || mail.value == null) {
        document.getElementById("errorMailCreate").innerHTML = "Veuillez entrer une adresse email";
        mail.style.border = "1px solid red";
        mail.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (mail.value.length > 100 || !ValidateEmail(mail)) {
        document.getElementById("errorMailCreate").innerHTML = "Veuillez entrer un email valide";
        mail.style.border = "1px solid red";
        mail.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorMailCreate").innerHTML = "";
        mail.removeAttribute('style');
    }

    if (password.value == "" || password.value == null) {
        document.getElementById("errorPasswordCreate").innerHTML = "Veuillez entrer un mot de passe";
        password.style.border = "1px solid red";
        password.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (password.value.length < 4 || password.value.length > 256) {
        document.getElementById("errorPasswordCreate").innerHTML = "Veuillez entrer un mot de passe valide entre 4 et 256 caract�res";
        password.style.border = "1px solid red";
        password.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPasswordCreate").innerHTML = "";
        password.removeAttribute('style');
    }

    if (confirmPassword.value == "" || confirmPassword.value == null) {
        document.getElementById("errorConfirmPasswordCreate").innerHTML = "Veuillez confirmer le mot de passe";
        confirmPassword.style.border = "1px solid red";
        confirmPassword.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (Password.value !== confirmPassword.value) {
        document.getElementById("errorConfirmPasswordCreate").innerHTML = "Les mots de passe ne sont pas identiques";
        confirmPassword.style.border = "1px solid red";
        confirmPassword.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorConfirmPasswordCreate").innerHTML = "";
        confirmPassword.removeAttribute('style');
    }

    if (telephone.value == "" || telephone.value == null) {
        document.getElementById("errorTelephoneCreate").innerHTML = "Veuillez entrer un num�ro de t�l�phone";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (telephone.value.length <= 6 || telephone.value.length > 50 || telephone.matches("\\+?[0-9]*")) {
        document.getElementById("errorTelephoneCreate").innerHTML = "Veuillez entrer un t�l�phone valide entre 2 et 50 caract�res";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorTelephoneCreate").innerHTML = "";
        telephone.removeAttribute('style');
    }

    if (rue.value == "" || rue.value == null) {
        document.getElementById("errorRueCreate").innerHTML = "Veuillez entrer un nom de rue";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (rue.value.length <= 6 || rue.value.length > 100) {
        document.getElementById("errorRueCreate").innerHTML = "Veuillez entrer une rue valide entre 6 et 100 caracteres";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorRueCreate").innerHTML = "";
        rue.removeAttribute('style');
    }

    if (numero.value == "" || numero.value == null) {
        document.getElementById("errorNumeroCreate").innerHTML = "Veuillez entrer un num�ro";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numero.value.length <= 1 || numero.value.length > 10) {
        document.getElementById("errorNumeroCreate").innerHTML = "Veuillez entrer un num�ro valide entre 1 et 10 caract�res";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumeroCreate").innerHTML = "";
        numero.removeAttribute('style');
    }

    if (boite.value.length > 50) {
        document.getElementById("errorBoiteCreate").innerHTML = "Veuillez entrer une boite valide inf�rieure � 50 caract�res";
        boite.style.border = "1px solid red";
        boite.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorBoiteCreate").innerHTML = "";
        boite.removeAttribute('style');
    }

    if (ville.value == "" || ville.value == null) {
        document.getElementById("errorVilleCreate").innerHTML = "Veuillez selectionner une ville";
        ville.style.border = "1px solid red";
        ville.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorVilleCreate").innerHTML = "";
        ville.removeAttribute('style');
    }

    if (dateNaissance.value == "" || dateNaissance.value == null) {
        document.getElementById("errorDateCreate").innerHTML = "Veuillez selectionner une date";
        dateNaissance.style.border = "1px solid red";
        dateNaissance.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorDateCreate").innerHTML = "";
        dateNaissance.removeAttribute('style');
    }

    if (datePermis.value == "" || datePermis.value == null) {
        document.getElementById("errorPermisCreate").innerHTML = "Veuillez selectionner une date";
        datePermis.style.border = "1px solid red";
        datePermis.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('errorPasswordLogin').innerHTML = "";
        datePermis.removeAttribute('style');
    }

    return result;
}

function validateModifUtilisateur() {

    let result = true;

    const nom = document.forms["modifUser"]["modif-nom"];
    const prenom = document.forms["modifUser"]["modif-prenom"];
    const telephone = document.forms["modifUser"]["modif-telephone"];
    const password = document.forms["modifUser"]["modif-password"];
    const dateNaissance = document.forms["modifUser"]["modif-password"];
    const datePermis = document.forms["modifUser"]["modif-password"];
    const rue = document.forms["modifUser"]["modif-rue"];
    const numero = document.forms["modifUser"]["modif-numero"];
    const boite = document.forms["modifUser"]["modif-boite"];

    if (nom.value == "" || nom.value == null) {
        document.getElementById("errorNomModif").innerHTML = "Veuillez entrer un nom";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else if (nom.value.length < 2 || nom.value.length > 100) {
        document.getElementById("errorNomModif").innerHTML = "Veuillez entrer un nom valide entre 2 et 100 caract�res";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorNomModif").innerHTML = "";
        nom.removeAttribute('style');
    }

    if (prenom.value == "" || prenom.value == null) {
        document.getElementById("errorPrenomModif").innerHTML = "Veuillez entrer un pr�nom";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else if (prenom.value.length < 2 || prenom.value.length > 100) {
        document.getElementById("errorPrenomModif").innerHTML = "Veuillez entrer un pr�nom valide entre 2 et 100 caract�res.";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorPrenomModif").innerHTML = "";
        prenom.removeAttribute('style');
    }

    if (telephone.value == "" || telephone.value == null) {
        document.getElementById("errorTelephoneModif").innerHTML = "Veuillez entrer un num�ro de t�l�phone";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else if (telephone.value.length <= 6 || telephone.value.length > 50 || telephone.matches("\\+?[0-9]*")) {
        document.getElementById("errorTelephoneModif").innerHTML = "Veuillez entrer un t�l�phone valide entre 2 et 50 caract�res";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorTelephoneModif").innerHTML = "";
        telephone.removeAttribute('style');
    }

    if (dateNaissance.value == "" || dateNaissance.value == null) {
        alert("date naissance null ou vide");
        document.getElementById("errorRueModif").innerHTML = "Veuillez selectionner une date";
        dateNaissance.style.border = "1px solid red";
        dateNaissance.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorRueModif").innerHTML = "";
        dateNaissance.removeAttribute('style');
    }

    if (datePermis.value == "" || datePermis.value == null) {
        alert("date permis null ou vide");
        document.getElementById("errorRueModif").innerHTML = "Veuillez selectionner une date";
        datePermis.style.border = "1px solid red";
        datePermis.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorRueModif").innerHTML = "";
        datePermis.removeAttribute('style');
    }

    if (rue.value == "" || rue.value == null) {
        document.getElementById("errorRueModif").innerHTML = "Veuillez entrer un nom de rue";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else if (rue.value.length <= 6 || rue.value.length > 100) {
        document.getElementById("errorRueModif").innerHTML = "Veuillez entrer une rue valide entre 6 et 100 caract�res";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorRueModif").innerHTML = "";
        rue.removeAttribute('style');
    }

    if (numero.value == "" || numero.value == null) {
        document.getElementById("errorNumModif").innerHTML = "Veuillez entrer un num�ro";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else if (numero.value.length <= 1 || numero.value.length > 10) {
        document.getElementById("errorNumModif").innerHTML = "Veuillez entrer un numero valide entre 1 et 10 caract�res";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorNumModif").innerHTML = "";
        numero.removeAttribute('style');
    }

    if (boite.value.length > 50 ) {
        document.getElementById("errorBoiteModif").innerHTML = "Veuillez entrer une boite valide entre 2 et 50 caract�res";
        boite.style.border = "1px solid red";
        boite.style.boxShadow = "0 0 1px 2px red";

        return false;
    } else {
        document.getElementById("errorBoiteModif").innerHTML = "";
        boite.removeAttribute('style');
    }

    return result;
}

function validateModifMotDePasse() {

    let result = true;

    const mail = document.forms["motDePasseOublie"]["reset-password-input"];

    if (mail.value == "" || mail.value == null) {
        document.getElementById("errorMotDePasseModif").innerHTML = "Veuillez entrer une adresse email";
        mail.style.border = "1px solid red";
        mail.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (mail.value.length > 100 || !ValidateEmail(mail)) {
        document.getElementById("errorMotDePasseModif").innerHTML = "Veuillez entrer un email valide";
        mail.style.border = "1px solid red";
        mail.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorMotDePasseModif").innerHTML = "";
        mail.removeAttribute('style');
    }

    return result;
}
