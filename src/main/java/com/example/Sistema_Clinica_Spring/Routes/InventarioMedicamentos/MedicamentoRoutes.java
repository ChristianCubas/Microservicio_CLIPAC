package com.example.Sistema_Clinica_Spring.Routes.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceMedicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceProveedor;
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
}