package it.unibs.arnaldo.cf;

import java.util.HashMap;


/**
 * Classe che visualizza tutti i comuni 
 * @author toBdefined
 *
 */
public class ElencoComuni {
	private static HashMap<String, String> comuni = init();
	
	
	/**
	 * Istanziamento dell'hashmap	 
	 * @return nuova hashmap
	 */		
	private static HashMap<String, String> init() {
		XMLReaderComuni xmlr = new XMLReaderComuni("comuni.xml");
		try {
			return xmlr.read();
		} catch (Exception e) {
			System.out.println(e);
		}
		return new HashMap<String, String>();
	}

	
	/**
	 * Prendo i vari codici dei comuni	 
	 * @param nome
	 * @return il codice del comune tutto in maiuscolo
	 */	
	public static String getCodice(String nome) {
		return comuni.get(nome.toUpperCase());
	}
	
	
	/**
	 * Verifica della validità del codice del comune cercandone la corrispondenza nell'hashmap (presa dal file Comuni.xml)
	 * @param codice
	 * @return codice mappato nella hashmap
	 */	
	public static boolean isValid(String codice) {
		return comuni.containsValue(codice);
	}
	
	
	/**
	 * Stampa di ogni codice di ogni comune presente 
	 * 
	 */	
	public static void printAll() {
		for (String comune: comuni.keySet()) {
		    String key = comune.toString();
		    String value = comuni.get(comune).toString();
		    System.out.println(key + " " + value);
		}
	}

}
