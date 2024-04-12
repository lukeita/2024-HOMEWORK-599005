package it.uniroma3.diadia.giocatore;

/**
 * Classe che gestisce i cfu e memorizza gli attrezzi nella borsa
 * 
 * @author 	Luca Italiano
 * @see		Borsa
 * @version	0.1
 */
public class Giocatore {	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	/**
	 * Crea giocatore 
	 * @param cfu 		i cfu che ha il giocatore all'inizio della partita
	 * @param borsa		l'inventario dove il giocatore andrà ad inserire gli oggetti	
	 */
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	/**
	 * Restituisce l'accesso all'inventario del giocatore
	 * @return inventario del giocatore 
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	/**
	 * Restituisce la quantità di cfu che ha il giocatore
	 * @return quantità attuale di cfu del giocatore
	 */
	public int getCfu(){
		return this.cfu;
	}
	
	/**
	 * Imposta il numero di cfu del giocatore
	 * @param cfu del giocatore
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
}
