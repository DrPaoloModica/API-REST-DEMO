package paolomodica.applicazione.servizi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paolomodica.applicazione.entita.OrdineDemo;
import paolomodica.applicazione.entita.ProdottoDemo;
import paolomodica.applicazione.entita.UtenteDemo;
import paolomodica.applicazione.repositories.OrdineDemoRepository;

@Service
public class OrdineDemoService {

	
	
	
	//Inserire interfaccia repository nella classe servizio
	

	private OrdineDemoRepository ordineDemoRepository;
	
	
	@Autowired
	public OrdineDemoService(OrdineDemoRepository ordineDemoRepository) {
		this.ordineDemoRepository = ordineDemoRepository;
	}

	
	
	
	//Chiamiamo i query methods del repository tramite i method del servizio
	
    public OrdineDemo salva(OrdineDemo ordineDemo) {
        return ordineDemoRepository.save(ordineDemo);
    }
	
    public List<OrdineDemo> salvaTutto(List<OrdineDemo> ordineDemo) {
        return ordineDemoRepository.saveAll(ordineDemo);
    }
	
	public void cancella(OrdineDemo ordineDemo) {
		ordineDemoRepository.delete(ordineDemo);
	}
	
	public void cancellaTutto() {
		ordineDemoRepository.deleteAll();
	}	
	
	public List<OrdineDemo> trovaTutto() {
		return ordineDemoRepository.findAll();
	}

	//Trovare istanze per singoli parametri. In questo caso, l'id dell'ordine e quello degli oggetti utente e prodotto passati alle singole istanze
	
    public Optional<OrdineDemo> trovaPerId(long id) {
        return ordineDemoRepository.findById(id);
    }

    public List<OrdineDemo> trovaPerUtenteId(long utenteId) {
        return ordineDemoRepository.findByUtenteId(utenteId);
    }

    public List<OrdineDemo> trovaPerProdottoId(long prodottoId) {
        return ordineDemoRepository.findByProdottoId(prodottoId);
    }
    
    //Adesso troviamo tutti i prodotti acquistati da un utente e tutti gli utenti che hanno acquistato un prodotto usando OrdineDemo come ponte
    
    public List<ProdottoDemo> trovaProdottiPerUtente(long utenteId) {
        return ordineDemoRepository.trovaProdottiPerUtente(utenteId);
    }

    public List<UtenteDemo> trovaUtentiPerProdotto(long prodottoId) {
        return ordineDemoRepository.trovaUtentiPerProdotto(prodottoId);
    }
    
}
