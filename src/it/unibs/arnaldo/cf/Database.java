package it.unibs.arnaldo.cf;

import java.util.ArrayList;

public class Database {
	private ArrayList<Persona> persone;
	private ArrayList<String> cfs;
	
	public Database() {
		this.persone = new ArrayList<Persona>();
		cfs = null;
	}
	
	public void addPersona(Persona p) {
		persone.add(p);
	}
	
	public void readCfs() {
		XMLReaderCF xmlrc = new XMLReaderCF("codiciFiscali.xml");
		cfs = xmlrc.read();
	}
	
	public void genAll() {
		for(Persona p : persone)
			p.genCodiceFiscale();
	}
	
	public boolean cmpAll() {
		boolean ris = false;
		//METODO DA CREARE
		return ris;
	}
	
	public void printAllPersone() {
		for(Persona p : persone)
			System.out.println(p);
	}
	public void printAllCFs() {
		for(String s : cfs)
			System.out.println(s);
	}
}
