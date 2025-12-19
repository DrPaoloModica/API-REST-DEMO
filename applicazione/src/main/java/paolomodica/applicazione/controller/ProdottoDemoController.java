package paolomodica.applicazione.controller;

import java.math.BigDecimal;
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

import paolomodica.applicazione.servizi.ProdottoDemoService;
import paolomodica.applicazione.entita.ProdottoDemo;
import paolomodica.applicazione.entita.UtenteDemo;
import paolomodica.applicazione.entita.common.Genere;

@RestController
@RequestMapping("/prodotti")
public class ProdottoDemoController {

	
	
	
	private ProdottoDemoService prodottoDemoService;
	
	
	
	
	@Autowired
	public ProdottoDemoController(ProdottoDemoService prodottoDemoService) {
		this.prodottoDemoService = prodottoDemoService;
	}
	

	
	//GET method per trovare tutto
	
	@GetMapping
	public ResponseEntity<List<ProdottoDemo>> trovaTutto(){
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaTutto();
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(prodotto);
		}
	}

	//GET methods per singoli parametri
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<ProdottoDemo> trovaPerId(@PathVariable long id) {
		
		return	prodottoDemoService.trovaPerId(id)
			.map(u -> ResponseEntity.ok(u))
			.orElse(ResponseEntity.notFound()
					.build());

	}
		
	@GetMapping(value= "/nome")
	public ResponseEntity<List<ProdottoDemo>> trovaPerNome(@RequestParam String nome) {
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaPerNome(nome);
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(prodotto);
		}
		
	}

	@GetMapping(value= "/prezzo")
	public ResponseEntity<List<ProdottoDemo>> trovaPerPrezzo(@RequestParam BigDecimal prezzo) {
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaPerPrezzo(prezzo);
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(prodotto);
		}
		
	}

	//GET methods per multipli parametri
	
	@GetMapping(value= "/nome-prezzo")
	public ResponseEntity<List<ProdottoDemo>> trovaPerNomeEPrezzo(@RequestParam String nome, @RequestParam BigDecimal prezzo) {
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaPerNomeEPrezzo(nome, prezzo);
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(prodotto);
		}
		
	}

	//GET methods per intervalli di valori
	
	@GetMapping(value= "/prezzo-intervallo")
	public ResponseEntity<List<ProdottoDemo>> trovaPerIntervalloPrezzo(@RequestParam BigDecimal prezzoMinimo,@RequestParam BigDecimal prezzoMassimo) {
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaPerIntervalloPrezzo(prezzoMinimo, prezzoMassimo);
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(prodotto);
		}
		
	}
	
	@GetMapping(value= "/prezzo-minore")
	public ResponseEntity<List<ProdottoDemo>> trovaPerPrezzoMinoreDi(@RequestParam BigDecimal prezzo) {
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaPerPrezzoMinoreDi(prezzo);
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(prodotto);
		}
		
	}
	
	@GetMapping(value= "/prezzo-maggiore")
	public ResponseEntity<List<ProdottoDemo>> trovaPerPrezzoMaggioreDi(@RequestParam BigDecimal prezzo) {
		
		List<ProdottoDemo> prodotto = prodottoDemoService.trovaPerPrezzoMaggioreDi(prezzo);
		
		if(prodotto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return	ResponseEntity.ok(prodotto);
		}
		
	}
	
	
	
	
	//POST method

	
	//Creare uno o più istanze nella tabella
	
	@PostMapping
	public ResponseEntity<List<ProdottoDemo>> creaNuoviProdotti(@RequestBody List<ProdottoDemo> prodottoDemo){
		
		List<ProdottoDemo> prodottoSalvato = prodottoDemoService.salvaTutto(prodottoDemo);
		return ResponseEntity.ok(prodottoSalvato);
		
	}
	
	
	
	
	//PUT method

	
	@PutMapping(value= "/{id}")
	public ResponseEntity<ProdottoDemo> aggiornaProdotto(@PathVariable long id, @RequestBody ProdottoDemo prodottoAggiornato) {
		
		Optional<ProdottoDemo> prodottoEsistente = prodottoDemoService.trovaPerId(id);
		
		if(prodottoEsistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {			
		
			ProdottoDemo prodottoDaAggiornare = prodottoEsistente.get();
			
			prodottoDaAggiornare.setNome(prodottoAggiornato.getNome());
			prodottoDaAggiornare.setPrezzo(prodottoAggiornato.getPrezzo());
		    
			ProdottoDemo prodotto = prodottoDemoService.salva(prodottoDaAggiornare);
		    
		    return ResponseEntity.ok(prodotto);
		}
	}
	
	
	
	//DELETE methods
	
	
	//Cancellare tutti i record dalla tabella

	@DeleteMapping
	public ResponseEntity<Void> cancellaTutto() {
		
		prodottoDemoService.cancellaTutto();
		return ResponseEntity.noContent().build();
				
	}
	
	//Cancellare una singola istanza, selezionandola per ID
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Void> cancellaPerId(@PathVariable long id) {
		
		Optional<ProdottoDemo> prodottoCancellato = prodottoDemoService.trovaPerId(id);
		
		if(prodottoCancellato.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			prodottoDemoService.cancella(prodottoCancellato.get()); //Non abbiamo mai creato un vero e proprio deleteById nel repository, ma non è necessario
			return ResponseEntity.noContent().build();
		}
			
	}
}
