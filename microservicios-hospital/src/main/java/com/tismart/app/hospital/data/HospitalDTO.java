package com.tismart.app.hospital.data;

import java.sql.Timestamp;

public class HospitalDTO {
	
    private Long idHospital;
    
    private Long idDistrito;
    
    private String nombre;
    
    private Integer antiguedad;
    
    private Double area;
    
    private Long idSede;
    
    private Long idGerente;
    
    private Long idCondicion;
    
    private Timestamp fechaRegistro;
    
	public Long getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}
	public Long getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(Long idDistrito) {
		this.idDistrito = idDistrito;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	public Long getIdGerente() {
		return idGerente;
	}
	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}
	public Long getIdCondicion() {
		return idCondicion;
	}
	public void setIdCondicion(Long idCondicion) {
		this.idCondicion = idCondicion;
	}
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    
    

    

}
