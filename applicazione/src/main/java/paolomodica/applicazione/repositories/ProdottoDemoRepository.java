package paolomodica.applicazione.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import paolomodica.applicazione.entita.ProdottoDemo;



@Repository
public interface ProdottoDemoRepository extends JpaRepository<ProdottoDemo, Long> {
	
	
	
	
	//Questa volta scriviamo le query in JPQL anche se non ce n'è bisogno, Spring Boot le imposterebbe da sè riconoscendo i nomi dei method.
	
	//Trovare istanze per singoli parametri
	
	@Query("SELECT p FROM ProdottoDemo p WHERE p.nome = :nome")
	public List<ProdottoDemo> findByNome(@Param("nome") String nome);
	
	@Query("SELECT p FROM ProdottoDemo p WHERE p.prezzo = :prezzo")
	public List<ProdottoDemo> findByPrezzo(@Param("prezzo") BigDecimal prezzo);
	
	
	//Trovare istanze tramite 2 parametri
	
	@Query("SELECT p FROM ProdottoDemo p WHERE p.nome = :nome AND p.prezzo = :prezzo")
	public List<ProdottoDemo> findByNomeAndPrezzo(@Param("nome") String nome, @Param("prezzo") BigDecimal prezzo);
	
	
	//Trovare gruppi di istanze contenuti in insiemi di valori per campi
	
	@Query("SELECT p FROM ProdottoDemo p WHERE p.prezzo BETWEEN :prezzoMinimo AND :prezzoMassimo")
	public List<ProdottoDemo> findByPrezzoBetween(@Param("prezzoMinimo") BigDecimal prezzoMinimo, @Param("prezzoMassimo")BigDecimal prezzoMassimo);
	
	@Query("SELECT p FROM ProdottoDemo p WHERE p.prezzo < :prezzo")
	public List<ProdottoDemo> findByPrezzoLessThan(@Param("prezzo") BigDecimal prezzo);
	
	@Query("SELECT p FROM ProdottoDemo p WHERE p.prezzo > :prezzo")
	public List<ProdottoDemo> findByPrezzoGreaterThan(@Param("prezzo") BigDecimal prezzo);
	
}