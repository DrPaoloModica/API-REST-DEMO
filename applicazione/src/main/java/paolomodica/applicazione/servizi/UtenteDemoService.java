package paolomodica.applicazione.servizi;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import paolomodica.applicazione.entita.UtenteDemo;
import paolomodica.applicazione.entita.common.Genere;
import paolomodica.applicazione.repositories.UtenteDemoRepository;




@Service
public class UtenteDemoService {
	
	
	
	
	//Inserire interfaccia repository nella classe servizio
	

	private UtenteDemoRepository utenteDemoRepository;
	
	@Autowired	
	public UtenteDemoService(UtenteDemoRepository utenteDemoRepository) {
		this.utenteDemoRepository = utenteDemoRepository;
	}
	
	
	
	
	//Chiamiamo i query methods del repository tramite i method del servizio
	
	public UtenteDemo salva(UtenteDemo utenteDemo) {
		return utenteDemoRepository.save(utenteDemo);
	}
	
	public List<UtenteDemo> salvaTutto(List<UtenteDemo> utenteDemo) {
		return utenteDemoRepository.saveAll(utenteDemo);
	}
	
	public void cancella(UtenteDemo utenteDemo) {
		utenteDemoRepository.delete(utenteDemo);
	}
	
	public void cancellaTutto() {
		utenteDemoRepository.deleteAll();
	}	
	
	public List<UtenteDemo> trovaTutto() {
		return utenteDemoRepository.findAll();
	}
	
	//Trovare istanze per singoli parametri
	
	public Optional<UtenteDemo> trovaPerId(long id) {
		return utenteDemoRepository.findById(id);
	}	
	
	public List<UtenteDemo> trovaPerNome(String nome){
		return utenteDemoRepository.findByNome(nome);
	}
	
	public List<UtenteDemo> trovaPerGenere(Genere genere){
		return utenteDemoRepository.findByGenere(genere);
	}
	
	public List<UtenteDemo> trovaPerEta(int eta){
		return utenteDemoRepository.findByEta(eta);
	}
	
	public List<UtenteDemo> trovaPerDataDiNascita(LocalDate dataDiNascita){
		return utenteDemoRepository.findByDataDiNascita(dataDiNascita);
	}
	
	public List<UtenteDemo>	trovaPerProfessione(String professione) {
		return utenteDemoRepository.findByProfessione(professione);
	}
	
	//Trovare istanze tramite 2 parametri
	
	public List<UtenteDemo> trovaPerEtaEGenere(int eta, Genere genere) {
		return utenteDemoRepository.findByEtaAndGenere(eta, genere);
	}
	
	public List<UtenteDemo> trovaPerGenereEProfessione(Genere genere, String professione) {
		return utenteDemoRepository.findByGenereAndProfessione(genere, professione);
	}
	
	//Trovare gruppi di istanze contenuti in insiemi di valori per campi
	
	public List<UtenteDemo> trovaPerIntervalloEta(int etaMinima, int etaMassima) {
		return utenteDemoRepository.findByEtaBetween(etaMinima, etaMassima);
	}
	
	public List<UtenteDemo> trovaPerEtaMinoreDi(int eta) {
		return utenteDemoRepository.findByEtaLessThan(eta);
	}
	
	public List<UtenteDemo> trovaPerEtaMaggioreDi(int eta) {
		return utenteDemoRepository.findByEtaGreaterThan(eta);
	}

	

}