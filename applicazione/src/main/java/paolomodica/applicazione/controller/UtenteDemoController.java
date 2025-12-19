package paolomodica.applicazione.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paolomodica.applicazione.servizi.UtenteDemoService;
import paolomodica.applicazione.entita.UtenteDemo;
import paolomodica.applicazione.entita.common.Genere;

@RestController
@RequestMapping("/utenti")
public class UtenteDemoController {
	
	
	
	
	private UtenteDemoService utenteDemoService;
	
	
	
	
	@Autowired
	public UtenteDemoController(UtenteDemoService utenteDemoService) {
		this.utenteDemoService = utenteDemoService;
	}
	
	
	//GET method per trovare tutto
	
	@GetMapping
	public ResponseEntity<List<UtenteDemo>> trovaTutto(){
		
		List<UtenteDemo> utente = utenteDemoService.trovaTutto();
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(utente);
		}
	}
	
	//GET methods per singoli parametri
		
	@GetMapping(value= "/{id}")
	public ResponseEntity<UtenteDemo> trovaPerId(@PathVariable long id) {
		
		return	utenteDemoService.trovaPerId(id)
			.map(u -> ResponseEntity.ok(u))
			.orElse(ResponseEntity.notFound()
					.build());

	}
	
	@GetMapping(value= "/genere")
	public ResponseEntity<List<UtenteDemo>> trovaPerNome(@RequestParam Genere genere) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerGenere(genere);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}

	@GetMapping(value= "/eta")
	public ResponseEntity<List<UtenteDemo>> trovaPerEta(@RequestParam int eta) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerEta(eta);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	@GetMapping(value= "/data-di-nascita")
	public ResponseEntity<List<UtenteDemo>> trovaPerDataDiNascita(@RequestParam LocalDate dataDiNascita) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerDataDiNascita(dataDiNascita);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	//GET methods per multipli parametri
	
	@GetMapping(value= "/eta-genere")
	public ResponseEntity<List<UtenteDemo>> trovaPerEtaEGenere(@RequestParam int eta, @RequestParam Genere genere) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerEtaEGenere(eta, genere);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	@GetMapping(value= "/genere-professione")
	public ResponseEntity<List<UtenteDemo>> trovaPerGenereEProfessione(@RequestParam Genere genere, @RequestParam String professione) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerGenereEProfessione(genere, professione);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	//GET methods per intervalli di valori
	
	@GetMapping(value= "/eta-intervallo")
	public ResponseEntity<List<UtenteDemo>> trovaPerIntervalloEta(@RequestParam int etaMinima,@RequestParam int etaMassima) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerIntervalloEta(etaMinima, etaMassima);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	@GetMapping(value= "/eta-minore")
	public ResponseEntity<List<UtenteDemo>> trovaPerEtaMinoreDi(@RequestParam int eta) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerEtaMinoreDi(eta);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	@GetMapping(value= "/eta-maggiore")
	public ResponseEntity<List<UtenteDemo>> trovaPerEtaMaggioreDi(@RequestParam int eta) {
		
		List<UtenteDemo> utente = utenteDemoService.trovaPerEtaMaggioreDi(eta);
		
		if(utente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(utente);
		}
		
	}
	
	
	
	
	//POST method
	
	
	//Creare uno o più istanze nella tabella
	
	@PostMapping
	public ResponseEntity<List<UtenteDemo>> creaNuoviUtenti(@RequestBody List<UtenteDemo> utenteDemo){
		
		List<UtenteDemo> utenteSalvato = utenteDemoService.salvaTutto(utenteDemo);
		return ResponseEntity.ok(utenteSalvato);
		
	}
	
	
	
	
	//PUT method
	
	
	@PutMapping(value= "/{id}")
	public ResponseEntity<UtenteDemo> aggiornaUtente(@PathVariable long id, @RequestBody UtenteDemo utenteAggiornato) {
		
		Optional<UtenteDemo> utenteEsistente = utenteDemoService.trovaPerId(id);
		
		if(utenteEsistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {			
		
			UtenteDemo utenteDaAggiornare = utenteEsistente.get();
			
			utenteDaAggiornare.setNome(utenteAggiornato.getNome());
			utenteDaAggiornare.setGenere(utenteAggiornato.getGenere());
			utenteDaAggiornare.setEta(utenteAggiornato.getEta());
			utenteDaAggiornare.setDataDiNascita(utenteAggiornato.getDataDiNascita());
			utenteDaAggiornare.setProfessione(utenteAggiornato.getProfessione());
		    
			UtenteDemo utente = utenteDemoService.salva(utenteDaAggiornare);
		    
		    return ResponseEntity.ok(utente);
		}
	}
	
	
	
	//DELETE methods
	
	
	//Cancellare tutti i record dalla tabella
	
	@DeleteMapping
	public ResponseEntity<Void> cancellaTutto() {
		
		utenteDemoService.cancellaTutto();
		return ResponseEntity.noContent().build();
				
	}
	/*Promemoria per me in caso dovessi usarlo per sbaglio:
	 * INSERT INTO utente_demo (nome, genere, eta, data_di_nascita, professione)
	 * VALUES
	 * ('Mario Mario', 'MASCHIO', 40, '1985-09-13', 'Idraulico'),
	 * ('Luigi Mario', 'MASCHIO', 40, '1985-09-13', 'Idraulico'),
	 * ('Peach Toadstool', 'FEMMINA', 40, '1985-09-13', 'Principessa'),
	 * ('Warrior of Light', 'MASCHIO', 38, '1987-12-18', 'Guerriero');
	 */
	
	//Cancellare una singola istanza, selezionandola per ID
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Void> cancellaPerId(@PathVariable long id) {
		
		Optional<UtenteDemo> utenteCancellato = utenteDemoService.trovaPerId(id);
		
		if(utenteCancellato.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			utenteDemoService.cancella(utenteCancellato.get()); //Non abbiamo mai creato un vero e proprio deleteById nel repository, ma non è necessario
			return ResponseEntity.noContent().build();
		}
			
	}
	
}
