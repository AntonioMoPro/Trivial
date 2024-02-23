package com.example.springtrivial.servicio;

import com.example.springtrivial.modelo.Preguntas;
import com.example.springtrivial.response.PreguntasResponseRest;
import org.springframework.http.ResponseEntity;

public interface IPreguntasService {
	
	public ResponseEntity<PreguntasResponseRest> buscarPreguntas();
	public ResponseEntity<PreguntasResponseRest> preguntaRandom();
	public ResponseEntity<PreguntasResponseRest> buscarPorId(int id);
	public ResponseEntity<PreguntasResponseRest> buscarPorCategoria(String categoria);
	public ResponseEntity<PreguntasResponseRest> crear(Preguntas pregunta);
	public ResponseEntity<PreguntasResponseRest> actualizar(Preguntas pregunta, int id);
	public ResponseEntity<PreguntasResponseRest> eliminar (int id);

}
