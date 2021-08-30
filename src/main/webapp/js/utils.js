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

function validateModifUser(){
    var name = document.forms["modifUser"]["nom"];
    var inputName = document.getElementById("nom");
    var prenom = document.forms["modifUser"]["prenom"];
    var inputPrenom = document.getElementById("prenom");
    var telephone = document.forms["modifUser"]["telephone"];
    var inputTelephone = document.getElementById("telephone");
    var rue = document.forms["modifUser"]["rue"];
    var inputRue = document.getElementById("rue");
    var numero = document.forms["modifUser"]["numero"];
    var inputNumero = document.getElementById("numero");
    var boite = document.forms["modifUser"]["boite"];
    var inputBoite = document.getElementById("boite");
    var result = true;

    if (name.value == "" || name.value.length < 2 || prenom.value.length > 100){
        var e = document.getElementById("errorNomModif");
        e.fontcolor("red");
        e.innerHTML ="Veuillez entrer un nom valide entre 2 et 100 caracteres.";
        name.focus();
        result = false;
    }else{
        document.getElementById("errorNomModif").innerHTML = "";

    }
    if (prenom.value == "" || prenom.value.length < 2 || prenom.value.length > 100){
        var e = document.getElementById("errorPrenomModif");
        e.fontcolor("red");
        e.innerHTML ="Veuillez entrer un prenom valide entre 2 et 100 caracteres.";
        prenom.focus();
        result = false;
    }else{
        document.getElementById("errorPrenomModif").innerHTML = "";
    }
    if (telephone.value == "" || telephone.value.length <= 6 || telephone.value.length > 50 || telephone.matches("\\+?[0-9]*")){
        var e = document.getElementById("errorTelephoneModif");
        e.fontcolor("red");
        e.innerHTML ="Veuillez entrer un telephone valide entre 2 et 50 caracteres.";
        telephone.focus();
        result = false;
    }else{
        document.getElementById("errorTelephoneModif").innerHTML = "";
    }
    if (rue.value == "" || rue.value.length <= 6 || telephone.value.length > 100){
        var e = document.getElementById("errorRueModif");
        e.fontcolor("red");
        e.innerHTML ="Veuillez entrer une rue valide entre 6 et 100 caracteres.";
        rue.focus();
        result = false;
    }else{
        document.getElementById("errorRueModif").innerHTML = "";
    }
    if (numero.value == "" || numero.value.length <= 1 || numero.value.length > 10 ){
        var e = document.getElementById("errorNumModif");
        e.fontcolor("red");
        e.innerHTML ="Veuillez entrer un numero valide entre 1 et 10 caracteres.";
        numero.focus();
        result = false;
    }else{
        document.getElementById("errorNumModif").innerHTML = "";
    }
    if (boite.value.length > 50 ){
        var e = document.getElementById("errorBoiteModif");
        e.fontcolor("red");
        e.innerHTML ="Veuillez entrer un telephone valide entre 2 et 50 caracteres.";
        boite.focus();
        result = false;
    }else{
        document.getElementById("errorBoiteModif").innerHTML = "";
    }

    if (!result){
        window.alert("Vous n'avez pas bien rempli les champs.");
    }

    return result;
}
