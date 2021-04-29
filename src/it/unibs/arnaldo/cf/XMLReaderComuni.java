package it.unibs.arnaldo.cf;

import java.util.HashMap;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

public class XMLReaderComuni extends GestoreXMLReader {

	public XMLReaderComuni(String path) {
		super(path);
	}
	
	public HashMap<String,String> read() throws XMLStreamException {
		HashMap<String,String> comuni = new HashMap<String, String>();
		String nome = "";
		String codice = "";
		
	    try {
	    	while (xmlr.hasNext()) {
	    		switch (xmlr.getEventType()) {
	    		case XMLStreamConstants.START_DOCUMENT: break;
	    		case XMLStreamConstants.START_ELEMENT:
	    			String src = xmlr.getLocalName();
		    		xmlr.next();
		    		switch (src) {
                    	case "nome": nome = xmlr.getText(); break;
                    	case "codice": codice = xmlr.getText(); break;
		    		}

	    		case XMLStreamConstants.END_ELEMENT: 
	    			if(!nome.equals("") && !codice.equals("")) {
		        		comuni.put(nome, codice);
		        		nome = "";
		        		codice = "";
		        	}
	    		case XMLStreamConstants.COMMENT: break;
	    		case XMLStreamConstants.CHARACTERS: break;
	    		}
	    		xmlr.next();
	    	}
	    	
	    	
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    return comuni;
	}	
}
