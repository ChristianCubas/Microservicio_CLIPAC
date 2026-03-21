package com.example.Sistema_Clinica_Spring.Routes.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.DTO.MedicamentoDTO;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceMedicamento;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceProveedor;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.Service_kardex_medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/inventario")
public class Kardex_medicamentoRoutes {

    @Autowired
    private Service_kardex_medicamento kardexMedicamentoService;

    @Autowired
    private ServiceMedicamento medicamentoService;

    @Autowired
    private ServiceProveedor proveedorService;

    @GetMapping
    public String inventario(Model model){
        List<Kardex_medicamento> kardex = kardexMedicamentoService.listarKardexMedicamento();
        model.addAttribute("movimientos",kardex);
        return "dashboard/Apartados/Inventario";
    }

    @GetMapping("/nuevo")
    public String registrarInventario(Model model){
        model.addAttribute("titulo","Registrar movimiento");
        model.addAttribute("accion","/inventario/nuevo/registrarMovimiento");
        model.addAttribute("kardex", new Kardex_medicamento()); // ← AGREGAR ESTA LÍNEA
        model.addAttribute("proveedores",proveedorService.listarProveedores());
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        return "dashboard/CRUDs/FormInventario.html";
    }

    @PostMapping("/nuevo/registrarMovimiento")
    public String registrarMovimiento(@ModelAttribute Kardex_medicamento kardex_medicamento){
        kardexMedicamentoService.crearKardexMedicamento(kardex_medicamento);
        return "redirect:/inventario";
    }

    @GetMapping("/editar/{id}")
    public String editarMovimiento(@PathVariable long id, Model model){
        model.addAttribute("titulo","Actualizar movimiento");
        model.addAttribute("accion","/inventario/actualizar/movimiento/"+id); // ← Misma ruta POST
        model.addAttribute("kardex", kardexMedicamentoService.obtenerKardexMedicamento(id));
        model.addAttribute("proveedores",proveedorService.listarProveedores());
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        return "dashboard/CRUDs/FormInventario.html";
    }

    @PostMapping("/actualizar/movimiento/{id}")
    public String actualizarMovimiento(@ModelAttribute Kardex_medicamento kardex_medicamento, @PathVariable long id){
        kardexMedicamentoService.actualizarKardexMedicamento(id,kardex_medicamento);
        return "redirect:/inventario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMovimiento(@PathVariable long id){
        kardexMedicamentoService.eliminarKardexMedicamento(id);
        return "redirect:/inventario";
    }

    @GetMapping("/medicamentos/proveedor/{idProveedor}")
    @ResponseBody
    public List<MedicamentoDTO> obtenerMedicamentosPorProveedor(@PathVariable Long idProveedor) {
        List<Medicamento> medicamentos = medicamentoService.listarMedicamentosPorProveedor(idProveedor);
        return medicamentos.stream()
                .map(med -> new MedicamentoDTO(med.getId_medicamento(), med.getNombre(), med.getPrecio()))
                .collect(Collectors.toList());
    }


}
