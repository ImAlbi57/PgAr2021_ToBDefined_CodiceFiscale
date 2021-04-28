package it.unibs.arnaldo.cf;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public abstract class GestoreXMLReader {
	private XMLInputFactory xmlif = null;
    private XMLStreamReader xmlr = null;
    private String path;

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

	public XMLStreamReader getXmlr() {
		return xmlr;
	}
}
