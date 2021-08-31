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
        error.innerHTML = "Veuillez choisir une heure de départ";
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
        error.innerHTML = "Veuillez choisir une date de départ";
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
        document.getElementById('lieu-depart-error').innerHTML = "Veuillez selectionner un lieu de départ";
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
        document.getElementById('date-time-depart-error').innerHTML = "Veuillez choisir une date et heure de départ";
        dateDepart.style.border = "1px solid red";
        dateDepart.style.boxShadow = "0 0 1px 2px red";
        heureDepart.style.border = "1px solid red";
        heureDepart.style.boxShadow = "0 0 1px 2px red";

        result = false;
    } else if (dateDepart.value == "" || dateDepart.value == null) {
        document.getElementById('date-time-depart-error').innerHTML = "Veuillez choisir une date de départ";
        dateDepart.style.border = "1px solid red";
        dateDepart.style.boxShadow = "0 0 1px 2px red";
        heureDepart.removeAttribute('style');

        result = false;
    } else if (heureDepart.value == "" || heureDepart.value == null) {
        document.getElementById('date-time-depart-error').innerHTML = "Veuillez choisir une heure de départ";
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
    var nom = document.forms["modifUser"]["nom"];
    var inputNom = document.getElementById("nom");
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

    if (nom.value == "" || nom.value.length < 2 || nom.value.length > 100){
        let e = document.getElementById("errorNomModif");
        inputNom.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un nom valide entre 2 et 100 caracteres.";
        nom.focus();
        return false;
    }else{
        inputNom.style.borderColor = "green";
        document.getElementById("errorNomModif").innerHTML = "";
    }
    if (prenom.value == "" || prenom.value.length < 2 || prenom.value.length > 100){
        let e = document.getElementById("errorPrenomModif");
        inputPrenom.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un nom valide entre 2 et 100 caracteres.";
        prenom.focus();
        return false;
    }else{
        inputPrenom.style.borderColor = "green";
        document.getElementById("errorPrenomModif").innerHTML = "";
    }
    if (telephone.value == "" || telephone.value.length <= 6 || telephone.value.length > 50 || telephone.matches("\\+?[0-9]*")){
        let e = document.getElementById("errorTelephoneModif");
        inputTelephone.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un telephone valide entre 2 et 50 caracteres.";
        telephone.focus();
        return false;
    }else{
        inputTelephone.style.borderColor = "green";
        document.getElementById("errorTelephoneModif").innerHTML = "";
    }
    if (rue.value == "" || rue.value.length <= 6 || rue.value.length > 100){
        let e = document.getElementById("errorRueModif");
        inputRue.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer une rue valide entre 6 et 100 caracteres.";
        rue.focus();
        return false;
    }else{
        inputRue.style.borderColor = "green";
        document.getElementById("errorRueModif").innerHTML = "";
    }
    if (numero.value == "" || numero.value.length <= 1 || numero.value.length > 10 ){
        let e = document.getElementById("errorNumModif");
        inputNumero.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un numero valide entre 1 et 10 caracteres.";
        numero.focus();
        return false;
    }else{
        inputNumero.style.borderColor = "green";
        document.getElementById("errorNumModif").innerHTML = "";
    }
    if (boite.value.length > 50 ){
        let e = document.getElementById("errorBoiteModif");
        inputBoite.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un telephone valide entre 2 et 50 caracteres.";
        boite.focus();
        return false;
    }else{
        inputBoite.style.borderColor = "green";
        document.getElementById("errorBoiteModif").innerHTML = "";
    }
    return (true);
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

function validateCreationUtilisateur (){
    var nom = document.forms["createUser"]["register-nom"];
    var inputNom = document.getElementById("register-nom")
    var prenom = document.forms["createUser"]["register-prenom"];
    var inputPrenom = document.getElementById("register-prenom")
    var mail = document.forms["createUser"]["register-mail"];
    var inputMail = document.getElementById("register-mail")
    var password = document.forms["createUser"]["register-password"];
    var inputPassword = document.getElementById("register-password");
    var confirmPassword = document.forms["createUser"]["register-confirmPassword"];
    var inputConfirmPassword = document.getElementById("register-confirmPassword")
    var telephone = document.forms["createUser"]["register-telephone"];
    var inputTelephone = document.getElementById("register-telephone")
    var rue = document.forms["createUser"]["register-rue"];
    var inputRue = document.getElementById("register-rue");
    var numero = document.forms["createUser"]["register-numero"];
    var inputNumero = document.getElementById("register-numero");
    var boite = document.forms["createUser"]["register-boite"];
    var inputBoite = document.getElementById("register-boite");
    var ville = document.forms["createUser"]["register-ville"];
    var inputVille = document.getElementById("register-ville")
    var dateNaissance = document.forms["createUser"]["register-dateNaissance"];
    var inputDateNaissance = document.getElementById("register-dateNaissance")
    var datePermis = document.forms["createUser"]["register-datePermis"];
    var inputDatePermis = document.getElementById("register-datePermis")
    var result = true;

    if (nom.value == "" || nom.value.length < 2 || nom.value.length > 100){
        let e = document.getElementById("errorNomCreate");
        inputNom.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un nom valide entre 2 et 100 caracteres.";
        nom.focus();
        result = false;
    }else{
        inputNom.style.borderColor = "green";
        document.getElementById("errorNomCreate").innerHTML = "";
    }
    if (prenom.value == "" || prenom.value.length < 2 || prenom.value.length > 100){
        let e = document.getElementById("errorPrenomCreate");
        inputPrenom.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un prenom valide entre 2 et 100 caracteres.";
        prenom.focus();
        result = false;
    }else{
        inputPrenom.style.borderColor = "green";
        document.getElementById("errorPrenomCreate").innerHTML = "";
    }
    if (mail.value == "" || mail.value.length > 100 || mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
        let e = document.getElementById("errorMailCreate");
        inputMail.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML = "Veuillez entrer un mail valide.";
        mail.focus();
        result = false;
    }else{
        inputMail.style.borderColor = "green";
        document.getElementById("errorMailCreate").innerHTML = "";
    }
    if (password.value == "" || password.value.length < 4 || password.value.length > 256 ){
        let e = document.getElementById("errorPasswordCreate");
        inputPassword.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un mot de passe valide entre 4 et 256 caracteres.";
        password.focus();
        result = false;
    }
    else{
        inputPassword.style.borderColor = "green";
        document.getElementById("errorPasswordCreate").innerHTML = "";
    }
    if (telephone.value == "" || telephone.value.length <= 6 || telephone.value.length > 50 || telephone.matches("\\+?[0-9]*")){
        let e = document.getElementById("errorTelephoneCreate");
        inputTelephone.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un telephone valide entre 2 et 50 caracteres.";
        telephone.focus();
        result = false;
    }else{
        inputTelephone.style.borderColor = "green";
        document.getElementById("errorTelephoneCreate").innerHTML = "";
    }
    if (rue.value == "" || rue.value.length <= 6 || rue.value.length > 100){
        let e = document.getElementById("errorRueCreate");
        inputRue.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer une rue valide entre 6 et 100 caracteres.";
        rue.focus();
        result = false;
    }else{
        inputRue.style.borderColor = "green";
        document.getElementById("errorRueCreate").innerHTML = "";
    }
    if (numero.value == "" || numero.value.length <= 1 || numero.value.length > 10 ){
        let e = document.getElementById("errorNumeroCreate");
        inputNumero.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un numero valide entre 1 et 10 caracteres.";
        numero.focus();
        result = false;
    }else{
        inputNumero.style.borderColor = "green";
        document.getElementById("errorNumeroCreate").innerHTML = "";
    }
    if (boite.value.length > 50 ){
        let e = document.getElementById("errorBoiteCreate");
        inputBoite.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez entrer un telephone valide entre 2 et 50 caracteres.";
        boite.focus();
        result = false;
    }else{
        inputBoite.style.borderColor = "green";
        document.getElementById("errorBoiteCreate").innerHTML = "";
    }
    if (ville.value ==""){
        let e = document.getElementById("errorVilleCreate");
        inputVille.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez selectionner une ville.";
        ville.focus();
        result = false;
    }else{
        inputVille.style.borderColor = "green";
        document.getElementById("errorVilleCreate").innerHTML = "";
    }
    if (dateNaissance.value ==""){
        let e = document.getElementById("errorDateCreate");
        inputDateNaissance.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez selectionner une date.";
        dateNaissance.focus();
        result = false;
    }else{
        inputDateNaissance.style.borderColor = "green";
        document.getElementById("errorDateCreate").innerHTML = "";
    }
    if (datePermis.value ==""){
        let e = document.getElementById("errorPermisCreate");
        inputDatePermis.style.borderColor = "red";
        e.style.color="red";
        e.innerHTML ="Veuillez selectionner une date.";
        datePermis.focus();
        result = false;
    }else{
        inputDatePermis.style.borderColor = "green";
        document.getElementById("errorPermisCreate").innerHTML = "";
    }
    return result;
}
