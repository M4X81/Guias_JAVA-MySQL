/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistence;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entity.Fabricante;

/**
 *
 * @author Max
 */
public final class FabricanteDao extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe ingresar un fabricante");

            }
            String sql = " INSERT INTO Fabricante (codigo,nombre)"
                    + " VALUES  ('" + fabricante.getCodigo() + "' , '" + fabricante.getNombre()
                    + "' ); ";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe ingresar un fabricante que desea modificar");
            }
            String sql = "UPDATE Fabricante SET"
                    + " nombre ='" + fabricante.getNombre() + "' WHERE codigo = '" + fabricante.getCodigo() + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }


    public void eliminarFabricante(int codigo) throws Exception {

        try {

            String sql = "DELETE FROM Usuario WHERE codigo = '" + codigo + "'";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Fabricante buscarFabricantePorCodigo(int codigo) throws Exception {
        try {

            String sql = "SELECT * FROM Fabricante "
                    + " WHERE codigo = '" + codigo + "'";

            consultarBase(sql);

            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {

            String sql = "SELECT * FROM Fabricante "
                    + " WHERE id = '" + nombre + "'";

            consultarBase(sql);

            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Collection<Fabricante> listarFabricantes() throws Exception {
         Collection<Fabricante> fabricantes = new ArrayList();
         
        try {
            String sql = "SELECT codigo, nombre FROM Fabricante ";

            consultarBase(sql);

            Fabricante fabricante = null;
           
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                fabricantes.add(fabricante);
            }
            desconectarBase();
           
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
        for (Fabricante fabricante : fabricantes) {
            System.out.println(fabricante.getCodigo() + " _ " + fabricante.getNombre());
        }
        
        return fabricantes;
    }
}

