package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	static final private String nome = "fine";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		io.mostraMessaggio("Grazie per aver giocato");
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

}
