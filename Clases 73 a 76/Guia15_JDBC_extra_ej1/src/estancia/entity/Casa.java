/*
• Cada una de estas familias vive en una casa, de la que se conoce la dirección (calle, 
numero, código postal, ciudad y país), el periodo de disponibilidad de la casa 
(fecha_desde, fecha_hasta), la cantidad de días mínimo de estancia y la cantidad máxima 
de días, el precio de la habitación por día y el tipo de vivienda.
 */
package estancia.entity;

import java.util.Date;

/**
 *
 * @author Max
 */
public class Casa {

    private int id_casa;
    private String calle;
    private int numero;
    private int codigo_postal;
    private String ciudad;
    private String pais;
    private Date fecha_desde;
    private Date fecha_hasta;   
    private int tiempo_minimo;
    private int tiempo_maximo;
    private double precio_habitacion;
    private String tipo_vivienda;
    private Familia familia;

    public Casa() {
    }

    public Casa(int id_casa, String calle, int numero, int codigo_postal, String ciudad, String pais, Date fecha_desde, Date fecha_hasta, int tiempo_minimo, int tiempo_maximo, double precio_habitacion, String tipo_vivienda, Familia familia) {
        this.id_casa = id_casa;
        this.calle = calle;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
        this.tiempo_minimo = tiempo_minimo;
        this.tiempo_maximo = tiempo_maximo;
        this.precio_habitacion = precio_habitacion;
        this.tipo_vivienda = tipo_vivienda;
        this.familia = familia;
    }

    public int getId_casa() {
        return id_casa;
    }

    public void setId_casa(int id_casa) {
        this.id_casa = id_casa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public Date getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(Date fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public int getTiempo_minimo() {
        return tiempo_minimo;
    }

    public void setTiempo_minimo(int tiempo_minimo) {
        this.tiempo_minimo = tiempo_minimo;
    }

    public int getTiempo_maximo() {
        return tiempo_maximo;
    }

    public void setTiempo_maximo(int tiempo_maximo) {
        this.tiempo_maximo = tiempo_maximo;
    }

    public double getPrecio_habitacion() {
        return precio_habitacion;
    }

    public void setPrecio_habitacion(double precio_habitacion) {
        this.precio_habitacion = precio_habitacion;
    }

    public String getTipo_vivienda() {
        return tipo_vivienda;
    }

    public void setTipo_vivienda(String tipo_vivienda) {
        this.tipo_vivienda = tipo_vivienda;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
    
    
    

}
