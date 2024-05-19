package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOSimulator implements IO {
	private List<String> input;
	private Iterator<String> itIn;
	private List<String> output;
	
	public IOSimulator(List<String> in) {
		this.input = new ArrayList<>(in);
		this.output = new ArrayList<>();
		this.itIn = this.input.iterator();
	}
	
	public List<String> getInput(){
		return this.input;
	}
	
	public List<String> getOutput(){
		return this.output;
	}

	@Override
	public String leggiRiga() {
		if(itIn.hasNext())
			return itIn.next();
		return null;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.output.add(messaggio);
	}

}
