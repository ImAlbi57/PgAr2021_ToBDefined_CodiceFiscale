package it.unibs.arnaldo.cf;

import it.unibs.arnaldo.cf.Persona.Sesso;

public class Main {
	public static void main(String args[]) {
		/*ORACOLO PROTETTORE DEL CODICE, NON CANCELLARE*/
		System.out.println("Se non va mi uccido");
		/*ORACOLO PROTETTORE DEL CODICE, NON CANCELLARE*/
		
		CodiceFiscale cf = new CodiceFiscale("ALBIERI", "LUCA", Sesso.M, 2001, 07, 05, "Padenghe sul Garda");
		CodiceFiscale cf2 = new CodiceFiscale("GERRI", "ALICE", Sesso.F, 2001, 12, 9, "Brescia");
		System.out.println(cf);
		System.out.println(cf2);
	}
}
