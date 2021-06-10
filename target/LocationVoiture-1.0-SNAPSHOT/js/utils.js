function showRegisterForm() {

    if(document.getElementById('container-register').style.display == 'block'){
        document.getElementById('container-register').style.display='none';
    }else{
        document.getElementById('container-register').style.display='block';
    }

    return false;
}