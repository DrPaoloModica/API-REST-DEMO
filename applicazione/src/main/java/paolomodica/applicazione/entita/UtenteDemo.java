package paolomodica.applicazione.entita;

import java.time.LocalDate;

import jakarta.persistence.Id;
import paolomodica.applicazione.entita.common.Genere;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;





@Entity
public class UtenteDemo {
	
	
	
	
	//Attributi/Campi/Colonne
	
	//Impostiamo la primary key ID con l'annotazione specifica e impostiamo la generazione automatica
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Genere genere;
	
	private int eta;
	private LocalDate dataDiNascita;
	private String professione;
	
	
	
	
	//Costruttore
	
	public UtenteDemo() {
	}
	
	
	
	
	//Getter methods
	
	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Genere getGenere() {
		return genere;
	}
	
	public int getEta() {
		return eta;
	}
	
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	
	public String getProfessione() {
		return professione;
	}
	
	
	
	
	//Setter methods
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public void setEta(int eta) {
		this.eta = eta;
	}
	
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public void setProfessione(String professione) {
		this.professione = professione;
	}
	
	
	

}