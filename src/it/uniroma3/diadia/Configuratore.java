package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

public class Configuratore {
	private static Properties prp = null;
	
	private static void carica() {
		prp = new Properties();
		try {
			//String urlFile = new StringBuilder().append(Configuratore.class.getResource("/costanti/diadia.properties")).substring(0);
			//FileInputStream input = new FileInputStream(urlFile.substring(urlFile.indexOf("/")));
			prp.load(Configuratore.class.getResourceAsStream("/costanti/diadia.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getPesoMaxBorsa() {
		if(prp == null)
			carica();
		return Integer.parseInt(prp.getProperty("peso_max_borsa"));
	}
	
	public static int getCfuIniziali() {
		if(prp == null)
			carica();
		return Integer.parseInt(prp.getProperty("cfu_iniziali"));
	}
	
	public static String getMessaggioBenvenuto() {
		if(prp == null)
			carica();
		return prp.getProperty("messaggio_benvenuto");
	}
	
	public static int getSogliaMagicaDefault() {
		if(prp == null)
			carica();
		return Integer.parseInt(prp.getProperty("soglia_magica_default"));
	}
}
