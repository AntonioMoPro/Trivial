package com.example.springtrivial;

import com.example.springtrivial.controlador.PreguntasRestController;
import com.example.springtrivial.modelo.Preguntas;
import com.example.springtrivial.modelo.dao.IPreguntasDao;
import com.example.springtrivial.repositorio.PreguntasRepositorio;
import com.example.springtrivial.response.PreguntasResponseRest;
import com.example.springtrivial.servicio.IPreguntasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PreguntasRestControllerTest {
    @InjectMocks
    PreguntasRestController preguntasController;

    @Mock
    private IPreguntasService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        Preguntas preguntas = new Preguntas("¿?", "Art");
        when(service.crear(any(Preguntas.class))).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.crear(preguntas);

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void buscarPreguntasTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(service.buscarPreguntas()).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.consultaCat();

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void buscarPreguntasPorIdTest() {

        int id = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(service.buscarPorId(id)).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.consultaPorId(id);

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void buscarPreguntasPorCategoriaTest() {

        String categoria = "Historia";

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(service.buscarPorCategoria(categoria)).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.consultaPorCategoria(categoria);

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void buscarPreguntaRandomTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(service.preguntaRandom()).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.preguntaRandom();

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void eliminarTest() {

        int id = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(service.eliminar(id)).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.eliminar(id);

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void updateTest() {

        int id = 1;
        Preguntas pregunta = new Preguntas("Mod. pregunta", "Mod. categoria");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((request)));

        when(service.actualizar(pregunta, id)).thenReturn(new ResponseEntity<PreguntasResponseRest>(HttpStatus.OK));
        ResponseEntity<PreguntasResponseRest> respuesta = preguntasController.actualizar(pregunta, id);

        assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);

    }
}
