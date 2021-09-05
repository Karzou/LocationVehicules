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

    if(inputText.value.match(mailformat)) {

        return true;
    } else {

        return false;
    }
}

function ValidateTelephone(inputText) {

    let telephoneformat = /^\+?[0-9]+/;

    if(inputText.value.match(telephoneformat)) {

        return true;
    } else {

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
    } else if (password.value == "" || password.value.length < 4 || password.value.length > 256) {
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
        document.getElementById("errorNomCreate").innerHTML = "Veuillez entrer un nom valide entre 2 et 100 caracteres";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNomCreate").innerHTML = "";
        nom.removeAttribute('style');
    }

    if (prenom.value == "" || prenom.value == null) {
        document.getElementById("errorPrenomCreate").innerHTML = "Veuillez entrer un prenom";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (prenom.value.length < 2 || prenom.value.length > 100) {
        document.getElementById("errorPrenomCreate").innerHTML = "Veuillez entrer un prenom valide entre 2 et 100 caracteres";
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
        document.getElementById("errorPasswordCreate").innerHTML = "Veuillez entrer un mot de passe valide entre 4 et 256 caracteres";
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

    } else {
        document.getElementById("errorConfirmPasswordCreate").innerHTML = "";
        confirmPassword.removeAttribute('style');
    }
    if (password.value !== confirmPassword.value) {
        document.getElementById("errorConfirmPasswordCreate").innerHTML = "Les mots de passe ne sont pas identiques";
        confirmPassword.style.border = "1px solid red";
        confirmPassword.style.boxShadow = "0 0 1px 2px red";

        result = false;
    }else {
        document.getElementById("errorConfirmPasswordCreate").innerHTML = "";
        confirmPassword.removeAttribute('style');
    }

    if (telephone.value == "" || telephone.value == null) {
        document.getElementById("errorTelephoneCreate").innerHTML = "Veuillez entrer un numero de telephone";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (telephone.value.length < 8 || telephone.value.length > 50 || telephone.matches("\\+?[0-9]*")) {
        document.getElementById("errorTelephoneCreate").innerHTML = "Veuillez entrer un telephone valide entre 8 et 50 caracteres";
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
    } else if (rue.value.length < 6 || rue.value.length > 100) {
        document.getElementById("errorRueCreate").innerHTML = "Veuillez entrer une rue valide entre 6 et 100 caracteres";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorRueCreate").innerHTML = "";
        rue.removeAttribute('style');
    }

    if (numero.value == "" || numero.value == null) {
        document.getElementById("errorNumeroCreate").innerHTML = "Veuillez entrer un numero";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numero.value.length > 10) {
        document.getElementById("errorNumeroCreate").innerHTML = "Veuillez entrer un numero valide entre 1 et 10 caracteres";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumeroCreate").innerHTML = "";
        numero.removeAttribute('style');
    }

    if (boite.value.length > 50) {
        document.getElementById("errorBoiteCreate").innerHTML = "Veuillez entrer une boite valide inferieure a 50 caracteres";
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
        document.getElementById("errorPermisCreate").innerHTML = "";
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
    const dateNaissance = document.forms["modifUser"]["modif-dateNaissance"];
    const datePermis = document.forms["modifUser"]["modif-datePermis"];
    const rue = document.forms["modifUser"]["modif-rue"];
    const numero = document.forms["modifUser"]["modif-numero"];
    const boite = document.forms["modifUser"]["modif-boite"];

    if (nom.value == "" || nom.value == null) {
        document.getElementById("errorNomModif").innerHTML = "Veuillez entrer un nom";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (nom.value.length < 2 || nom.value.length > 100) {
        document.getElementById("errorNomModif").innerHTML = "Veuillez entrer un nom valide entre 2 et 100 caracteres";
        nom.style.border = "1px solid red";
        nom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNomModif").innerHTML = "";
        nom.removeAttribute('style');
    }

    if (prenom.value == "" || prenom.value == null) {
        document.getElementById("errorPrenomModif").innerHTML = "Veuillez entrer un prenom";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (prenom.value.length < 2 || prenom.value.length > 100) {
        document.getElementById("errorPrenomModif").innerHTML = "Veuillez entrer un prenom valide entre 2 et 100 caracteres.";
        prenom.style.border = "1px solid red";
        prenom.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPrenomModif").innerHTML = "";
        prenom.removeAttribute('style');
    }

    if (telephone.value == "" || telephone.value == null) {
        document.getElementById("errorTelephoneModif").innerHTML = "Veuillez entrer un numero de telephone";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (telephone.value.length < 8 || telephone.value.length > 50) {
        document.getElementById("errorTelephoneModif").innerHTML = "Veuillez entrer un telephone valide entre 8 et 50 caracteres";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (!ValidateTelephone(telephone)) {
        document.getElementById("errorTelephoneModif").innerHTML = "Veuillez entrer un telephone valide";
        telephone.style.border = "1px solid red";
        telephone.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorTelephoneModif").innerHTML = "";
        telephone.removeAttribute('style');
    }

    if (dateNaissance.value == "" || dateNaissance.value == null) {
        document.getElementById("errorDateNaissanceModif").innerHTML = "Veuillez selectionner une date";
        dateNaissance.style.border = "1px solid red";
        dateNaissance.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorDateNaissanceModif").innerHTML = "";
        dateNaissance.removeAttribute('style');
    }

    if (datePermis.value == "" || datePermis.value == null) {
        document.getElementById("errorDatePermisModif").innerHTML = "Veuillez selectionner une date";
        datePermis.style.border = "1px solid red";
        datePermis.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorDatePermisModif").innerHTML = "";
        datePermis.removeAttribute('style');
    }

    if (rue.value == "" || rue.value == null) {
        document.getElementById("errorRueModif").innerHTML = "Veuillez entrer un nom de rue";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (rue.value.length < 6 || rue.value.length > 100) {
        document.getElementById("errorRueModif").innerHTML = "Veuillez entrer une rue valide entre 6 et 100 caract�res";
        rue.style.border = "1px solid red";
        rue.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorRueModif").innerHTML = "";
        rue.removeAttribute('style');
    }

    if (numero.value == "" || numero.value == null) {
        document.getElementById("errorNumModif").innerHTML = "Veuillez entrer un numero";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numero.value.length > 10) {
        document.getElementById("errorNumModif").innerHTML = "Veuillez entrer un numero valide entre 1 et 10 caract�res";
        numero.style.border = "1px solid red";
        numero.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumModif").innerHTML = "";
        numero.removeAttribute('style');
    }

    if (boite.value.length > 50 ) {
        document.getElementById("errorBoiteModif").innerHTML = "Veuillez entrer une boite valide entre 2 et 50 caract�res";
        boite.style.border = "1px solid red";
        boite.style.boxShadow = "0 0 1px 2px red";

        result = false;
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

function validateAjoutCouleur() {

    let result = true;

    const couleur = document.forms["ajoutCouleur"]["couleur-ajout-input"];

    if (couleur.value == "" || couleur.value == null) {
        document.getElementById("errorCouleurAjout").innerHTML = "Veuillez entrer une couleur";
        couleur.style.border = "1px solid red";
        couleur.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (couleur.value.length < 2 || couleur.value.length > 50 ) {
        document.getElementById("errorCouleurAjout").innerHTML = "Veuillez entrer un nom de couleur valide";
        couleur.style.border = "1px solid red";
        couleur.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorCouleurAjout").innerHTML = "";
        couleur.removeAttribute('style');
    }

    return result;
}

function validateModifCouleur() {

    let result = true;

    const couleur = document.forms["modifCouleur"]["couleur-modif-input"];

    if (couleur.value == "" || couleur.value == null) {
        document.getElementById("errorCouleurModif").innerHTML = "Veuillez entrer une couleur";
        couleur.style.border = "1px solid red";
        couleur.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (couleur.value.length < 2 || couleur.value.length > 50 ) {
        document.getElementById("errorCouleurModif").innerHTML = "Veuillez entrer un nom de couleur valide";
        couleur.style.border = "1px solid red";
        couleur.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorCouleurModif").innerHTML = "";
        couleur.removeAttribute('style');
    }

    return result;
}

function validateAjoutEntrepot() {

    let result = true;

    const nomEntrepot = document.forms["ajoutEntrepot"]["nomEntrepot-ajout-input"];
    const nombrePlaceEntrepot = document.forms["ajoutEntrepot"]["nombrePlaceEntrepot-ajout-input"];
    const rueEntrepot = document.forms["ajoutEntrepot"]["rueEntrepot-ajout-input"];
    const numeroEntrepot = document.forms["ajoutEntrepot"]["numeroEntrepot-ajout-input"];
    const boiteEntrepot = document.forms["ajoutEntrepot"]["boiteEntrepot-ajout-input"];
    const villeEntrepot = document.forms["ajoutEntrepot"]["villeEntrepot-ajout-input"];

    if (nomEntrepot.value == "" || nomEntrepot.value == null) {
        document.getElementById("errorNomEntrepotAjout").innerHTML = "Veuillez entrer un nom d'entrep�t";
        nomEntrepot.style.border = "1px solid red";
        nomEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if ((nomEntrepot.value.length < 2) || (nomEntrepot.value.length > 50)) {
        document.getElementById("errorNomEntrepotAjout").innerHTML = "Veuillez entrer un nom d'entrep�t valide";
        nomEntrepot.style.border = "1px solid red";
        nomEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNomEntrepotAjout").innerHTML = "";
        nomEntrepot.removeAttribute('style');
    }

    if (nombrePlaceEntrepot.value == "" || nombrePlaceEntrepot.value == null) {
        document.getElementById("errorPlaceEntrepotAjout").innerHTML = "Veuillez entrer un nombre de place";
        nombrePlaceEntrepot.style.border = "1px solid red";
        nombrePlaceEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (nombrePlaceEntrepot.value.length < 2 || nombrePlaceEntrepot.value.length > 50 ) {
        document.getElementById("errorPlaceEntrepotAjout").innerHTML = "Veuillez entrer un nombre de place valide";
        nombrePlaceEntrepot.style.border = "1px solid red";
        nombrePlaceEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPlaceEntrepotAjout").innerHTML = "";
        nombrePlaceEntrepot.removeAttribute('style');
    }

    if (rueEntrepot.value == "" || rueEntrepot.value == null) {
        document.getElementById("errorRueEntrepotAjout").innerHTML = "Veuillez entrer un nom de rue";
        rueEntrepot.style.border = "1px solid red";
        rueEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (rueEntrepot.value.length < 2 || rueEntrepot.value.length > 50 ) {
        document.getElementById("errorRueEntrepotAjout").innerHTML = "Veuillez entrer un nom de rue valide";
        rueEntrepot.style.border = "1px solid red";
        rueEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorRueEntrepotAjout").innerHTML = "";
        rueEntrepot.removeAttribute('style');
    }

    if (numeroEntrepot.value == "" || numeroEntrepot.value == null) {
        document.getElementById("errorNumeroEntrepotAjout").innerHTML = "Veuillez entrer un numero";
        numeroEntrepot.style.border = "1px solid red";
        numeroEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numeroEntrepot.value.length <= 1 || numeroEntrepot.value.length > 10) {
        document.getElementById("errorNumeroEntrepotAjout").innerHTML = "Veuillez entrer un numero valide";
        numeroEntrepot.style.border = "1px solid red";
        numeroEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumeroEntrepotAjout").innerHTML = "";
        numeroEntrepot.removeAttribute('style');
    }

    if (boiteEntrepot.value.length > 10 ) {
        document.getElementById("errorBoiteEntrepotAjout").innerHTML = "Veuillez entrer une boite valide";
        boiteEntrepot.style.border = "1px solid red";
        boiteEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorBoiteEntrepotAjout").innerHTML = "";
        boiteEntrepot.removeAttribute('style');
    }

    if (villeEntrepot.value == "" || villeEntrepot.value == null) {
        document.getElementById("errorVilleEntrepotAjout").innerHTML = "Veuillez selectionner une ville";
        villeEntrepot.style.border = "1px solid red";
        villeEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorVilleEntrepotAjout").innerHTML = "";
        villeEntrepot.removeAttribute('style');
    }

    return result;
}

function validateModifEntrepot() {

    let result = true;

    const nomEntrepot = document.forms["modifEntrepot"]["nomEntrepot-modif-input"];
    const nombrePlaceEntrepot = document.forms["modifEntrepot"]["nombrePlaceEntrepot-modif-input"];
    const rueEntrepot = document.forms["modifEntrepot"]["rueEntrepot-modif-input"];
    const numeroEntrepot = document.forms["modifEntrepot"]["numeroEntrepot-modif-input"];
    const boiteEntrepot = document.forms["modifEntrepot"]["boiteEntrepot-modif-input"];

    if (nomEntrepot.value == "" || nomEntrepot.value == null) {
        document.getElementById("errorNomEntrepotModif").innerHTML = "Veuillez entrer un nom d'entrep�t";
        nomEntrepot.style.border = "1px solid red";
        nomEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if ((nomEntrepot.value.length < 2) || (nomEntrepot.value.length > 50)) {
        document.getElementById("errorNomEntrepotModif").innerHTML = "Veuillez entrer un nom d'entrep�t valide";
        nomEntrepot.style.border = "1px solid red";
        nomEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNomEntrepotModif").innerHTML = "";
        couleur.removeAttribute('style');
    }

    if (nombrePlaceEntrepot.value == "" || nombrePlaceEntrepot.value == null) {
        document.getElementById("errorPlaceEntrepotModif").innerHTML = "Veuillez entrer un nombre de place";
        nombrePlaceEntrepot.style.border = "1px solid red";
        nombrePlaceEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (nombrePlaceEntrepot.value.length < 2 || nombrePlaceEntrepot.value.length > 50 ) {
        document.getElementById("errorPlaceEntrepotModif").innerHTML = "Veuillez entrer un nombre de place valide";
        nombrePlaceEntrepot.style.border = "1px solid red";
        nombrePlaceEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPlaceEntrepotModif").innerHTML = "";
        nombrePlaceEntrepot.removeAttribute('style');
    }

    if (rueEntrepot.value == "" || rueEntrepot.value == null) {
        document.getElementById("errorRueEntrepotModif").innerHTML = "Veuillez entrer un nom de rue";
        rueEntrepot.style.border = "1px solid red";
        rueEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (rueEntrepot.value.length < 2 || rueEntrepot.value.length > 50 ) {
        document.getElementById("errorRueEntrepotModif").innerHTML = "Veuillez entrer un nom de rue valide";
        rueEntrepot.style.border = "1px solid red";
        rueEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorRueEntrepotModif").innerHTML = "";
        rueEntrepot.removeAttribute('style');
    }

    if (numeroEntrepot.value == "" || numeroEntrepot.value == null) {
        document.getElementById("errorNumeroEntrepotModif").innerHTML = "Veuillez entrer un num�ro";
        numeroEntrepot.style.border = "1px solid red";
        numeroEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numeroEntrepot.value.length <= 1 || numeroEntrepot.value.length > 10) {
        document.getElementById("errorNumeroEntrepotModif").innerHTML = "Veuillez entrer un num�ro valide";
        numeroEntrepot.style.border = "1px solid red";
        numeroEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumeroEntrepotModif").innerHTML = "";
        numeroEntrepot.removeAttribute('style');
    }

    if (boiteEntrepot.value.length > 10 ) {
        document.getElementById("errorBoiteEntrepotModif").innerHTML = "Veuillez entrer une boite valide";
        boiteEntrepot.style.border = "1px solid red";
        boiteEntrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorBoiteEntrepotModif").innerHTML = "";
        boiteEntrepot.removeAttribute('style');
    }

    return result;
}

function validateAjoutMarque() {

    let result = true;

    const marque = document.forms["ajoutMarque"]["marque-ajout-input"];

    if (marque.value == "" || marque.value == null) {
        document.getElementById("errorMarqueAjout").innerHTML = "Veuillez entrer une marque";
        marque.style.border = "1px solid red";
        marque.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (marque.value.length < 2 || marque.value.length > 50 ) {
        document.getElementById("errorMarqueAjout").innerHTML = "Veuillez entrer un nom de marque valide";
        marque.style.border = "1px solid red";
        marque.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorMarqueAjout").innerHTML = "";
        marque.removeAttribute('style');
    }

    return result;
}

function validateModifMarque() {

    let result = true;

    const marque = document.forms["modifMarque"]["marque-modif-input"];

    if (marque.value == "" || marque.value == null) {
        document.getElementById("errorMarqueModif").innerHTML = "Veuillez entrer une marque";
        marque.style.border = "1px solid red";
        marque.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (marque.value.length < 2 || marque.value.length > 50 ) {
        document.getElementById("errorMarqueModif").innerHTML = "Veuillez entrer un nom de marque valide";
        marque.style.border = "1px solid red";
        marque.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorMarqueModif").innerHTML = "";
        marque.removeAttribute('style');
    }

    return result;
}

function validateAjoutModele() {

    let result = true;

    const marque = document.forms["ajoutModele"]["marque-ajout-input2"];
    const modele = document.forms["ajoutModele"]["modele-ajout-input"];

    if (marque.value == "" || marque.value == null) {
        document.getElementById("errorMarqueAjout2").innerHTML = "Veuillez selectionner une marque";
        marque.style.border = "1px solid red";
        marque.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorMarqueAjout2").innerHTML = "";
        marque.removeAttribute('style');
    }

    if (modele.value == "" || modele.value == null) {
        document.getElementById("errorModeleAjout").innerHTML = "Veuillez entrer un mod�le";
        modele.style.border = "1px solid red";
        modele.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (modele.value.length < 2 || modele.value.length > 50 ) {
        document.getElementById("errorModeleAjout").innerHTML = "Veuillez entrer un nom de mod�le valide";
        modele.style.border = "1px solid red";
        modele.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorModeleAjout").innerHTML = "";
        modele.removeAttribute('style');
    }

    return result;
}

function validateModifModele() {

    let result = true;

    const modele = document.forms["modifModele"]["modele-modif-input"];

    if (modele.value == "" || modele.value == null) {
        document.getElementById("errorModeleModif").innerHTML = "Veuillez entrer un mod�le";
        modele.style.border = "1px solid red";
        modele.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (modele.value.length < 2 || modele.value.length > 50 ) {
        document.getElementById("errorModeleModif").innerHTML = "Veuillez entrer un nom de mod�le valide";
        modele.style.border = "1px solid red";
        modele.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorModeleModif").innerHTML = "";
        modele.removeAttribute('style');
    }

    return result;
}

function validateAjoutVehicule() {

    let result = true;

    const marque = document.forms["ajoutVehicule"]["marque-ajout-input"];
    const modele = document.forms["ajoutVehicule"]["modele-ajout-input"];
    const cylindree = document.forms["ajoutVehicule"]["cylindree-ajout-input"];
    const puissance = document.forms["ajoutVehicule"]["puissance-ajout-input"];
    const numChassis = document.forms["ajoutVehicule"]["numChassis-ajout-input"];
    const immatriculation = document.forms["ajoutVehicule"]["immatriculation-ajout-input"];
    const dateAchat = document.forms["ajoutVehicule"]["dateAchat-ajout-input"];
    const prixJournalier = document.forms["ajoutVehicule"]["prixJournalier-ajout-input"];
    const couleur = document.forms["ajoutVehicule"]["couleur-ajout-input"];
    const entrepot = document.forms["ajoutVehicule"]["entrepot-ajout-input"];

    if (marque.value == "" || marque.value == null) {
        document.getElementById("errorMarqueAjout").innerHTML = "Veuillez selectionner une marque";
        marque.style.border = "1px solid red";
        marque.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorMarqueAjout").innerHTML = "";
        marque.removeAttribute('style');
    }

    if (modele.value == "" || modele.value == null) {
        document.getElementById("errorModeleAjout").innerHTML = "Veuillez selectionner un mod�le";
        modele.style.border = "1px solid red";
        modele.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorModeleAjout").innerHTML = "";
        modele.removeAttribute('style');
    }

    if (cylindree.value == "" || cylindree.value == null) {
        document.getElementById("errorCylindreeAjout").innerHTML = "Veuillez entrer une cylindr�e";
        cylindree.style.border = "1px solid red";
        cylindree.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (cylindree.value.length < 2 || cylindree.value.length > 50 ) {
        document.getElementById("errorCylindreeAjout").innerHTML = "Veuillez entrer une cylindr�e valide";
        cylindree.style.border = "1px solid red";
        cylindree.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorCylindreeAjout").innerHTML = "";
        cylindree.removeAttribute('style');
    }

    if (puissance.value == "" || puissance.value == null) {
        document.getElementById("errorPuissanceAjout").innerHTML = "Veuillez entrer une puissance";
        puissance.style.border = "1px solid red";
        puissance.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (puissance.value.length < 2 || puissance.value.length > 50 ) {
        document.getElementById("errorPuissanceAjout").innerHTML = "Veuillez entrer une puissance valide";
        puissance.style.border = "1px solid red";
        puissance.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPuissanceAjout").innerHTML = "";
        puissance.removeAttribute('style');
    }

    if (numChassis.value == "" || numChassis.value == null) {
        document.getElementById("errorNumChassisAjout").innerHTML = "Veuillez entrer un num�ro de chassis";
        numChassis.style.border = "1px solid red";
        numChassis.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numChassis.value.length < 2 || numChassis.value.length > 50 ) {
        document.getElementById("errorNumChassisAjout").innerHTML = "Veuillez entrer un num�ro de chassis valide";
        numChassis.style.border = "1px solid red";
        numChassis.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumChassisAjout").innerHTML = "";
        numChassis.removeAttribute('style');
    }

    if (immatriculation.value == "" || immatriculation.value == null) {
        document.getElementById("errorImmatriculationAjout").innerHTML = "Veuillez entrer une immatriculation";
        immatriculation.style.border = "1px solid red";
        immatriculation.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (immatriculation.value.length < 2 || immatriculation.value.length > 50 ) {
        document.getElementById("errorImmatriculationAjout").innerHTML = "Veuillez entrer une immatriculation valide";
        immatriculation.style.border = "1px solid red";
        immatriculation.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorImmatriculationAjout").innerHTML = "";
        immatriculation.removeAttribute('style');
    }

    if (dateAchat.value == "" || dateAchat.value == null) {
        document.getElementById("errorDateAchatAjout").innerHTML = "Veuillez selectionner une date";
        dateAchat.style.border = "1px solid red";
        dateAchat.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorDateAchatAjout").innerHTML = "";
        dateAchat.removeAttribute('style');
    }

    if (prixJournalier.value == "" || prixJournalier.value == null) {
        document.getElementById("errorPrixJournalierAjout").innerHTML = "Veuillez entrer un prix journalier";
        prixJournalier.style.border = "1px solid red";
        prixJournalier.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (prixJournalier.value.length < 2 || prixJournalier.value.length > 50 ) {
        document.getElementById("errorPrixJournalierAjout").innerHTML = "Veuillez entrer un prix journalier valide";
        prixJournalier.style.border = "1px solid red";
        prixJournalier.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPrixJournalierAjout").innerHTML = "";
        prixJournalier.removeAttribute('style');
    }

    if (couleur.value == "" || couleur.value == null) {
        document.getElementById("errorCouleurAjout").innerHTML = "Veuillez entrer une cylindr�e";
        couleur.style.border = "1px solid red";
        couleur.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorCouleurAjout").innerHTML = "";
        couleur.removeAttribute('style');
    }

    if (entrepot.value == "" || entrepot.value == null) {
        document.getElementById("errorEntrepotAjout").innerHTML = "Veuillez entrer une cylindr�e";
        entrepot.style.border = "1px solid red";
        entrepot.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorEntrepotAjout").innerHTML = "";
        entrepot.removeAttribute('style');
    }

    return result;
}

function validateModifVehicule() {

    let result = true;

    const cylindree = document.forms["modifVehicule"]["cylindree-modif-input"];
    const puissance = document.forms["modifVehicule"]["puissance-modif-input"];
    const numChassis = document.forms["modifVehicule"]["numChassis-modif-input"];
    const immatriculation = document.forms["modifVehicule"]["immatriculation-modif-input"];
    const dateAchat = document.forms["modifVehicule"]["dateAchat-modif-input"];
    const prixJournalier = document.forms["modifVehicule"]["prixJournalier-modif-input"];

    if (cylindree.value == "" || cylindree.value == null) {
        document.getElementById("errorCylindreeModif").innerHTML = "Veuillez entrer une cylindr�e";
        cylindree.style.border = "1px solid red";
        cylindree.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (cylindree.value.length < 2 || cylindree.value.length > 50 ) {
        document.getElementById("errorCylindreeModif").innerHTML = "Veuillez entrer une cylindr�e valide";
        cylindree.style.border = "1px solid red";
        cylindree.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorCylindreeModif").innerHTML = "";
        cylindree.removeAttribute('style');
    }

    if (puissance.value == "" || puissance.value == null) {
        document.getElementById("errorPuissanceModif").innerHTML = "Veuillez entrer une puissance";
        puissance.style.border = "1px solid red";
        puissance.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (puissance.value.length < 2 || puissance.value.length > 50 ) {
        document.getElementById("errorPuissanceModif").innerHTML = "Veuillez entrer une puissance valide";
        puissance.style.border = "1px solid red";
        puissance.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPuissanceModif").innerHTML = "";
        puissance.removeAttribute('style');
    }

    if (numChassis.value == "" || numChassis.value == null) {
        document.getElementById("errorNumChassisModif").innerHTML = "Veuillez entrer un num�ro de chassis";
        numChassis.style.border = "1px solid red";
        numChassis.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (numChassis.value.length < 2 || numChassis.value.length > 50 ) {
        document.getElementById("errorNumChassisModif").innerHTML = "Veuillez entrer un num�ro de chassis valide";
        numChassis.style.border = "1px solid red";
        numChassis.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorNumChassisModif").innerHTML = "";
        numChassis.removeAttribute('style');
    }

    if (immatriculation.value == "" || immatriculation.value == null) {
        document.getElementById("errorImmatriculationModif").innerHTML = "Veuillez entrer une immatriculation";
        immatriculation.style.border = "1px solid red";
        immatriculation.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (immatriculation.value.length < 2 || immatriculation.value.length > 50 ) {
        document.getElementById("errorImmatriculationModif").innerHTML = "Veuillez entrer une immatriculation valide";
        immatriculation.style.border = "1px solid red";
        immatriculation.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorImmatriculationModif").innerHTML = "";
        immatriculation.removeAttribute('style');
    }

    if (dateAchat.value == "" || dateAchat.value == null) {
        document.getElementById("errorDateAchatModif").innerHTML = "Veuillez selectionner une date";
        dateAchat.style.border = "1px solid red";
        dateAchat.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorDateAchatModif").innerHTML = "";
        dateAchat.removeAttribute('style');
    }

    if (prixJournalier.value == "" || prixJournalier.value == null) {
        document.getElementById("errorPrixJournalierModif").innerHTML = "Veuillez entrer un prix journalier";
        prixJournalier.style.border = "1px solid red";
        prixJournalier.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (prixJournalier.value.length < 2 || prixJournalier.value.length > 50 ) {
        document.getElementById("errorPrixJournalierModif").innerHTML = "Veuillez entrer un prix journalier valide";
        prixJournalier.style.border = "1px solid red";
        prixJournalier.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById("errorPrixJournalierModif").innerHTML = "";
        prixJournalier.removeAttribute('style');
    }

    return result;
}