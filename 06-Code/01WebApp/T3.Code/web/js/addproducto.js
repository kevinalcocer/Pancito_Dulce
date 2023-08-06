//JS para la comprobacion de los datos al Añadir un Producto.

//Inicializacion de variables y objetos del DOM
const nameInput = document.getElementById("nombre");
const cantidadInput = document.getElementById("cantidad");
const medidaInput = document.getElementById("medida");
const valorInput = document.getElementById("valor");
const formProduct = document.getElementById("form");
const error = document.getElementById("error");

//Comprobar si hay algun error al añadir un producto
if (sessionStorage.getItem('error') != null) {
    error.innerText = sessionStorage.getItem('error');
    sessionStorage.removeItem('error');
}

//Funciones de Evento:
function validarForm(event){
    //Comprobar Cambios
    //nickInput.value.length == 0: verifica que la cantidad de caracteres sea mayor a 0
    if (nameInput.value.match(/(?<!\S)[0-9]/)){ 
        
        nameInput.focus(); // focus: esta funcion sirve para poner el raton en el lugar donde hay un problema.
        error.innerText = "El Campo de Nombre no puede comenzar con un número";
        event.preventDefault();
        return false;

    }else if(cantidadInput.value <= "0"){
       
        cantidadInput.focus();
        event.preventDefault();
        error.innerText = "La Cantidad debe ser mayor a 0";
        return false;

    }else if(medidaInput.value == "0"){
        medidaInput.focus();
        event.preventDefault();
        error.innerText = "Seleccione la Medida del Producto";
        return false;

    }else if(valorInput.value <= "0"){
        valorInput.focus();
        event.preventDefault();
        error.innerText = "El valor debe ser mayor a 0";
        return false;
    }

    //Información es Correcta
    datosPro(nickInput);
    historicoPro(nickInput);
    return true;

}

//Inicio de Carga de Eventos:
formProduct.addEventListener('submit', validarForm); //submit: para capturar tanto al dar click en un boton o input, y al dar al ENTER