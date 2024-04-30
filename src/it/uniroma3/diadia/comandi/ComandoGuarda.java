package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	static final private String nome = "guarda";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione() + "\nHai ancora " + partita.getGiocatore().getCfu() + " cfu");
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
