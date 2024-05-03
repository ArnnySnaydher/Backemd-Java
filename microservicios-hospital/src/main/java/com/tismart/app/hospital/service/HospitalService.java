package com.tismart.app.hospital.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tismart.app.hospital.data.HospitalDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class HospitalService {
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public String eliminarHospital(Long idHospital) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_HOSPITAL_ELIMINAR")
				.registerStoredProcedureParameter("sp_idHospital", Long.class, ParameterMode.IN)
				.setParameter("sp_idHospital", idHospital);

		try {
			query.execute();
			return "Hospital eliminado exitosamente.";
		} catch (Exception e) {
			return "Error al eliminar el hospital: " + e.getMessage();
		}
	}

	@Transactional
	public String registrarHospital(HospitalDTO hospitalDTO) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_HOSPITAL_REGISTRAR")
				.registerStoredProcedureParameter("sp_idDistrito", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_Nombre", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_Antiguedad", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_Area", Double.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idSede", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idGerente", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idCondicion", Long.class, ParameterMode.IN)
				.setParameter("sp_idDistrito", hospitalDTO.getIdDistrito())
				.setParameter("sp_Nombre", hospitalDTO.getNombre())
				.setParameter("sp_Antiguedad", hospitalDTO.getAntiguedad())
				.setParameter("sp_Area", hospitalDTO.getArea()).setParameter("sp_idSede", hospitalDTO.getIdSede())
				.setParameter("sp_idGerente", hospitalDTO.getIdGerente())
				.setParameter("sp_idCondicion", hospitalDTO.getIdCondicion());

		try {
			query.execute();
			return "Hospital registrado exitosamente.";
		} catch (Exception e) {

			return "Error al registrar el hospital: " + e.getMessage();

		}
	}

	@Transactional
	public String actualizarHospital(Long idHospital, HospitalDTO hospitalDTO) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_HOSPITAL_ACTUALIZAR")
				.registerStoredProcedureParameter("sp_idHospital", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idSede", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idDistrito", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idGerente", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sp_idCondicion", Long.class, ParameterMode.IN)
				.setParameter("sp_idHospital", idHospital).setParameter("sp_idSede", hospitalDTO.getIdSede())
				.setParameter("sp_idDistrito", hospitalDTO.getIdDistrito())
				.setParameter("sp_idGerente", hospitalDTO.getIdGerente())
				.setParameter("sp_idCondicion", hospitalDTO.getIdCondicion());
		try {
			query.execute();
			return "Hospital actualizado correctamente.";
		} catch (PersistenceException pe) {
			Throwable cause = pe.getCause();
			if (cause instanceof SQLException) {
				SQLException sqlEx = (SQLException) cause;
				if (sqlEx.getErrorCode() == 20001) {
					return "Error al actualizar el hospital: El Gerente ya está siendo utilizado por otro hospital.";
				}
			}
			return "Error al actualizar el hospital: " + pe.getMessage();
		} catch (Exception e) {
			return "Error al actualizar el hospital: " + e.getMessage();
		}
	}
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	 @Transactional
	    public List<HospitalDTO> listarHospitales() {
	        List<HospitalDTO> hospitales = new ArrayList<>();

	        // llamo a mi sp
	        jdbcTemplate.execute((Connection connection) -> {
	            try (CallableStatement call = connection.prepareCall("{call SP_LISTAR_HOSPITALES(?)}")) {
	                call.registerOutParameter(1, OracleTypes.CURSOR); // 1 es el índice del parámetro de salida
	                call.execute();

	                try (ResultSet rs = (ResultSet) call.getObject(1)) {
	                    while (rs.next()) {
	                        HospitalDTO hospital = new HospitalDTO();
	                        hospital.setIdHospital(rs.getLong("idHospital"));
	                        hospital.setIdDistrito(rs.getLong("idDistrito"));
	                        hospital.setNombre(rs.getString("Nombre"));
	                        hospital.setAntiguedad(rs.getInt("Antiguedad"));
	                        hospital.setArea(rs.getDouble("Area"));
	                        hospital.setIdSede(rs.getLong("idSede"));
	                        hospital.setIdGerente(rs.getLong("idGerente"));
	                        hospital.setIdCondicion(rs.getLong("idCondicion"));
	                        hospital.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
	                        hospitales.add(hospital);
	                    }
	                }
	            }
	            return null; 
	        });

	        return hospitales;
	    }
	 
	 
//	@Transactional
//	public List<HospitalDTO> listarHospitales() {
//	    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_LISTAR_HOSPITALES")
//	        .registerStoredProcedureParameter("sp_hospitales", void.class, ParameterMode.REF_CURSOR);
//
//	    query.execute();
//		
//	    List<Object[]> result = query.getResultList();
//	    
//	    List<HospitalDTO> hospitales = new ArrayList<>();
//	    
//	    for (Object[] row : result) {
//	        HospitalDTO hospital = new HospitalDTO();
//
//	        hospital.setIdDistrito((Long) row[1]);
//	        hospital.setNombre((String) row[2]);
//	        hospital.setAntiguedad((Integer) row[3]);
//	        hospital.setArea((Double) row[4]);
//	        hospital.setIdSede((Long) row[5]);
//	        hospital.setIdGerente((Long) row[6]);
//	        hospital.setIdCondicion((Long) row[7]);
//	        hospitales.add(hospital);
//	    }
//
//	    return hospitales;
//	}

}
