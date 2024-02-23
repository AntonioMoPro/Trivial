package com.example.springtrivial.response;

public class PreguntasResponseRest extends ResponseRest{

	private PreguntasResponse preguntasResponse = new PreguntasResponse();

	public PreguntasResponse getPreguntasResponse() {
		return preguntasResponse;
	}

	public void setPreguntasResponse(PreguntasResponse preguntasResponse) {
		this.preguntasResponse = preguntasResponse;
	}
}
