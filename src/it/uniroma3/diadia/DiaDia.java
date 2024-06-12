package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = Configuratore.getMessaggioBenvenuto();

	private Partita partita;
	private IO io;
	
	public DiaDia(Labirinto labirinto, IO console) {
		this.partita = new Partita(labirinto);
		this.io = console;
	}
	
	public DiaDia(IO console){
		this.io = console;
	}

	public void gioca() {
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   	

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(this.io);
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		
		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
		if (this.partita.giocatoreIsMorto())
			this.io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) throws Exception {
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);
		Labirinto labirinto = Labirinto.newBuilder("/livelli/labirinto1.txt").getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		scanner.close();
	}
}