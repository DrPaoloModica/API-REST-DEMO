package paolomodica.applicazione.servizi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import paolomodica.applicazione.entita.ProdottoDemo;
import paolomodica.applicazione.repositories.ProdottoDemoRepository;




@Service
public class ProdottoDemoService {

	
	
	
	//Inserire interfaccia repository nella classe servizio
	

	private ProdottoDemoRepository prodottoDemoRepository;
	
	@Autowired
	public ProdottoDemoService(ProdottoDemoRepository prodottoDemoRepository) {
		this.prodottoDemoRepository = prodottoDemoRepository;		
	}

	
	
	
	//Chiamiamo i query methods del repository tramite i method del servizio
	
	public ProdottoDemo salva(ProdottoDemo prodottoDemo) {
		return prodottoDemoRepository.save(prodottoDemo);
	}
	
	public List<ProdottoDemo> salvaTutto(List<ProdottoDemo> prodottoDemo) {
		return prodottoDemoRepository.saveAll(prodottoDemo);
	}
	
	public void cancella(ProdottoDemo prodottoDemo) {
		prodottoDemoRepository.delete(prodottoDemo);
	}
	
	public void cancellaTutto() {
		prodottoDemoRepository.deleteAll();
	}	
	
	public List<ProdottoDemo> trovaTutto() {
		return prodottoDemoRepository.findAll();
	}
	
	//Trovare istanze per singoli parametri
	
	public Optional<ProdottoDemo> trovaPerId(long id) {
		return prodottoDemoRepository.findById(id);
	}
	
	public List<ProdottoDemo> trovaPerNome(String nome){
		return prodottoDemoRepository.findByNome(nome);
	}
	
	public List<ProdottoDemo> trovaPerPrezzo(BigDecimal prezzo){
		return prodottoDemoRepository.findByPrezzo(prezzo);
	}
		
	//Trovare istanze tramite 2 parametri
	
	public List<ProdottoDemo> trovaPerNomeEPrezzo(String nome, BigDecimal prezzo) {
		return prodottoDemoRepository.findByNomeAndPrezzo(nome, prezzo);
	}
	
	//Trovare gruppi di istanze contenuti in insiemi di valori per campi
	
	public List<ProdottoDemo> trovaPerIntervalloPrezzo(BigDecimal prezzoMinimo, BigDecimal prezzoMassimo) {
		return prodottoDemoRepository.findByPrezzoBetween(prezzoMinimo,prezzoMassimo);
	}
	
	public List<ProdottoDemo> trovaPerPrezzoMinoreDi(BigDecimal prezzo) {
		return prodottoDemoRepository.findByPrezzoLessThan(prezzo);
	}
	
	public List<ProdottoDemo> trovaPerPrezzoMaggioreDi(BigDecimal prezzo) {
		return prodottoDemoRepository.findByPrezzoGreaterThan(prezzo);
	}
	
}
