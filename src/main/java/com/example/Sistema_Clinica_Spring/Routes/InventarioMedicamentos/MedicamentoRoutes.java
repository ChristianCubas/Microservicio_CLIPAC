package com.example.Sistema_Clinica_Spring.Routes.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Estructuras.TCola;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceMedicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceProveedor;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.Service_kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.Service_tipo_medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MedicamentoRoutes {

    @Autowired
    private ServiceMedicamento serviceMedicamento;

    @Autowired
    private Service_tipo_medicamento serviceTipoMedicamento;

    @Autowired
    private ServiceProveedor serviceProveedor;

    @Autowired
    private Service_kardex_medicamento serviceKardexMedicamento;

    @GetMapping("/medicamentos")
    public String listarMedicamentos(Model model){
        List<Medicamento> listadoMedicamentos = serviceMedicamento.listarMedicamentos();
        model.addAttribute("medicamentos", listadoMedicamentos);
        return "dashboard/Apartados/Medicamentos";
    }

    @GetMapping("/medicamentos/nuevo")
    public String nuevoMedicamento(Model model){
        model.addAttribute("medicamento", new Medicamento());
        model.addAttribute("tipos", serviceTipoMedicamento.listadoTipoMedicamento());
        model.addAttribute("proveedores", serviceProveedor.listarProveedores());
        model.addAttribute("titulo", "Nuevo medicamento");
        model.addAttribute("accion", "/medicamentos/guardar");
        return "dashboard/CRUDs/FormMedicamento";
    }

    @PostMapping("/medicamentos/guardar")
    public String guardarMedicamento(@ModelAttribute Medicamento medicamento){
        serviceMedicamento.crearMedicamento(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/medicamentos/editar/{id}")
    public String editarMedicamento(@PathVariable Long id, Model model){
        Medicamento medicamento = serviceMedicamento.obtenerMedicamento(id);
        model.addAttribute("medicamento", medicamento);
        model.addAttribute("tipos", serviceTipoMedicamento.listadoTipoMedicamento());
        model.addAttribute("proveedores", serviceProveedor.listarProveedores());
        model.addAttribute("titulo", "Editar medicamento");
        model.addAttribute("accion", "/medicamentos/actualizar/"+id);
        return "dashboard/CRUDs/FormMedicamento";
    }

    @PostMapping("/medicamentos/actualizar/{id}")
    public String actualizarMedicamento(@PathVariable Long id, @ModelAttribute Medicamento medicamento){
        serviceMedicamento.actualizarMedicamento(id,medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/medicamentos/baja/{id}")
    public String bajaMedicamento(@PathVariable Long id){
        serviceMedicamento.darBajaMedicamento(id);
        return "redirect:/medicamentos";
    }

    @GetMapping("/medicamentos/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable Long id){
        serviceMedicamento.eliminarMedicamento(id);
        return "redirect:/medicamentos";
    }

    @GetMapping("/rotacion")
    public String rotacionMedicamentos(Model model){

        List<Kardex_medicamento> kardex = serviceKardexMedicamento.listarKardexMedicamento();

        TCola colaFEFO = serviceMedicamento.FEFOMedicamento();

        List<Kardex_medicamento> lotes = colaFEFO.toList();

        model.addAttribute("lotes",lotes);
        model.addAttribute("proveedores", serviceProveedor.listarProveedores());
        model.addAttribute("medicamentos",serviceMedicamento.listarMedicamentos());

        return "dashboard/Apartados/RotacionMedicamentos";
    }

    // Filtrar FEFO por proveedor
    @GetMapping("/rotacion/proveedor/{idProveedor}")
    public String rotacionPorProveedor(@PathVariable Long idProveedor, Model model){

        TCola colaFEFO = serviceMedicamento.FiltroPorProveedor(idProveedor);

        List<Kardex_medicamento> lotes = colaFEFO.toList();

        model.addAttribute("lotes", lotes);
        model.addAttribute("proveedores", serviceProveedor.listarProveedores());
        model.addAttribute("medicamentos", serviceMedicamento.listarMedicamentos());
        model.addAttribute("filtroProveedor", idProveedor); // opcional, para UI

        return "dashboard/Apartados/RotacionMedicamentos";
    }

    // Filtrar FEFO por medicamento
    @GetMapping("/rotacion/medicamento/{idMedicamento}")
    public String rotacionPorMedicamento(@PathVariable Long idMedicamento, Model model){

        TCola colaFEFO = serviceMedicamento.FiltroPorMedicamento(idMedicamento);

        List<Kardex_medicamento> lotes = colaFEFO.toList();

        model.addAttribute("lotes", lotes);
        model.addAttribute("proveedores", serviceProveedor.listarProveedores());
        model.addAttribute("medicamentos", serviceMedicamento.listarMedicamentos());
        model.addAttribute("filtroMedicamento", idMedicamento); // opcional, para UI

        return "dashboard/Apartados/RotacionMedicamentos";
    }

    // Filtrar FEFO por proveedor y medicamento
    @GetMapping("/rotacion/filtro/{idProveedor}/{idMedicamento}")
    public String rotacionPorProveedorYMedicamento(
            @PathVariable Long idProveedor,
            @PathVariable Long idMedicamento,
            Model model) {

        TCola colaFEFO;

        if (idProveedor != null && idMedicamento != null) {
            // Ambos filtros
            colaFEFO = serviceMedicamento.FiltroPorProveedorYMedicamento(idProveedor, idMedicamento);
        } else if (idProveedor != null) {
            // Solo proveedor
            colaFEFO = serviceMedicamento.FiltroPorProveedor(idProveedor);
        } else if (idMedicamento != null) {
            // Solo medicamento
            colaFEFO = serviceMedicamento.FiltroPorMedicamento(idMedicamento);
        } else {
            // Ningún filtro, mostrar todo
            colaFEFO = serviceMedicamento.FEFOMedicamento();
        }

        List<Kardex_medicamento> lotes = colaFEFO.toList();

        model.addAttribute("lotes", lotes);
        model.addAttribute("proveedores", serviceProveedor.listarProveedores());
        model.addAttribute("medicamentos", serviceMedicamento.listarMedicamentos());
        model.addAttribute("filtroProveedor", idProveedor);   // opcional, para UI
        model.addAttribute("filtroMedicamento", idMedicamento); // opcional, para UI

        return "dashboard/Apartados/RotacionMedicamentos";
    }
}