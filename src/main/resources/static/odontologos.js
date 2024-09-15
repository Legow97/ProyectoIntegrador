const API_URL = "http://localhost:8080/odontologo";

function listarOdontologos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            let odontologosList = document.getElementById('odontologos-list');
            odontologosList.innerHTML = '';
            data.forEach(odontologo => {
                odontologosList.innerHTML += `<p>ID: ${odontologo.id}, Nombre: ${odontologo.nombre}, Apellido: ${odontologo.apellido}, Matrícula: ${odontologo.matricula}</p>`;
            });
        });
}

function mostrarFormularioAgregarOdontologo() {
    let formHTML = `
        <form id="formAgregarOdontologo">
            <input type="text" id="nombre" placeholder="Nombre">
            <input type="text" id="apellido" placeholder="Apellido">
            <input type="text" id="matricula" placeholder="Matrícula">
            <button type="button" onclick="agregarOdontologo()">Agregar</button>
        </form>
    `;
    document.getElementById('odontologos-list').innerHTML = formHTML;
}

function agregarOdontologo() {
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const matricula = document.getElementById('matricula').value;

    const odontologo = { nombre, apellido, matricula };

    fetch(`${API_URL}/agregar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(odontologo)
    })
        .then(response => response.json())
        .then(data => {
            listarOdontologos();
            alert('Odontólogo agregado correctamente');
        });
}

function eliminarOdontologo() {
    const id = prompt("Ingrese el ID del odontólogo a eliminar:");
    fetch(`${API_URL}/${id}`, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                listarOdontologos();
                alert('Odontólogo eliminado correctamente');
            }
        });
}

function obtenerOdontologo() {
    const id = prompt("Ingrese el ID del odontólogo:");
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(odontologo => {
            alert(`Nombre: ${odontologo.nombre}, Apellido: ${odontologo.apellido}, Matrícula: ${odontologo.matricula}`);
        });
}
