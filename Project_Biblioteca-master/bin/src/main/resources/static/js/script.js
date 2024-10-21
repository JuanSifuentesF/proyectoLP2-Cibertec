/* Confirmación de eliminación */
function deleteConfirm(event) {
    event.preventDefault();
    const deleteUrl = event.target.getAttribute("href");

    Swal.fire({
        title: '¿Estás seguro?',
        text: 'No podrás revertir esto',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminarlo',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = deleteUrl; // Si se confirma, redirige al listado
        }
    });
}

$('#form-libro').submit(function (e) {
    e.preventDefault();
    let form = '#form-libro'

    let titulo = $('#titulo').val();
    let paginas = $('#paginas').val();
    let cantidad = $('#cantidad').val();
    let categoria = $('#categoria').val();
    let isbn = $('#isbn').val();
    // Convirtiendo a un número
    paginas = parseInt(paginas);
    cantidad = parseInt(cantidad);

    let isbnPattern = /^(?:\d{10}|\d{13})$/; // Expresión regular para validar el ISBN

    if (titulo && paginas && cantidad && categoria && !isNaN(paginas) && !isNaN(cantidad) && paginas > 0 && cantidad > 0 && isbnPattern.test(isbn)) {
        alertSuccess(form);
    } else {
        alertWarningEmpty(form);
    }
});

$('#form-prestamo').submit(function (e) {
    e.preventDefault();
    let form = '#form-prestamo'
    let libro = $('#libro').val();
    let usuario = $('#usuario').val();
    let fecDev = $('#fecDevolucion').val();

    if (libro && usuario && fecDev) {
        alertSuccess(form);
    } else {
        alertWarningEmpty(form);
    }
});

$('#form-ver-prestamo').submit(function (e) {
    e.preventDefault(); // Prevenir el envío automático del formulario
    let form = '#form-ver-prestamo'
    alertConfirm(form);
});

$('#form-multa').submit(function (e) {
    e.preventDefault();
    let form = '#form-multa';
    alertConfirm(form);
});

function alertLogout(e){
    e.preventDefault();
    alertSuccess();
}
function alertConfirm(form) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: "¿Quieres realizar esta operación?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, confirmar!',
        cancelButtonText: 'No, cancelar!'
    }).then((result) => {
        if (result.isConfirmed) {
            alertSuccess(form)
        }
    })
}

function alertSuccess(form) {
    Swal.fire({
        title: '¡Operación exitosa!',
        icon: 'success',
        confirmButtonText: 'Aceptar'
    }).then(function () {
        $(form).off('submit').submit();
    });
}

function alertWarningEmpty(form) {
    Swal.fire({
        title: 'Campos vacíos detectados',
        text: 'Por favor llene los campos requeridos antes de guardar',
        icon: 'warning',
        confirmButtonText: 'Aceptar'
    }).then(function () {
        $(form).off('submit').submit()
    });
}


// Validación de campos de contraseña del usuario:
/*document.getElementById('form-user').addEventListener('submit', function (event) {
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;

    if (password !== confirmPassword) {
        document.getElementById('passwordError').textContent = 'Las contraseñas no coinciden.';
        event.preventDefault();
    }
});*/
