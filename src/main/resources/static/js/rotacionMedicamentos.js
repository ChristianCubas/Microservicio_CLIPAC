const btn_reporte = document.getElementById("descargarReporte");
const btn_reporte_detallado = document.getElementById("descargarReporteDetallado");

btn_reporte.addEventListener('click', function() {

    const cards = document.querySelectorAll('.caja-lote');
    let datosCards = [];

    cards.forEach((card, index) => {

        const lote = card.querySelector('.fw-bold.text-primary span')?.textContent || 'N/A';
        const cantidad = card.querySelector('.mt-2 span')?.textContent || '0';
        const fechaVence = card.querySelector('.text-muted span')?.textContent || 'N/A';

        // 🔥 FIX REAL AQUÍ (medicamento)
        const medicamento = card.querySelector('.small')?.textContent || 'N/A';

        const estado = card.querySelector('.mt-3 span')?.textContent || '⚠️';

        const colorEstado =
            card.classList.contains('estado-naranja') ? '#ffe5cc' :
            card.classList.contains('estado-rojo') ? '#ffd6d6' : 'white';

        datosCards.push({
            lote, cantidad, fechaVence, medicamento, estado, colorEstado
        });
    });

    let tablaHTML = `
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Lote</th>
                    <th>Medicamento</th>
                    <th>Cantidad</th>
                    <th>Vence</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>`;

    datosCards.forEach((item, i) => {
        tablaHTML += `
            <tr style="background-color:${item.colorEstado}">
                <td>${i + 1}</td>
                <td>${item.lote}</td>
                <td>${item.medicamento}</td>
                <td>${item.cantidad}</td>
                <td>${item.fechaVence}</td>
                <td>${item.estado}</td>
            </tr>`;
    });

    tablaHTML += '</tbody></table>';

    const printWindow = window.open('', '_blank');

    printWindow.document.write(`
    <!DOCTYPE html>
    <html>
    <head>
        <title>Reporte</title>
        <style>

            body {
                font-family: Arial;
                margin: 0;
                font-size: 11px;
            }

            .header {
                text-align: center;
                border-bottom: 2px solid black;
                padding-bottom: 10px;
            }

            .logo {
                width: 140px;
                margin: 0 auto 5px;
                display: block;
            }

            .titulo {
                font-size: 18px;
                font-weight: bold;
            }

            .subtitulo {
                font-size: 13px;
                margin-top: 3px;
            }

            .fecha {
                font-size: 11px;
                margin-top: 5px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }

            th, td {
                border: 1px solid black;
                padding: 6px;
                text-align: center;
            }

            th {
                background: black;
                color: white;
            }

            /* 🔥 FIX DEFINITIVO */
            @media print {

                body {
                    padding-top: 180px; /* 👈 más espacio real */
                }

                .header {
                    position: fixed;
                    top: 0;
                    left: 0;
                    right: 0;
                    height: 160px; /* 👈 altura real */
                    background: white;
                }

                table {
                    margin-top: 0;
                }

                tr {
                    page-break-inside: avoid;
                }
            }

        </style>
    </head>
    <body>

        <div class="header">
            <img src="/imgs/clipac.png" class="logo"
                 onerror="this.style.display='none'">

            <div class="titulo">CLÍNICA DEL PACÍFICO</div>

            <div class="subtitulo">
                REPORTE DETALLADO - ROTACIÓN MEDICAMENTOS
            </div>

            <div class="fecha">
                Total lotes: ${datosCards.length} |
                ${new Date().toLocaleString('es-PE')}
            </div>
        </div>

        ${tablaHTML}

    </body>
    </html>
    `);

    printWindow.document.close();

    printWindow.onload = () => {
        printWindow.focus();
        printWindow.print();
        printWindow.close();
    };

});

