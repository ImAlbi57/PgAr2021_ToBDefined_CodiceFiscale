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
	
	public GestoreXMLWriter(String path) {
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}
	}
	
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
			xmlw.writeEndDocument();
			xmlw.flush();
			xmlw.close();
		} catch (Exception e) {
			System.out.println("HAI ROTTO TUTTO FRATELLI'");
			System.out.println(e.getMessage());
		}
	}

	private void iniziaXML() throws XMLStreamException {
		xmlw.writeStartDocument("utf-8", "1.0");aCapo();
	}

	private void apriTag(String s) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement(s);
		aCapo();
	}
	
	private void apriTagConAttr(String s, String attr, String val) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement(s);
		xmlw.writeAttribute(attr, val);
		aCapo();
	}

	private void chiudiTag() throws XMLStreamException {
		tabula(--nTabs);
		xmlw.writeEndElement();
		aCapo();
	}

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
	
	public void tabula(int n) {
		try {
			for(;n>0;n--)
				xmlw.writeCharacters("\t");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void aCapo() {
		try {
			xmlw.writeCharacters("\r\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
