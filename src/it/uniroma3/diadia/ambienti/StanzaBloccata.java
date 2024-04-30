package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private String blocco;
	private String chiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
		super(nome);
		this.blocco = direzioneBloccata;
		this.chiave = attrezzoChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.blocco) && !this.hasAttrezzo(chiave))
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		super.toString();
		return "A quanto pare l'uscita" + this.blocco + "è bloccata, mi servirà un" + this.chiave + "per sbloccarla";
	}
	
}
