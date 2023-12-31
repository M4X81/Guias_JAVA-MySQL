/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.service;

import java.util.Collection;
import tienda.entity.Fabricante;
import tienda.entity.Producto;
import tienda.persistence.FabricanteDao;

/**
 *
 * @author Max
 */
public class FabricanteService {

    private FabricanteDao dao;

    public FabricanteService() {
        this.dao = new FabricanteDao();
    }

    public void crearFabricante(int codigo, String nombre, Producto producto) throws Exception {

        try {
            Fabricante fabricante = new Fabricante();
            fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);
            dao.guardarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {

        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            Fabricante fabricante = dao.buscarFabricantePorNombre(nombre);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorCodigo(int codigo) throws Exception {

        try {

            /* //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el id");
            }*/
            Fabricante fabricante = dao.buscarFabricantePorCodigo(codigo);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricante() throws Exception {

        try {

            Collection<Fabricante> fabricantes = dao.listarFabricantes();

            return fabricantes;
        } catch (Exception e) {
            throw e;
        }
    }
}
