package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Classe che gestisce l'input e l'output del gioco
 * 
 * @author docente di POO
 * @version base
 */
public class IOConsole implements IO{
	
	/**
	 * Stampa a schermo il messaggio
	 * @param msg	messaggio da stampare a schermo
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();							per adesso questa riga è omessa
		return riga;
	}
}