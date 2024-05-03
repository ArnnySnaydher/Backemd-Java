package com.tismart.app.hospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tismart.app.hospital.data.HospitalDTO;
import com.tismart.app.hospital.service.HospitalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sp")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> eliminarHospital(@PathVariable Long id) {
//        String resultado = hospitalService.eliminarHospital(id);
//        return ResponseEntity.ok(resultado);
//    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> eliminarHospital(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		try {
			String resultado = hospitalService.eliminarHospital(id);
			response.put("status", "success");
			response.put("message", resultado);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "Error al eliminar el hospital: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/registrar")
	public ResponseEntity<Map<String, String>> registrarHospital(@RequestBody HospitalDTO hospitalDTO) {
		Map<String, String> response = new HashMap<>();

		try {
			String resultado = hospitalService.registrarHospital(hospitalDTO);
			response.put("status", "success");
			response.put("message", resultado);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "Error al registrar hospital: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}
	}

//    @PostMapping("/registrar")
//    public ResponseEntity<String> registrarHospital(@RequestBody HospitalDTO hospitalDTO) {
//        String resultado = hospitalService.registrarHospital(hospitalDTO);
//        return ResponseEntity.ok(resultado);
//    }
//    
    @PutMapping("/actualizar/{idHospital}")
    public ResponseEntity<Map<String, String>> actualizarHospital(@PathVariable Long idHospital, @RequestBody HospitalDTO hospitalDTO) {
    	Map<String, String> response = new HashMap<>();
    	
    	try {
    		String resultado = hospitalService.actualizarHospital(idHospital, hospitalDTO);
    		
    		response.put("status", "success");
			response.put("message", resultado);
            return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "Error al actulizar hospital: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
    	
    }

//    
//    @GetMapping("/listar")
//    public List<HospitalDTO> listarHospitales() {
//        return hospitalService.listarHospitales();
//    }

    @GetMapping("/listar")
    public ResponseEntity<List<HospitalDTO>> listarHospitales() {
        List<HospitalDTO> hospitales = hospitalService.listarHospitales();
        return ResponseEntity.ok(hospitales);
    }
    
//    @GetMapping("/listar")
//    public ResponseEntity<Map<String, Object>> listarHospitales() {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            List<HospitalDTO> hospitales = hospitalService.listarHospitales();
//            response.put("status", "success");
//            response.put("data", hospitales);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.put("status", "error");
//            response.put("message", "Error al obtener la lista de hospitales: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }

}
