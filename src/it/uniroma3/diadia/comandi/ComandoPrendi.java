package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private String attrezzoRicercato;
	static final private String nome = "prendi";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().hasAttrezzo(this.attrezzoRicercato)){
			Attrezzo attrezzoSpostato = partita.getStanzaCorrente().getAttrezzo(this.attrezzoRicercato);
			partita.getStanzaCorrente().removeAttrezzo(this.attrezzoRicercato);
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzoSpostato);
		}
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
