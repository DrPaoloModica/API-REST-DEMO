package paolomodica.applicazione.entita;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



//Creiamo una entità ponte per la relazione many to many tra utenti e prodotti

@Entity
public class OrdineDemo {
	
	
	
	
	//Attributi/Campi/Colonne
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Indichiamo a Spring che questa entità è in relazione many to one sia con UtenteDemo che con ProdottoDemo e gli indichiamo di recuperare (fetch) sempre tutti i dati degli utenti e prodotti correlati
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "utente_id", referencedColumnName = "id", nullable = false)
	private UtenteDemo utente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prodotto_id", referencedColumnName = "id", nullable = false)
	private ProdottoDemo prodotto;
	
	private int quantita;
	private LocalDate dataOrdine;

	
	
	
	//Costruttore
	
	public OrdineDemo() {
	}

	
	
	
	//Getter methods
	
	public long getId() {
		return id;
	}
	
	public UtenteDemo getUtente() {
		return utente;
	}
	
	public ProdottoDemo getProdotto() {
		return prodotto;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	
	
	
	//Setter methods
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setUtente(UtenteDemo utente) {
		this.utente = utente;
	}
	
	public void setProdotto(ProdottoDemo prodotto) {
		this.prodotto = prodotto;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
}
