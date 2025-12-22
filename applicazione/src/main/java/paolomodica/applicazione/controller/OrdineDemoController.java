package paolomodica.applicazione.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import paolomodica.applicazione.entita.OrdineDemo;
import paolomodica.applicazione.entita.ProdottoDemo;
import paolomodica.applicazione.entita.UtenteDemo;
import paolomodica.applicazione.servizi.OrdineDemoService;





@RestController
@RequestMapping("/ordini")
public class OrdineDemoController {

	
	
	
    private OrdineDemoService ordineDemoService;

    
    
    
    @Autowired
    public OrdineDemoController(OrdineDemoService ordineDemoService) {
        this.ordineDemoService = ordineDemoService;
    }

    
    

  //GET method per trovare tutto
    
    @GetMapping
    public ResponseEntity<List<OrdineDemo>> trovaTutti() {
    	
        List<OrdineDemo> ordini = ordineDemoService.trovaTutto();
        
        if (ordini.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordini);
    }

	//GET methods per singoli parametri
    
    @GetMapping("/{id}")
    public ResponseEntity<OrdineDemo> trovaPerId(@PathVariable long id) {
    	
        return ordineDemoService.trovaPerId(id)
    			.map(u -> ResponseEntity.ok(u))
    			.orElse(ResponseEntity.notFound()
    					.build());

    	}

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<OrdineDemo>> trovaPerUtente(@PathVariable long utenteId) {
    	
        List<OrdineDemo> ordine = ordineDemoService.trovaPerUtenteId(utenteId);
        
        if (ordine.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordine);
    }

    @GetMapping("/prodotto/{prodottoId}")
    public ResponseEntity<List<OrdineDemo>> trovaPerProdotto(@PathVariable long prodottoId) {
    	
        List<OrdineDemo> ordine = ordineDemoService.trovaPerProdottoId(prodottoId);
        
        if (ordine.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordine);
    }
    
    
    //GET methods per le istanze di utenti e prodotti corrispondenti l'uno all'altro
    
    // Tutti i prodotti acquistati da un certo utente
    @GetMapping("/utente/{id}/prodotti")
    public ResponseEntity<List<ProdottoDemo>> trovaProdottiPerUtente(@PathVariable long id) {
    	
        List<ProdottoDemo> prodotto = ordineDemoService.trovaProdottiPerUtente(id);
        
        if (prodotto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prodotto);
    }

    // Tutti gli utenti che hanno acquistato un certo prodotto
    @GetMapping("/prodotto/{id}/utenti")
    public ResponseEntity<List<UtenteDemo>> trovaUtentiPerProdotto(@PathVariable long id) {
    	
        List<UtenteDemo> utente = ordineDemoService.trovaUtentiPerProdotto(id);
        
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utente);
    }
	
	
	
	//POST method

	
	//Creare uno o più istanze nella tabella
	
    @PostMapping
    public ResponseEntity<List<OrdineDemo>> creaOrdini(@RequestBody List<OrdineDemo> ordini) {
        List<OrdineDemo> ordiniSalvati = ordineDemoService.salvaTutto(ordini);
        return ResponseEntity.ok(ordiniSalvati);
    }
	
	
	
	//PUT method

	
    @PutMapping("/{id}")
    public ResponseEntity<OrdineDemo> aggiornaOrdine(@PathVariable long id, @RequestBody OrdineDemo ordineAggiornato) {
        Optional<OrdineDemo> ordineEsistente = ordineDemoService.trovaPerId(id);

        if (ordineEsistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
        	
            OrdineDemo ordine = ordineEsistente.get();
            
            ordine.setQuantita(ordineAggiornato.getQuantita());
            ordine.setDataOrdine(ordineAggiornato.getDataOrdine());

            OrdineDemo ordineSalvato = ordineDemoService.salva(ordine);
            
            return ResponseEntity.ok(ordineSalvato);
        }
    }

	
	
	//DELETE methods
	
	
	//Cancellare tutti i record dalla tabella

    @DeleteMapping
    public ResponseEntity<Void> cancellaTutti() {
        ordineDemoService.cancellaTutto();
        return ResponseEntity.noContent().build();
    }
	
	//Cancellare una singola istanza, selezionandola per ID
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancellaPerId(@PathVariable long id) {
        Optional<OrdineDemo> ordine = ordineDemoService.trovaPerId(id);
        if (ordine.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            ordineDemoService.cancella(ordine.get());
            return ResponseEntity.noContent().build();
        }
    }

}