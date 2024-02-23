package com.example.springtrivial.servicio;


import com.example.springtrivial.modelo.Preguntas;
import com.example.springtrivial.modelo.dao.IPreguntasDao;
import com.example.springtrivial.response.PreguntasResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PreguntasServiceImpl implements IPreguntasService {

	private static final Logger log = LoggerFactory.getLogger(PreguntasServiceImpl.class);
	
	@Autowired
	private IPreguntasDao preguntasDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PreguntasResponseRest> buscarPreguntas() {
		
		log.info("inicio metodo buscarPreguntas()");
		
		PreguntasResponseRest response = new PreguntasResponseRest();
		
		try {
			
			List<Preguntas> preguntas = (List<Preguntas>) preguntasDao.findAll();
			
			response.getPreguntasResponse().setCategoria(preguntas);
			
			response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch (Exception e) {
			response.setMetada("Respuesta nok", "-1", "Error al consultar preguntas");
			log.error("error al consultar preguntas: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PreguntasResponseRest> preguntaRandom() {
		log.info("Inicio metodo buscarPorId)");

		PreguntasResponseRest response = new PreguntasResponseRest();
		List<Preguntas> list = new ArrayList<>();
		int id = new Random().nextInt(1, 61);

		try {

			Optional<Preguntas> pregunta = preguntasDao.findById(id);

			if (pregunta.isPresent()) {
				list.add(pregunta.get());
				response.getPreguntasResponse().setCategoria(list);
			} else {
				log.error("Error en consultar preguntas");
				response.setMetada("Respuesta nok", "-1", "Pregunta no encontrado");
				return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error("Error en consultar preguntas");
			response.setMetada("Respuesta nok", "-1", "Error al consultar preguntas");
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PreguntasResponseRest> buscarPorId(int id) {
		
		log.info("Inicio metodo buscarPorId)");
		
		PreguntasResponseRest response = new PreguntasResponseRest();
		List<Preguntas> list = new ArrayList<>();
		
		try {
			
			Optional<Preguntas> pregunta = preguntasDao.findById(id);
			
			if (pregunta.isPresent()) {
				list.add(pregunta.get());
				response.getPreguntasResponse().setCategoria(list);
			} else {
				log.error("Error en consultar preguntas");
				response.setMetada("Respuesta nok", "-1", "Pregunta no encontrado");
				return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error en consultar preguntas");
			response.setMetada("Respuesta nok", "-1", "Error al consultar preguntas");
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PreguntasResponseRest> buscarPorCategoria(String categoria) {

		log.info("Inicio metodo buscarPorCategoria)");

		PreguntasResponseRest response = new PreguntasResponseRest();

		try {

			List<Preguntas> preguntas = (List<Preguntas>) preguntasDao.findAll();
			List<Preguntas> categorias = new ArrayList<>();

			for (Preguntas preguntas1 : preguntas){
				if (preguntas1.getCategoria().equals(categoria)){
					categorias.add(preguntas1);
				}
			}

			response.getPreguntasResponse().setCategoria(categorias);

			response.setMetada("Respuesta ok", "00", "Respuesta exitosa");

		} catch (Exception e) {
			response.setMetada("Respuesta nok", "-1", "Error al consultar preguntas");
			log.error("error al consultar preguntas: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<PreguntasResponseRest> crear(Preguntas pregunta) {
		log.info("Inicio metodo crear preguntas");
		
		PreguntasResponseRest response = new PreguntasResponseRest();
		List<Preguntas> list = new ArrayList<>();
		
		try {
			
			Preguntas preguntaGuardada = preguntasDao.save(pregunta);
			
			if( preguntaGuardada != null) {
				list.add(preguntaGuardada);
				response.getPreguntasResponse().setCategoria(list);
			} else {
				log.error("Error en grabar pregunta");
				response.setMetada("Respuesta nok", "-1", "Pregunta no guardada");
				return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch( Exception e) {
			log.error("Error en grabar pregunta");
			response.setMetada("Respuesta nok", "-1", "Error al grabar pregunta");
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Pregunta creada");
		return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<PreguntasResponseRest> actualizar(Preguntas pregunta, int id) {
		
		log.info("Inicio metodo actualizar");
		
		PreguntasResponseRest response = new PreguntasResponseRest();
		List<Preguntas> list = new ArrayList<>();
		
		try {
			
			Optional<Preguntas> preguntaBuscada = preguntasDao.findById(id);
			
			if (preguntaBuscada.isPresent()) {
				preguntaBuscada.get().setPregunta(pregunta.getPregunta());
				preguntaBuscada.get().setCategoria(pregunta.getCategoria());
				 
				 Preguntas preguntasActualizar = preguntasDao.save(preguntaBuscada.get()); //actualizando
				 
				 if( preguntasActualizar != null ) {
					 response.setMetada("Respuesta ok", "00", "Preguta actualizado");
					 list.add(preguntasActualizar);
					 response.getPreguntasResponse().setCategoria(list);
				 } else {
					 log.error("error en actualizar categoria");
					 response.setMetada("Respuesta nok", "-1", "Pregunta no actualizada");
					 return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.BAD_REQUEST);
				 }
				 
				 
			} else {
				log.error("error en actualizar pregunta");
				response.setMetada("Respuesta nok", "-1", "Pregunta no actualizado");
				return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch ( Exception e) {
			log.error("error en actualizar pregunta", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta nok", "-1", "Pregunta no actualizada");
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<PreguntasResponseRest> eliminar(int id) {
		
		log.info("Inicio metodo eliminar categoria");
		
		PreguntasResponseRest response = new PreguntasResponseRest();
		
		 try {
			 
			 //eliminamos el registro
			 preguntasDao.deleteById(id);
			 response.setMetada("Respuesta ok", "00", "pregunta eliminada");
			 
		 } catch (Exception e) {
			log.error("error en eliminar pregunta", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta nok", "-1", "Categoria no eliminada");
			return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 
		 return new ResponseEntity<PreguntasResponseRest>(response, HttpStatus.OK);
		
	}

}
