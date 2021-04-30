package it.unibs.arnaldo.cf;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * 
 * @author toBdefined
 *
 */
public class GestoreXMLWriter {
	private XMLOutputFactory xmlof = null;
	private XMLStreamWriter xmlw = null;
	private int nTabs = 0;
	
	//Metodo costruttore del writer, crea un writer dato il path
	public GestoreXMLWriter(String path) {
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}
	}
	
	//Metodo per la scrittura dell'XML completo,
	//semplicemente richiama i metodi creati in seguito, usati opportunamente e con dei cicli
	public void scriviXML(ArrayList<Persona> persone, ArrayList<String> cfsInvalidi, ArrayList<String> cfsSpaiati) {
		try {
			iniziaXML();
				apriTag("output");
					apriTagConAttr("persone", "numero", ""+persone.size());
					for(Persona p : persone) {
						writePersona(p);
					}
					chiudiTag();
					
					
					apriTag("codici");
						apriTagConAttr("invalidi", "numero", ""+cfsInvalidi.size());
						for(String s : cfsInvalidi) {
							writeSimpleTag("codice", s);
						}
						chiudiTag();
						
						apriTagConAttr("spaiati", "numero", ""+cfsSpaiati.size());
						for(String s : cfsSpaiati) {
							writeSimpleTag("codice", s);
						}
						chiudiTag();
					chiudiTag();
					
				chiudiTag();
			chiudiXML();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Metodo che inizia il file XML, scrivendo l'intestazione
	private void iniziaXML() throws XMLStreamException {
		xmlw.writeStartDocument("utf-8", "1.0");aCapo();
	}

	//Metodo che termina l'XML, scrive i dati immagazzinati nella cache, dentro il file e chiude
	private void chiudiXML() throws XMLStreamException {
		xmlw.writeEndDocument();
		xmlw.flush();
		xmlw.close();
	}

	//Metodo da usare per aprire i tag
	private void apriTag(String s) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement(s);
		aCapo();
	}

	//Metodo da usare per aprire i tag con attributo
	private void apriTagConAttr(String s, String attr, String val) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement(s);
		xmlw.writeAttribute(attr, val);
		aCapo();
	}

	//Metodo da usare per chiudere i tag
	private void chiudiTag() throws XMLStreamException {
		tabula(--nTabs);
		xmlw.writeEndElement();
		aCapo();
	}

	//Metodo da usare per la scrittura di una persona
	private void writePersona(Persona p) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement("persona");
		xmlw.writeAttribute("id", ""+p.getId());
		aCapo();
		writeSimpleTag("nome", p.getNome());
		writeSimpleTag("cognome", p.getCognome());
		writeSimpleTag("sesso", p.getSesso().toString());
		writeSimpleTag("comune_nascita", p.getComune());
		writeSimpleTag("data_nascita", p.getNascita());
		//Se il codice fiscale è assente scrivo "ASSENTE" altrimenti il codice fiscale
		writeSimpleTag("codice_fiscale", (p.isAssente() ? "ASSENTE" : p.getCf()));
		chiudiTag();
	}

	//Metodo da usare per la scrittura dei tag semplici (=solo testo, no attributi, no sotto-tag)
	public void writeSimpleTag(String tagName, String characters) {
		try {
			tabula(nTabs);
			xmlw.writeStartElement(tagName);
			xmlw.writeCharacters(characters);
			xmlw.writeEndElement();
			aCapo();;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Metodo da usare per inserire n tabulazioni
	public void tabula(int n) {
		try {
			for(;n>0;n--)
				xmlw.writeCharacters("\t");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Metodo da usare per andare a capo
	public void aCapo() {
		try {
			xmlw.writeCharacters("\r\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