btn_reporte_detallado.addEventListener('click', function() {
    // Crear ventana de impresión personalizada
    const printWindow = window.open('', '_blank', 'width=800,height=600');

    // Obtener datos de la tabla
    const tabla = document.querySelector('#tablaLotes table').outerHTML;
    const logoSrc = '/imgs/clipac.png';

    printWindow.document.write(`
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Reporte Rotación Medicamentos</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 20px;
                    font-size: 12px;
                }
                .header {
                    text-align: center;
                    margin-bottom: 30px;
                }
                .logo {
                    max-width: 200px;
                    height: auto;
                    text-align:text;
                    display:flex;
                    justify-content:center;
                    align-items:center;
                    margin-bottom: 10px;
                }
                .titulo {
                    font-size: 18px;
                    font-weight: bold;
                    color: #333;
                }
                .fecha {
                    color: #666;
                    font-size: 12px;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }
                th, td {
                    border: 2px solid #000 !important;
                    padding: 8px;
                    text-align: center;
                }
                th {
                    background-color: #000 !important;
                    color: white !important;
                    font-weight: bold;
                }
                .estado-naranja td { background-color: #ffe5cc !important; }
                .estado-rojo td { background-color: #ffd6d6 !important; }
            </style>
        </head>
        <body>
            <div class="header">
                ${logoSrc ? `<img src="${logoSrc}" class="logo" onerror="this.style.display='none'">` : ''}
                <div class="titulo">CLÍNICA DEL PACÍFICO</div>
                <div class="titulo">REPORTE DE ROTACIÓN DE MEDICAMENTOS</div>
                <div class="fecha">Fecha: ${new Date().toLocaleDateString('es-PE')}</div>
            </div>
            ${tabla}
        </body>
        </html>
    `);

    printWindow.document.close();

    // Imprimir cuando cargue
    printWindow.onload = function() {
        printWindow.focus();
        printWindow.print();
        printWindow.close();
    };
});

// rotacionMedicamentos.js

document.addEventListener('DOMContentLoaded', function() {
    // Filtros

    const btnFiltrar = document.getElementById('btnFiltrar');

    if (!btnFiltrar) {
        console.warn('No se encontró el botón con id="btnFiltrar"');
        return;
    }

    btnFiltrar.addEventListener('click', function() {
        const selectProveedor = document.getElementById('selectProveedor');
        const selectMedicamento = document.getElementById('selectMedicamento');

        // Protección: si alguno es null, no seguimos
        if (!selectProveedor || !selectMedicamento) {
            console.warn('No se encontraron los selects', { selectProveedor, selectMedicamento });
            return;
        }

        const idProveedor = selectProveedor.value;
        const idMedicamento = selectMedicamento.value;

        if (idProveedor && !idMedicamento) {
            window.location.href = `/rotacion/proveedor/${idProveedor}`;
            return;
        }

        if (!idProveedor && idMedicamento) {
            window.location.href = `/rotacion/medicamento/${idMedicamento}`;
            return;
        }

        if (idProveedor && idMedicamento) {
            window.location.href = `/rotacion/filtro/${idProveedor}/${idMedicamento}`;
            return;
        }

        window.location.href = `/rotacion`;
    });

    // Filtro de canje
    const selectEstado = document.getElementById('estado');
    const btn_filtrar_canje = document.getElementById('btnFiltrarCanje');
    const tabla = document.querySelector('#tablaLotes table tbody');
    const filas = Array.from(tabla.querySelectorAll('tr'));

    function filtrarTablaCanje() {
        const valor = selectEstado.value; // "1" = Si, "0" = No

        filas.forEach(fila => {
            const celdaApto = fila.cells[5]; // columna "Apto rotar canje"
            if (!celdaApto) return;

            const texto = celdaApto.innerText.trim();
            if ((valor === "1" && texto.includes("SI")) ||
                (valor === "0" && texto.includes("NO"))) {
                fila.style.display = '';
            } else {
                fila.style.display = 'none';
            }
        });
    }

    // Filtrar cuando se cambie el select
    btn_filtrar_canje.addEventListener('click', filtrarTablaCanje);

});
