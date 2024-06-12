package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	String luce;

	public StanzaBuia(String nome, String luce) {
		super(nome);
		this.luce = luce;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.luce))
			return this.toString();
		else
			return "qui c'è buio pesto";
	}

}
