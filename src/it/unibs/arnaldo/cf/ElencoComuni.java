package it.unibs.arnaldo.cf;

import java.util.HashMap;

public class ElencoComuni {
	private static HashMap<String, String> comuni = init();
	
	private static HashMap<String, String> init() {
		XMLReaderComuni xmlr = new XMLReaderComuni("comuni.xml");
		try {
			return xmlr.read();
		} catch (Exception e) {
			System.out.println(e);
		}
		return new HashMap<String, String>();
	}

	public static String getCodice(String nome) {
		return comuni.get(nome.toUpperCase());
	}
	
	public static void printAll() {
		for (String comune: comuni.keySet()) {
		    String key = comune.toString();
		    String value = comuni.get(comune).toString();
		    System.out.println(key + " " + value);
		}
	}

}
