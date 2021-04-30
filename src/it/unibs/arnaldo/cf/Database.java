package it.unibs.arnaldo.cf;

import java.util.ArrayList;

public class Database {
	private ArrayList<Persona> persone;
	private ArrayList<String> cfs;
	private ArrayList<String> cfsInvalidi;
	private ArrayList<String> cfsSpaiati;
	
	public Database() {
		this.persone = new ArrayList<Persona>();
		cfs = null;
		cfsInvalidi = null;
	}
	
	//Getters
	public ArrayList<Persona> getPersone(){
		return persone;
	}
	public ArrayList<String> getCfsInvalidi(){
		return cfsInvalidi;
	}
	public ArrayList<String> getCfsSpaiati(){
		return cfsSpaiati;
	}
	
	
	public void addPersona(Persona p) {
		persone.add(p);
	}
	
	//Ritorna la posizione della persona, dato il suo codice fiscale
	//-1 se è assente
	public int getPersonaByCF(String cf) {
		for(Persona p:persone)
			if(p.getCf().equals(cf))
				return persone.indexOf(p);
		
		return -1;
	}
	
	public void readCfs() {
		XMLReaderCF xmlrc = new XMLReaderCF("codiciFiscali.xml");
		cfs = xmlrc.read();
	}
	
	public void checkCfsValidi(){
		cfsInvalidi = new ArrayList<String>();
		int i=0;
		
		while(i < cfs.size()) {
			if(CodiceFiscale.validateCF(cfs.get(i)) == false) {
				cfsInvalidi.add(cfs.get(i));
				cfs.remove(cfs.get(i));
			}
			else i++;
		}
	}
	
	public void checkCfsSpaiati(){
		cfsSpaiati = new ArrayList<String>();
		//i = iteratore di cfs, pos = posizione della persona
		int i=0, pos;
		
		while(i < cfs.size()) {
			pos = getPersonaByCF(cfs.get(i));
			if(pos == -1) {
				cfsSpaiati.add(cfs.get(i));
				cfs.remove(cfs.get(i));
			}
			else{
				persone.get(pos).setAssente(false);
				i++;
			}
		}
	}
	/*
	public void checkPresenza() {
		for(Persona p:persone) {
			if(cfs.contains(p.getCf()))
				p.setAssente(false);
			else p.setAssente(true);
		}
	}*/
	
	public void genAll() {
		for(Persona p : persone)
			p.genCodiceFiscale();
	}
	
	public void printAllPersone() {
		for(Persona p : persone)
			System.out.println(p);
	}
	public void printAllPresenti() {
		for(Persona p : persone)
			if(!p.isAssente())
				System.out.println(p);
	}
	
	public void printAllCFs() {
		for(String s : cfs)
			System.out.println(s);
	}
	public void printAllCFsInvalidi() {
		for(String s : cfsInvalidi)
			System.out.println(s);
	}
	public void printAllCFsSpaiati() {
		for(String s : cfsSpaiati)
			System.out.println(s);
	}
}
