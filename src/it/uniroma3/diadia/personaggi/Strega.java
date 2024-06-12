package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	final static private String MESSAGGIO_ARRABIATA = "Un maleducato che non saluta merita una punizione";
	final static private String MESSAGGIO_CALMA = "Ti porto dove puoi appesantire quel borsone che hai";
	final static private String MESSAGGIO_RINGRAZIAMENTO = "AHAHAHAHAHAHAHAHAAH!";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza tp = null;
		int nAtt = 0;
		
		if(!this.haSalutato()) {
			for(Stanza accanto : partita.getStanzaCorrente().getMapStanzeAdiacenti().values())
				if(tp == null || accanto.getAttrezzi().size() < nAtt) {
					tp = accanto;
					nAtt = tp.getAttrezzi().size();
				}
			partita.setStanzaCorrente(tp);
			return MESSAGGIO_ARRABIATA;
		}
		else {
			for(Stanza accanto : partita.getStanzaCorrente().getMapStanzeAdiacenti().values())
				if(tp == null || accanto.getAttrezzi().size() > nAtt) {
					tp = accanto;
					nAtt = tp.getAttrezzi().size();
				}
			partita.setStanzaCorrente(tp);
			return MESSAGGIO_CALMA;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RINGRAZIAMENTO;
	}


}
