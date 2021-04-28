package it.unibs.arnaldo.cf;

import javax.xml.stream.XMLStreamConstants;

public class XMLReaderPersone extends GestoreXMLReader {

	public XMLReaderPersone(String path) {
		super(path);
	}
	
    public Database myReader() {
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
            while(getXmlr().hasNext() && (getXmlr().getEventType() != XMLStreamConstants.START_ELEMENT || !getXmlr().getLocalName().equals("persone")))
                getXmlr().next();
            nPersone = Integer.parseInt(getXmlr().getAttributeValue(0));

            while(getXmlr().hasNext()){
                //Ogni persona
                if(getXmlr().getEventType() == XMLStreamConstants.START_ELEMENT && getXmlr().getLocalName().equals("persona")){
                    id = Integer.parseInt(getXmlr().getAttributeValue(0));
                    getXmlr().next();getXmlr().next();

                    //finisco di ciclare quando trovo il tag di chiusura di persona
                    while(getXmlr().getEventType() != XMLStreamConstants.END_ELEMENT || !getXmlr().getLocalName().equals("persona")){
                        //prendo il tipo di tag
                        String src = getXmlr().getLocalName();
                        //vado al valore
                        getXmlr().next();
                        switch (src) {
                            case "nome": nome = getXmlr().getText(); break;
                            case "cognome": cognome = getXmlr().getText(); break;
                            case "sesso": sesso = getXmlr().getText().toUpperCase(); break;
                            case "comune_nascita": comune = getXmlr().getText(); break;
                            case "data_nascita": data = getXmlr().getText(); break;
                        }
                        //salto gli spazi inutili
                        getXmlr().next();getXmlr().next();getXmlr().next();
                    }
                    persone.addPersona(new Persona(id, cognome, nome, sesso, data, comune));
                }
                getXmlr().next();
            }
        }catch (Exception e){
            System.out.println(e);
        };

        return persone;
    }	
}
