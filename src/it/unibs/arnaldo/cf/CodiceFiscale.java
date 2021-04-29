package it.unibs.arnaldo.cf;

import it.unibs.arnaldo.cf.Persona.Sesso;

public class CodiceFiscale {
	private String cf;
	
	/**
	* Metodo costruttore della classe CodiceFiscale,
	* prende come parametri i dati della persona e genera il codice fiscale
	*
	* @param  cognome  Rossi
	* @param  nome  Mario
	* @param  sesso  Sesso.M
	* @param  anno  1990
	* @param  mese  5
	* @param  giorno  21
	* @param  comune  Brescia
	*/
	public CodiceFiscale(String cognome, String nome, Sesso sesso, int anno, int mese, int giorno, String comune) {
		
		/*PROCEDURA PER GENERARE IL CF DATE LE INFO DELLA PERSONA*/
		
		String parts[] = new String[8];
		
		//Genero le parti di nome e cognome
		parts[0] = generate3Chars(cognome);
		parts[1] = generate3Chars(nome);
		
		
		//Nascita, genero le varie parti
		//ANNO (scarto le prime 2 cifre) 
		parts[2] = String.format("%2s", Integer.toString(anno).substring(2));
		//MESE 
		parts[3] = "" + getMonth(mese);
		//GIORNO
		//Se è donna, aggiungo 40
		if(sesso == Sesso.F)
			giorno += 40;
		parts[4] = String.format("%02d", giorno);
		
		
		//Comune, necessaria la lettura da tastiera
		parts[5] = ElencoComuni.getCodice(comune);
		
		//Codice di controllo
		parts[6] = "" + getControllo(new String(parts[0] + parts[1] + parts[2] + parts[3] + parts[4] + parts[5]));
		
		//CodiceFiscale, unisco tutte le parti per avere il codice intero
		cf = new String(parts[0] + parts[1] + parts[2] + parts[3] + parts[4] + parts[5] + parts[6]);
	}
	
	
	/*METODI DI UTILITA'*/
	//Ritorna true se il carattere passato è una vocale
	public boolean isVowel(char c) {
		String vowels = "AEIOU";
		return vowels.indexOf(c) != -1;
	}
	/*//Ritorna true se il carattere passato è un numero [SOSTITUITO DA Character.isDigit(char c)]
	public boolean isNumber(char c) {
		String vowels = "0123456789";
		return vowels.indexOf(c) != -1;
	}*/	
	private char getMonth(int mese) {
		char mesi[] = {'A','B','C','D','E','H','L','M','P','R','S','T'};
		return mesi[mese-1];
	}
	
	
	/*METODI PER CREARE E CONTROLLARE IL CF*/
	
	//Metodo per generare nome e cognome
	private String generate3Chars(String part) {
		char src[] = part.toCharArray();
		String chars = "";
		
		//Prima aggiungo le consonanti
		for(int i=0; i<src.length && chars.length() < 3; i++)
			if(!isVowel(src[i]))
				chars += src[i];
		
		//Se le consonanti non sono abbastanza aggiungo le vocali
		if(chars.length() < 3)
			for(int i=0; i<src.length && chars.length() < 3; i++)
				if(isVowel(src[i]))
					chars += src[i];
		
		//Se le lettere non bastano aggiungo X
		for(int i=0; i<src.length && chars.length() < 3; i++)
			chars += 'X';
		
		return chars;
	}
	
	private char getControllo(String partialCF) {
		//Caratteri di controllo per le posizioni dispari (vedi tabella), le pari corrispondono al numero/lettera
		int dispari[] = {1,0,5,7,9,13,15,17,19,21,1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23};
		int sumDispari = 0;
		int sumPari = 0;
		
		char chars[] = partialCF.toCharArray();
		
		for(int i=0; i<chars.length; i++) {
			//Caso pari
			if((i+1)%2 == 0) {
				//Il valore da aggiungere corrisponde al valore del numero/lettera
				sumPari += (Character.isDigit(chars[i])) ? Character.getNumericValue(chars[i]) : (Character.toUpperCase(chars[i]) - 'A');
			}
			//Caso dispari
			else {
				//il valore da aggiungere corrisponde al valore nella x-esima posizione di dispari[] (da tabella)
				//la posizione x corrisponde al numero stesso, oppure al "valore" della cifra + 10 [0,...,9,A,...,Z]
				int pos = (Character.isDigit(chars[i])) ? Character.getNumericValue(chars[i]) : 10 + (Character.toUpperCase(chars[i]) - 'A');
				sumDispari += dispari[pos];
			}
		}
		int ris = (sumPari+sumDispari)%26;
		
		return (char) ('A' + ris);
	}
	
	//Metodo toString
	public String toString() {
		return cf;
	}
	
}
