package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
//import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	private static final String STANZA_BLOCCATA_MAKER = "Bloccata:";

	private static final String STANZA_BUIA_MAKER = "Buia:";

	private static final String STANZA_MAGICA_MAKER = "Magica:";

	private static final String PERSONAGGIO_CANE_MAKER = "Cane:";

	private static final String PERSONAGGIO_MAGO_MAKER = "Mago:";

	private static final String PERSONAGGIO_STREGA_MAKER = "Strega:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	//private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto.LabirintoBuilder labirintoBuilder;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.labirintoBuilder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.reader = new LineNumberReader(reader);
		this.labirintoBuilder = Labirinto.newBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECreaBloccata();
			this.leggiECreaBuia();
			this.leggiECreaMagica();
			this.leggiECollocaCane();
			this.leggiECollocaMago();
			this.leggiECollocaStrega();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			nomeStanza = nomeStanza.substring(1);
			this.labirintoBuilder = this.labirintoBuilder.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale.substring(1)), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente.substring(1)), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.labirintoBuilder.getListaStanze().get(nomeStanzaIniziale.substring(1));
		this.stanzaVincente = this.labirintoBuilder.getListaStanze().get(nomeStanzaVincente.substring(1));
	}

	private void leggiECreaBloccata() throws FormatoFileNonValidoException{
		String specificheBloccate = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MAKER);

		for(String specificaBloccata : separaStringheAlleVirgole(specificheBloccate)) {
			String nomeStanza = null;
			String direzioneBloccata = null;
			String nomeChiave = null;
			try(Scanner scannerLinea = new Scanner(specificaBloccata)){
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la direzione bloccata"));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome della chiave"));
				nomeChiave = scannerLinea.next();
			}
			this.labirintoBuilder.addStanzaBloccata(nomeStanza, direzioneBloccata, nomeChiave);
		}
	}

	private void leggiECreaBuia() throws FormatoFileNonValidoException {
		String specificheBuie = this.leggiRigaCheCominciaPer(STANZA_BUIA_MAKER);

		for(String specificaBuia : separaStringheAlleVirgole(specificheBuie)) {
			String nomeStanza = null;
			String nomeLuce = null;
			try(Scanner scannerLinea = new Scanner(specificaBuia)){
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo per illuminare"));
				nomeLuce = scannerLinea.next();
			}
			this.labirintoBuilder.addStanzaBuia(nomeStanza, nomeLuce);
		}
	}

	private void leggiECreaMagica() throws FormatoFileNonValidoException {
		String specificheMagiche = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MAKER);

		for(String specificaMagica : separaStringheAlleVirgole(specificheMagiche)) {
			String nomeStanza = null;
			String sogliaMagica = null;
			try(Scanner scannerLinea = new Scanner(specificaMagica)){
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il numero della soglia magica"));
				sogliaMagica = scannerLinea.next();
			}
			collocaMagica(nomeStanza, sogliaMagica);
		}
	}

	private void collocaMagica(String nomeStanza, String sogliaMagica) throws FormatoFileNonValidoException {
		int soglia;
		try {
			soglia = Integer.parseInt(sogliaMagica);
			check(isStanzaValida(nomeStanza),"Impossibile creare stanza magica : stanza " + nomeStanza +" inesistente");
			this.labirintoBuilder = this.labirintoBuilder.addStanzaMagica(nomeStanza, soglia);
		}
		catch (NumberFormatException e) {
			check(false, "Soglia magica non valida");
		}
	}

	private void leggiECollocaCane() throws FormatoFileNonValidoException{
		String specificheCani = this.leggiRigaCheCominciaPer(PERSONAGGIO_CANE_MAKER);

		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String nomeCibo = null;
			String nomeRegalo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cibo."));
				nomeCibo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del regalo."));
				nomeRegalo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomeCane+"."));
				nomeStanza = scannerLinea.next();
			}
			this.labirintoBuilder.setUltima(nomeStanza);
			this.labirintoBuilder.addCane(nomeStanza, nomeCibo, nomeRegalo);
		}
	}

	private void leggiECollocaMago() throws FormatoFileNonValidoException{
		String specificheMaghi = this.leggiRigaCheCominciaPer(PERSONAGGIO_MAGO_MAKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String nomeRegalo = null;
			String pesoRegalo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del regalo."));
				nomeRegalo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso del regalo."));
				pesoRegalo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomeMago+"."));
				nomeStanza = scannerLinea.next();
			}
			aggiungiAlMago(nomeMago, nomeRegalo, pesoRegalo, nomeStanza);
		}
	}

	private void aggiungiAlMago(String nomeMago, String nomeRegalo, String pesoRegalo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoRegalo);
			check(isStanzaValida(nomeStanza),"Regalo "+ nomeRegalo +" non collocabile: stanza " +nomeStanza+" inesistente");
			this.labirintoBuilder.setUltima(nomeStanza);
			this.labirintoBuilder = this.labirintoBuilder.addMago(nomeMago, nomeRegalo, peso);
		}
		catch (NumberFormatException e) {
			check(false, "Peso regalo "+nomeRegalo+" non valido");
		}
	}

	private void leggiECollocaStrega() throws FormatoFileNonValidoException{
		String specificheStreghe = this.leggiRigaCheCominciaPer(PERSONAGGIO_STREGA_MAKER);

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomeStrega+"."));
				nomeStanza = scannerLinea.next();
			}
			this.labirintoBuilder.setUltima(nomeStanza);
			this.labirintoBuilder.addStrega(nomeStrega);
		}
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.labirintoBuilder.setUltima(nomeStanza);
			this.labirintoBuilder = this.labirintoBuilder.addAttrezzo(nomeAttrezzo, peso);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.labirintoBuilder.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : this.separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.labirintoBuilder = this.labirintoBuilder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}