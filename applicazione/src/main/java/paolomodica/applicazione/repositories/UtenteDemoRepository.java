package paolomodica.applicazione.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paolomodica.applicazione.entita.UtenteDemo;
import paolomodica.applicazione.entita.common.Genere;



@Repository
public interface UtenteDemoRepository extends JpaRepository<UtenteDemo, Long> {
	
	
	
	
	//Trovare istanze per singoli parametri
	
	public List<UtenteDemo> findByNome(String nome);
	
	public List<UtenteDemo> findByGenere(Genere genere);
	
	public List<UtenteDemo> findByEta(int eta);
	
	public List<UtenteDemo> findByDataDiNascita(LocalDate dataDiNascita);
	
	public List<UtenteDemo> findByProfessione(String professione);
	
	
	//Trovare istanze tramite 2 parametri
	
	public List<UtenteDemo> findByEtaAndGenere(int eta, Genere genere);
	
	public List<UtenteDemo> findByGenereAndProfessione(Genere genere, String professione);
	
	
	//Trovare gruppi di istanze contenuti in insiemi di valori per campi
	
	public List<UtenteDemo> findByEtaBetween(int etaMinima, int etaMassima);
	
	public List<UtenteDemo> findByEtaLessThan(int eta);
	
	public List<UtenteDemo> findByEtaGreaterThan(int eta);
	
}
