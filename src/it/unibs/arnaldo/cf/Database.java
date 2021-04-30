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
	
	public ArrayList<String> checkCfs(){
		ArrayList<String> sbagliati = new ArrayList<String>();
		for(String cf:cfs) {
			if(CodiceFiscale.validateCF(cf) == false)
				sbagliati.add(cf);
		}
		return sbagliati;
	}
	
	public void genAll() {
		for(Persona p : persone)
			p.genCodiceFiscale();
	}
	
	public boolean cmpAll() {
		if(persone.size() != cfs.size())
			return false;
		
		for(int i=0; i<cfs.size(); i++) {
			String cfNostro = persone.get(i).getCf();
			String cfXML = cfs.get(i);
			System.out.println(cfNostro + " " + cfXML);
			if(!cfNostro.equals(cfXML))
				return false;
		}
		
		return true;
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
