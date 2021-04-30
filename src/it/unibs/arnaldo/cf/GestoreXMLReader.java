package it.unibs.arnaldo.cf;

import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe astratta per la lettura
 * @author toBdefined
 *
 */
public abstract class GestoreXMLReader {
	
	//dichiarazione delle variabili
	protected XMLInputFactory xmlif = null;
	protected XMLStreamReader xmlr = null;
	protected String path;

	
	/**
	 * Istanziamento dello StreamReader con gestione delle eccezioni
	 * @param path percorso del file
	 */	
    public GestoreXMLReader(String path){
        this.path = path;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
    }

    
    /**
     * Getter di Xmlr
     */
	public XMLStreamReader getXmlr() {
		return xmlr;
	}
}
