package it.unibs.arnaldo.cf;

import java.util.ArrayList;

/**
 * Metodo che gestisce tutti i dati
 * Contiene un ArrayList con tutte le persone e i codici fiscali
 * Vengono estratti dai cfs quelli invalidi e quelli spaiati, immagazzinati separatamente
 * 
 * @author toBdefined
 *
 */
public class Database {
	private ArrayList<Persona> persone;
	private ArrayList<String> cfs;
	private ArrayList<String> cfsInvalidi;
	private ArrayList<String> cfsSpaiati;
	
	//Metodo costruttore del database, crea l'arraylist di persone e metto a null gli altri
	public Database() {
		XMLReaderPersone xmlr = new XMLReaderPersone("inputPersone.xml");
		this.persone = xmlr.read();
		cfs = null;
		cfsInvalidi = null;
		cfsSpaiati = null;
	}
	
	//Getters degli arraylist, usati per passarli al writer
	public ArrayList<Persona> getPersone(){
		return persone;
	}
	public ArrayList<String> getCfsInvalidi(){
		return cfsInvalidi;
	}
	public ArrayList<String> getCfsSpaiati(){
		return cfsSpaiati;
	}
	
	//Ritorna la posizione della persona, dato il suo codice fiscale
	//-1 se è assente
	public int getPersonaByCF(String cf) {
		for(Persona p:persone)
			if(p.getCf().equals(cf))
				return persone.indexOf(p);
		
		return -1;
	}
	
	//Metodo usato per leggere i codici fiscali da file
	public void readCfs() {
		XMLReaderCF xmlrc = new XMLReaderCF("codiciFiscali.xml");
		cfs = xmlrc.read();
	}
	
	//Metodo usato per scrivere il file
	public void writeCfs() {
		GestoreXMLWriter xmlw = new GestoreXMLWriter("codiciPersone.xml");
		xmlw.scriviXML(persone, cfsInvalidi, cfsSpaiati);
		System.out.println("Fine scrittura file.");
	}
	
	//Metodo per che verifica la validità dei codici fiscali
	//I codici non validi vengono tolti da cfs e messi in cfsInvalidi
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

	//Metodo per che verifica se i codici fiscali sono spaiati
	//I codici spaiati vengono tolti da cfs e messi in cfsSpaiati
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
	
	//Metodo per genererare tutti i codici fiscali di persone
	public void genAll() {
		for(Persona p : persone)
			p.genCodiceFiscale();
	}
	
	//Metodo per stampare tutte le persone
	public void printAllPersone() {
		for(Persona p : persone)
			System.out.println(p);
	}
	
	//Metodo per stampare tutte le persone che non sono assenti
	public void printAllPresenti() {
		for(Persona p : persone)
			if(!p.isAssente())
				System.out.println(p);
	}
	
	//Metodo per la scrittura di tutti i codici fiscali
	public void printAllCFs() {
		for(String s : cfs)
			System.out.println(s);
	}
	
	//Metodo per la scrittura di tutti i codici fiscali invalidi
	public void printAllCFsInvalidi() {
		for(String s : cfsInvalidi)
			System.out.println(s);
	}
	
	//Metodo per la scrittura di tutti i codici spaiati
	public void printAllCFsSpaiati() {
		for(String s : cfsSpaiati)
			System.out.println(s);
	}
}
