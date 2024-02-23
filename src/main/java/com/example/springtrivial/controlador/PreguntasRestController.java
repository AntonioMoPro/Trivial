package com.example.springtrivial.controlador;

import com.example.springtrivial.modelo.Preguntas;
import com.example.springtrivial.response.PreguntasResponseRest;
import com.example.springtrivial.servicio.IPreguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rh-app")
//localhost:8080/api/rh-app
public class PreguntasRestController {
	
	@Autowired
	private IPreguntasService service;
	//localhost:8080/api/rh-app/empleados
	
	@GetMapping("/preguntas")
	public ResponseEntity<PreguntasResponseRest> consultaCat() {
		
		ResponseEntity<PreguntasResponseRest> response = service.buscarPreguntas();
		return response;
	}
	
	@GetMapping("/preguntas/{id}")
	public ResponseEntity<PreguntasResponseRest> consultaPorId(@PathVariable int id) {
		ResponseEntity<PreguntasResponseRest> response = service.buscarPorId(id);
		return response;
	}

	@GetMapping("/preguntas/categoria/{categoria}")
	public ResponseEntity<PreguntasResponseRest> consultaPorCategoria(@PathVariable String categoria) {
		ResponseEntity<PreguntasResponseRest> response = service.buscarPorCategoria(categoria);
		return response;
	}

	@GetMapping("/preguntas/r")
	public ResponseEntity<PreguntasResponseRest> preguntaRandom() {
		ResponseEntity<PreguntasResponseRest> response = service.preguntaRandom();
		return response;
	}
	
	
	@PostMapping("/preguntas")
	public ResponseEntity<PreguntasResponseRest> crear(@RequestBody Preguntas request) {
		ResponseEntity<PreguntasResponseRest> response = service.crear(request);
		return response;
	}
	
	@PutMapping("/preguntas/{id}")
	public ResponseEntity<PreguntasResponseRest> actualizar (@RequestBody Preguntas request, @PathVariable int id) {
		ResponseEntity<PreguntasResponseRest> response = service.actualizar(request, id);
		return response;
	}
	
	@DeleteMapping("/preguntas/{id}")
	public ResponseEntity<PreguntasResponseRest> eliminar (@PathVariable int id) {
		ResponseEntity<PreguntasResponseRest> response = service.eliminar(id);
		return response;
	}

}
