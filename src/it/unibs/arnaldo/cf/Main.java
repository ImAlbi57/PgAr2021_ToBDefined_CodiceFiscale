package it.unibs.arnaldo.cf;

public class Main {
	public static void main(String args[]) {
		/*ORACOLO PROTETTORE DEL CODICE, NON CANCELLARE*/
		System.out.println("Se non va mi uccido");
		/*ORACOLO PROTETTORE DEL CODICE, NON CANCELLARE*/
		
		/*
		CodiceFiscale cf = new CodiceFiscale("ALBIERI", "LUCA", Sesso.M, 2001, 07, 05, "Desenzano del garda");
		CodiceFiscale cf2 = new CodiceFiscale("GERRI", "ALICE", Sesso.F, 2001, 12, 9, "Brescia");
		CodiceFiscale cf3 = new CodiceFiscale("FRANZONI", "GABRIELE", Sesso.M, 2001, 05, 03, "Gavardo");
		System.out.println(cf);
		System.out.println(cf2);
		System.out.println(cf3);
		
		ElencoComuni.printAll();
		System.out.println(ElencoComuni.getCodice("Padenghe sul Garda"));
		*/
		
		XMLReaderPersone xmlr = new XMLReaderPersone("inputPersone.xml");
		Database persone = xmlr.read();
		persone.genAll();
		persone.readCfs();
		//persone.printAllPersone();
		persone.printAllCFs();
		
	}
}
