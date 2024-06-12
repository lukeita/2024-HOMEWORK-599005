package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	final static private String MESSAGGIO = "GRRRRRR WOOOF WOOOF!";
	final static private String MESSAGGIO_RINGRAZIAMENTO = "Arf Arf!";
	final static private String MESSAGGIO_RIFUITO = "Grrrrrrrrrr!";
	private Attrezzo ciboPreferito;
	private Attrezzo regalo;
	
	public Cane(String nome, String presentazione, Attrezzo ciboPreferito, Attrezzo regalo) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.regalo = regalo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MESSAGGIO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(this.ciboPreferito.equals(attrezzo)) {
			partita.getStanzaCorrente().addAttrezzo(regalo);
			return MESSAGGIO_RINGRAZIAMENTO;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return MESSAGGIO_RIFUITO;
		}
	}

}
