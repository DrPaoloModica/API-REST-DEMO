package paolomodica.applicazione.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import paolomodica.applicazione.entita.OrdineDemo;
import paolomodica.applicazione.entita.ProdottoDemo;
import paolomodica.applicazione.entita.UtenteDemo;




@Repository
public interface OrdineDemoRepository extends JpaRepository<OrdineDemo, Long> {

	
	
	
	//Questa volta ci atteniamo più sull'immediatemente utile con i query method
	
	
	// Trova tutti gli ordini di un certo utente
	
   public List<OrdineDemo> findByUtenteId(long utenteId);

    // Trova tutti gli ordini per un certo prodotto
   
   public List<OrdineDemo> findByProdottoId(long prodottoId);
   
   
   
   
   //Data la natura ponte della nostra entità relazione, in questo caso è necessario definire dei query method con JPQL o SQL per trovare gli oggetti in relazione fra loro 
   
   
   // Tutti i prodotti comprati da un certo utente
   
	@Query("SELECT o.prodotto FROM OrdineDemo o WHERE o.utente.id = :utenteId") //JPQL
	public List<ProdottoDemo> trovaProdottiPerUtente(@Param("utenteId") long utenteId);
	
	// Tutti gli utenti che hanno acquistato un certo prodotto
	
	@Query(value = "SELECT u.* FROM utente_demo u JOIN ordine_demo o ON u.id = o.utente_id WHERE o.prodotto_id = :prodottoId", nativeQuery = true) //SQL
	public List<UtenteDemo> trovaUtentiPerProdotto(@Param("prodottoId") long prodottoId);

}
