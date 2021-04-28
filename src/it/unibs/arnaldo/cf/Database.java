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
}
