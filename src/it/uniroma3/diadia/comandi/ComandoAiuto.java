package it.uniroma3.diadia.comandi;

/*import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;*/

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "guarda","aiuto","fine"};
	
	@Override
	public void esegui(Partita partita) {
		IO io = this.getIo();
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}
	
	/*@Override
	public void esegui(Partita partita) {
		IO io = this.getIo();
		int i = 0;
		List<Class<?>> classiComando = new ArrayList<>();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			String nomePkg = getClass().getPackage().getName();
			//StringBuilder nomePkg = new StringBuilder();
			//nomePkg.append(getClass().getPackage().getName()).append(".Comando");
			Enumeration<URL> files = classLoader.getResources(nomePkg.substring(0).replace(".", "/") + "/");
			
			System.out.println("nomePkg:\t" + nomePkg);
			System.out.println("loader:\t\t" + classLoader.getResource("Comando.class"));
			System.out.println("clazz:\t\t"  + getClass().getResource("Comando.class"));
			System.out.println("files:\t\t" + files + "\n");
			
			Class<?> c = classLoader.loadClass(nomePkg + "." + "Comando");
			Class<?>[] clazzes = c.getClasses();
			System.out.println(clazzes.length);

			while(files.hasMoreElements()) {
				URL file = files.nextElement();
				String nomeFile = file.getFile();
				
				System.out.println("file:\t\t" + file);
				System.out.println("nomeFile:\t" + nomeFile + "\n");
				
				//if(nomeFile.endsWith(".class")) {
					String nomeClasse = "ComandoVai";
					Class<?> classeComando = classLoader.loadClass(nomePkg + "." + nomeClasse);
					if(!classeComando.equals(Comando.class))
						classiComando.add(classeComando);
				//}
					i++;
			}
		} catch (IOException | ClassNotFoundException e) {
			io.mostraMessaggio("errore");
		}
		System.out.println("i = " + i);
		System.out.println(classiComando.size());
		for(Class<?> classeComando : classiComando) {
			io.mostraMessaggio(classeComando.getSimpleName().replace("Comando", ""));
		}
	}*/

}
