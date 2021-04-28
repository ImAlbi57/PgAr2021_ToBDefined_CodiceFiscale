package it.unibs.arnaldo.cf;

import javax.xml.stream.XMLStreamConstants;

public class XMLReaderPersone extends GestoreXMLReader {

	public XMLReaderPersone(String path) {
		super(path);
	}
	
    public Database read() {
        Database persone= new Database();

        int nPersone=-1;
        int id = -1;
        String nome = "";
        String cognome = "";
        String sesso = null;
        String comune = "";
        String data = "";

        try {
            //Prendo il numero di persone
            while(xmlr.hasNext() && (xmlr.getEventType() != XMLStreamConstants.START_ELEMENT || !xmlr.getLocalName().equals("persone")))
                xmlr.next();
            nPersone = Integer.parseInt(xmlr.getAttributeValue(0));

            while(xmlr.hasNext()){
                //Ogni persona
                if(xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals("persona")){
                    id = Integer.parseInt(xmlr.getAttributeValue(0));
                    xmlr.next();xmlr.next();

                    //finisco di ciclare quando trovo il tag di chiusura di persona
                    while(xmlr.getEventType() != XMLStreamConstants.END_ELEMENT || !xmlr.getLocalName().equals("persona")){
                        //prendo il tipo di tag
                        String src = xmlr.getLocalName();
                        //vado al valore
                        xmlr.next();
                        switch (src) {
                            case "nome": nome = xmlr.getText(); break;
                            case "cognome": cognome = xmlr.getText(); break;
                            case "sesso": sesso = xmlr.getText().toUpperCase(); break;
                            case "comune_nascita": comune = xmlr.getText(); break;
                            case "data_nascita": data = xmlr.getText(); break;
                        }
                        //salto gli spazi inutili
                        xmlr.next();xmlr.next();xmlr.next();
                    }
                    persone.addPersona(new Persona(id, cognome, nome, sesso, data, comune));
                }
                xmlr.next();
            }
        }catch (Exception e){
            System.out.println(e);
        };

        return persone;
    }	
}
