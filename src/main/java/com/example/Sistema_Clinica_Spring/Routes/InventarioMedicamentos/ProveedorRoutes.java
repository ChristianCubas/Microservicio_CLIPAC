package com.example.Sistema_Clinica_Spring.Routes.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Proveedor;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.ServiceProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProveedorRoutes {
    @Autowired
    private ServiceProveedor serviceProveedor;

    @GetMapping("/proveedores")
    public String listarProveedores(Model model){
        List<Proveedor> listadoProveedores = serviceProveedor.listarProveedores();
        model.addAttribute("proveedores",listadoProveedores);
        return "dashboard/Apartados/Proveedores";
    }

    @GetMapping("/proveedores/nuevo")
    public String nuevoProveedor(Model model){
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("accion", "/proveedores/guardar");
        model.addAttribute("titulo", "Crear Proveedor");
        model.addAttribute("botonTexto", "Guardar");
        return "dashboard/CRUDs/FormProveedores";
    }

    @GetMapping("/proveedores/editar/{id}")
    public String editarProveedor(@PathVariable Long id, Model model) {

        Proveedor proveedor = serviceProveedor.obtenerProveedor(id);

        model.addAttribute("proveedor", proveedor);
        model.addAttribute("accion", "/proveedores/actualizar/" + id);
        model.addAttribute("titulo", "Editar Proveedor");
        model.addAttribute("botonTexto", "Actualizar");

        return "dashboard/CRUDs/FormProveedores";
    }

    @PostMapping("/proveedores/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        serviceProveedor.crearProveedor(proveedor);
        return "redirect:/proveedores";
    }

    @PostMapping("/proveedores/actualizar/{id}")
    public String actualizarProveedor(@PathVariable Long id, @ModelAttribute Proveedor proveedor){
        serviceProveedor.actualizarProveedor(id,proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/baja/{id}")
    public String darBajaProveedor(@PathVariable Long id){
        serviceProveedor.darBajaProveedor(id);
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id){
        serviceProveedor.eliminarProveedor(id);
        return "redirect:/proveedores";
    }

}
