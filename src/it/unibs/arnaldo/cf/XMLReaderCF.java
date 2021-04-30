package it.unibs.arnaldo.cf;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamConstants;


/**
 * Classe che andrà a "leggere" in codici fiscali ereditando i metodi della classe astratta (GestoreXMLReader)
 * @author toBdefined
 *
 */
public class XMLReaderCF extends GestoreXMLReader {

	public XMLReaderCF(String path) {
		super(path);
	}
	
	
	/**
	 * Lettura dei codiciFiscali.xml e andiamo ad aggiungere i vari codici fiscali all'interno dell'ArrayList cfs
	 * In caso di eccezione, quest'ultima verrà stampata
	 * @return l'ArrayList di codici fiscali
	 */	
	public ArrayList<String> read() {
		ArrayList<String> cfs = new ArrayList<String>();
		String cf = "";
		
	    try {
	    	while (xmlr.hasNext()) {
	    		switch (xmlr.getEventType()) {
	    		case XMLStreamConstants.START_DOCUMENT: break;
	    		case XMLStreamConstants.START_ELEMENT:
	    			String src = xmlr.getLocalName();
		    		xmlr.next();
		    		switch (src) {
                    	case "codice": cf = xmlr.getText(); break;
		    		}
		    		break;
	    		case XMLStreamConstants.END_ELEMENT: 
	    			if(!cf.equals("")) {
		        		cfs.add(cf);
		        		cf = "";
		        	}
	    			break;
	    		case XMLStreamConstants.COMMENT: break;
	    		case XMLStreamConstants.CHARACTERS: break;
	    		}
	    		xmlr.next();
	    	}
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    return cfs;
	}
}
