package paolomodica.applicazione.entita;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




/*Qui la tabella la creiamo da dentro MySQL e la gestiamo con Spring, invece che farla creare direttamente a spring
 * 
 * CREATE TABLE prodotto_demo (
 *      id BIGINT AUTO_INCREMENT PRIMARY KEY,
 *      nome VARCHAR(100) NOT NULL,
 *      prezzo DECIMAL(10,2) NOT NULL
 *    	);
 *    
 *    INSERT INTO prodotti_demo (nome, prezzo) 
 *    VALUES 
 *    ('Pizza', 8.50), 
 *    ('Macchina', 14900.50), 
 *    ('Cappotto', 119.90), 
 *    ('Vittoria alle Elezioni', 8900000.75);
 */




@Entity
@Table(name = "prodotto_demo")
public class ProdottoDemo {
	
	
	
	
	//Attributi/Campi/Colonne
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;
	
	@Column(name = "nome")
    private String nome;
	
	@Column(name = "prezzo")
    private BigDecimal prezzo;
	
	
	
	
	//Costruttore
	
	public ProdottoDemo() {
	}
	
	
	
	
	//Getter methods


	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public BigDecimal getPrezzo() {
		return prezzo;
	}

	
	
	
	//Setter methods
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	
}
