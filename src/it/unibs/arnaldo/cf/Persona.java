package it.unibs.arnaldo.cf;

public class Persona {
	public enum Sesso {M, F};
	
	private int id;
	private String cognome;
	private String nome;
	private Sesso sesso;
	private int anno;
	private int mese;
	private int giorno;
	private String comune;
	private CodiceFiscale cf;
	
	public Persona(int id, String cognome, String nome, String sesso, String nascita, String comune) {
		this.id = id;
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = Sesso.valueOf(sesso);
		this.anno = Integer.parseInt(nascita.substring(0,4));
		this.mese = Integer.parseInt(nascita.substring(5,7));
		this.giorno = Integer.parseInt(nascita.substring(1,0));
		this.comune = comune;
	}
	
	public void genCodiceFiscale() {
		this.cf = new CodiceFiscale(cognome, nome, sesso, anno, mese, giorno, comune);
	}
	
}
