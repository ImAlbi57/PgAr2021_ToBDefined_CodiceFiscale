package it.unibs.arnaldo.cf;

import java.util.ArrayList;

public class Database {
	private ArrayList<Persona> persone;
	
	public Database() {
		this.persone = new ArrayList<Persona>();
	}
	/*
	public Database(ArrayList<Persona> persone) {
		this.persone = persone;
	}*/
	
	public void addPersona(Persona p) {
		persone.add(p);
	}
	
	public void genAll() {
		for(Persona p : persone)
			p.genCodiceFiscale();
	}
	
	public void printAll() {
		for(Persona p : persone)
			System.out.println(p);
	}
}
