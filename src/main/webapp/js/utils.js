function colorOnLoadHome() {

    document.getElementById('LieuDepart').style.color = "#999";
    document.getElementById('LieuRetour').style.color = "#999";
    document.getElementById('dateTimeDepart').style.color = "#999";
    document.getElementById('heureDepart').style.color = "#999";
    document.getElementById('dateTimeRetour').style.color = "#999";
    document.getElementById('heureRetour').style.color = "#999";
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

function validateSearchVehicle() {

    let result = true;

    const lieuDepart = document.forms["searchVehicle"]["LieuDepart"];
    const lieuRetour = document.forms["searchVehicle"]["LieuRetour"];
    const dateDepart = document.forms["searchVehicle"]["dateTimeDepart"];
    const heureDepart = document.forms["searchVehicle"]["heureDepart"];
    const dateRetour = document.forms["searchVehicle"]["dateTimeRetour"];
    const heureRetour = document.forms["searchVehicle"]["heureRetour"];

    if (lieuDepart.value == "" || lieuDepart.value == null) {
        document.getElementById('lieu-depart-error').innerHTML = "Veuillez selectionner un lieu de départ";
        LieuDepart.style.border = "1px solid red";
        LieuDepart.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('lieu-depart-error').innerHTML = "";
        LieuDepart.style.border = "1px solid gray";
        LieuDepart.style.boxShadow = "none";
    }

    if (lieuRetour.value == "" || lieuRetour.value == null) {
        document.getElementById('lieu-retour-error').innerHTML = "Veuillez selectionner un lieu de retour";
        lieuRetour.style.border = "1px solid red";
        lieuRetour.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('lieu-retour-error').innerHTML = "";
        lieuRetour.style.border = "1px solid gray";
        lieuRetour.style.boxShadow = "none";
    }

    if (dateDepart.value == "" || dateDepart.value == null) {
        document.getElementById('date-depart-error').innerHTML = "Veuillez choisir une date et heure de départ";
        dateDepart.style.border = "1px solid red";
        dateDepart.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('date-depart-error').innerHTML = "";
        dateDepart.style.border = "1px solid gray";
        dateDepart.style.boxShadow = "none";
    }

    if (heureDepart.value == "" || heureDepart.value == null) {
        document.getElementById('date-depart-error').innerHTML = "Veuillez choisir une date et heure de départ";
        heureDepart.style.border = "1px solid red";
        heureDepart.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('date-depart-error').innerHTML = "";
        heureDepart.style.border = "1px solid gray";
        heureDepart.style.boxShadow = "none";
    }

    if (dateRetour.value == "" || dateRetour.value == null) {
        document.getElementById('date-retour-error').innerHTML = "Veuillez choisir une date et heure de retour";
        dateRetour.style.border = "1px solid red";
        dateRetour.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('date-retour-error').innerHTML = "";
        dateRetour.style.border = "1px solid gray";
        dateRetour.style.boxShadow = "none";
    }

    if (heureRetour.value == "" || heureRetour.value == null) {
        document.getElementById('date-retour-error').innerHTML = "Veuillez choisir une date et heure de retour";
        heureRetour.style.border = "1px solid red";
        heureRetour.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else {
        document.getElementById('date-retour-error').innerHTML = "";
        heureRetour.style.border = "1px solid gray";
        heureRetour.style.boxShadow = "none";
    }

    return result;
}