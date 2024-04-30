package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPosa implements Comando {
	private String attrezzoRicercato;
	static final private String nome = "posa";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzoRicercato))
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoRicercato));
		else
			io.mostraMessaggio("Attrezzo non trovato");
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzoRicercato = parametro;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getParametro() {
		return this.attrezzoRicercato;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

}
