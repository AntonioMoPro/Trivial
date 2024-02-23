package com.example.springtrivial.response;

import com.example.springtrivial.modelo.Preguntas;

import java.util.List;



public class PreguntasResponse {
	
	private List<Preguntas> preguntas;

	public List<Preguntas> getpreguntas() {
		return preguntas;
	}

	public void setCategoria(List<Preguntas> preguntas) {
		this.preguntas = preguntas;
	}

}
