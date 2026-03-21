document.getElementById('selectProveedor').addEventListener('change', function () {
    const idProveedor = this.value;

    // vaciar el select de medicamentos
    const selectMedicamento = document.getElementById('selectMedicamento');
    selectMedicamento.innerHTML = '<option value="">Seleccione un medicamento</option>';

    if (!idProveedor) return;

    // llamar al endpoint
    fetch('/inventario/medicamentos/proveedor/' + idProveedor)
      .then(response => response.json())
      .then(medicamentos => {
        medicamentos.forEach(med => {
          const option = document.createElement('option');
          option.value = med.idMedicamento; // o el nombre del campo ID
          option.textContent = med.nombre;
          selectMedicamento.appendChild(option);
        });
      })
      .catch(err => console.error('Error:', err));
  });

  document.addEventListener('DOMContentLoaded', function () {
    const selectProveedor = document.getElementById('selectProveedor');

    if (selectProveedor.value) {
      selectProveedor.dispatchEvent(new Event('change'));
    }
  });