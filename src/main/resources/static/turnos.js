const API_URL_TURNOS = "http://localhost:8080/turno";

function listarTurnos() {
    fetch(API_URL_TURNOS)
        .then(response => response.json())
        .then(data => {
            let turnosList = document.getElementById('turnos-list');
            turnosList.innerHTML = '';
            data.forEach(turno => {
                turnosList.innerHTML += `<p>ID: ${turno.id}, Fecha: ${turno.fecha}, Paciente: ${turno.paciente.nombre} ${turno.paciente.apellido}, Odontólogo: ${turno.odontologo.nombre} ${turno.odontologo.apellido}</p>`;
            });
        });
}

function mostrarFormularioAgregarTurno() {
    let formHTML = `
        <form id="formAgregarTurno">
            <input type="date" id="fechaTurno" placeholder="Fecha">
            <input type="text" id="pacienteIdTurno" placeholder="ID del Paciente">
            <input type="text" id="odontologoIdTurno" placeholder="ID del Odontólogo">
            <button type="button" onclick="agregarTurno()">Agregar</button>
        </form>
    `;
    document.getElementById('turnos-list').innerHTML = formHTML;
}

function agregarTurno() {
    const fecha = document.getElementById('fechaTurno').value;
    const pacienteId = document.getElementById('pacienteIdTurno').value;
    const odontologoId = document.getElementById('odontologoIdTurno').value;

    const turno = { fecha, pacienteId, odontologoId };

    fetch(`${API_URL_TURNOS}/agregar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(turno)
    })
        .then(response => response.json())
        .then(data => {
            listarTurnos();
            alert('Turno agregado correctamente');
        });
}

function eliminarTurno() {
    const id = prompt("Ingrese el ID del turno a eliminar:");
    fetch(`${API_URL_TURNOS}/${id}`, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                listarTurnos();
                alert('Turno eliminado correctamente');
            }
        });
}

function obtenerTurno() {
    const id = prompt("Ingrese el ID del turno:");
    fetch(`${API_URL_TURNOS}/${id}`)
        .then(response => response.json())
        .then(turno => {
            alert(`Fecha: ${turno.fecha}, Paciente: ${turno.paciente.nombre} ${turno.paciente.apellido}, Odontólogo: ${turno.odontologo.nombre} ${turno.odontologo.apellido}`);
        });
}
