package com.example.Sistema_Clinica_Spring.Routes.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Tipo_medicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.Service_tipo_medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tipos_medicamentos")
public class TipoMedicamentoRoutes {
    @Autowired
    private Service_tipo_medicamento serviceTipoMedicamento;

    @GetMapping
    public String listarTiposMedicamento(Model model){
        List<Tipo_medicamento> tiposMedicamentos = serviceTipoMedicamento.listadoTipoMedicamento();
        model.addAttribute("tiposMedicamentos",tiposMedicamentos);
        return "dashboard/Apartados/Tipos_Medicamentos";
    }

    @GetMapping("/nuevo")
    public String registrarTipoMedicamento(Model model){
        model.addAttribute("tipoMedicamento",new Tipo_medicamento());
        model.addAttribute("titulo","Registrar tipo de medicamento");
        model.addAttribute("accion","/tipos_medicamentos/guardar");
        model.addAttribute("botonTexto","Registrar");
        return "dashboard/CRUDs/FormTipoMedicamento";
    }

    @GetMapping("/editar/{id}")
    public String editarTipoMedicamento(@PathVariable Long id,Model model){
        Tipo_medicamento tipo_medicamento_enviar = serviceTipoMedicamento.obtenerTipoMedicamento(id);
        model.addAttribute("tipoMedicamento",tipo_medicamento_enviar);
        model.addAttribute("titulo","Actualizar tipo de medicamento");
        model.addAttribute("accion","/tipos_medicamentos/actualizar/"+id);
        model.addAttribute("botonTexto","Actualizar");
        return "dashboard/CRUDs/FormTipoMedicamento";
    }

    @PostMapping("/guardar")
    public String guardarTipoMedicamento(@ModelAttribute Tipo_medicamento tipoMedicamento){
        serviceTipoMedicamento.crearTipoMedicamento(tipoMedicamento);
        return "redirect:/tipos_medicamentos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarTipoMedicamento(@PathVariable Long id, @ModelAttribute Tipo_medicamento tipo_medicamento){
        serviceTipoMedicamento.actualizarMedicamento(id,tipo_medicamento);
        return "redirect:/tipos_medicamentos";
    }

    @GetMapping("/baja/{id}")
    public String darBajaTipoMedicamento(@PathVariable Long id){
        serviceTipoMedicamento.darBajaTipoMedicamento(id);
        return "redirect:/tipos_medicamentos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTipoMedicamento(@PathVariable Long id){
        serviceTipoMedicamento.eliminarMedicamento(id);
        return "redirect:/tipos_medicamentos";
    }
}
