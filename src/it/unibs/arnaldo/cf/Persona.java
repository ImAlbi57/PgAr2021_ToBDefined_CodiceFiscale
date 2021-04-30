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
	private boolean assente;
	
	public Persona(int id, String cognome, String nome, String sesso, String nascita, String comune) {
		this.id = id;
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = Sesso.valueOf(sesso);
		this.anno = Integer.parseInt(nascita.substring(0,4));
		this.mese = Integer.parseInt(nascita.substring(5,7));
		this.giorno = Integer.parseInt(nascita.substring(8,10));
		this.comune = comune;
		this.cf = null;
		this.assente = true;
	}
	
	public void setAssente(boolean tf) {
		this.assente = tf;
	}
	public boolean isAssente() {
		return this.assente;
	}
	
	public void genCodiceFiscale() {
		this.cf = new CodiceFiscale(cognome, nome, sesso, anno, mese, giorno, comune);
	}
	
	public String getCf() {
		return cf.toString();
	}
	
	public String toString() {
		return cognome + " " + nome + (cf != null ? " " + cf : "");
	}
	
}
