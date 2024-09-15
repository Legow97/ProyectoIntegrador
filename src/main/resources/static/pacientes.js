const API_URL_PACIENTES = "http://localhost:8080/paciente";

function listarPacientes() {
    fetch(API_URL_PACIENTES)
        .then(response => response.json())
        .then(data => {
            let pacientesList = document.getElementById('pacientes-list');
            pacientesList.innerHTML = '';
            data.forEach(paciente => {
                pacientesList.innerHTML += `<p>ID: ${paciente.id}, Nombre: ${paciente.nombre}, Apellido: ${paciente.apellido}, DNI: ${paciente.dni}</p>`;
            });
        });
}

function mostrarFormularioAgregarPaciente() {
    let formHTML = `
        <form id="formAgregarPaciente">
            <input type="text" id="nombrePaciente" placeholder="Nombre">
            <input type="text" id="apellidoPaciente" placeholder="Apellido">
            <input type="text" id="dniPaciente" placeholder="DNI">
            <button type="button" onclick="agregarPaciente()">Agregar</button>
        </form>
    `;
    document.getElementById('pacientes-list').innerHTML = formHTML;
}

function agregarPaciente() {
    const nombre = document.getElementById('nombrePaciente').value;
    const apellido = document.getElementById('apellidoPaciente').value;
    const dni = document.getElementById('dniPaciente').value;

    const paciente = { nombre, apellido, dni };

    fetch(`${API_URL_PACIENTES}/agregar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(paciente)
    })
        .then(response => response.json())
        .then(data => {
            listarPacientes();
            alert('Paciente agregado correctamente');
        });
}

function eliminarPaciente() {
    const id = prompt("Ingrese el ID del paciente a eliminar:");
    fetch(`${API_URL_PACIENTES}/${id}`, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                listarPacientes();
                alert('Paciente eliminado correctamente');
            }
        });
}

function obtenerPaciente() {
    const id = prompt("Ingrese el ID del paciente:");
    fetch(`${API_URL_PACIENTES}/${id}`)
        .then(response => response.json())
        .then(paciente => {
            alert(`Nombre: ${paciente.nombre}, Apellido: ${paciente.apellido}, DNI: ${paciente.dni}`);
        });
}
