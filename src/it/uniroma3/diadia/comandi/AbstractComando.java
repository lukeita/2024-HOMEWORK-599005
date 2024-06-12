package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	private IO io;
	private String parametro;
	
	abstract public void esegui(Partita partita);
	
	public void setIo(IO io) {
		this.io = io;
	}
	
	public IO getIo() {
		return this.io;
	}
	
	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
}
