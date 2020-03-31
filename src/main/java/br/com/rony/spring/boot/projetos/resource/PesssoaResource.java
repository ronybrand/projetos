package br.com.rony.spring.boot.projetos.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rony.spring.boot.projetos.domain.Pessoa;
import br.com.rony.spring.boot.projetos.service.PessoaService;

@RestController
@Validated
@RequestMapping( value = "/portifolio/pessoa")
public class PesssoaResource {
	@Autowired
	private PessoaService pessoaService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
	public Pessoa getClienteById(
			   @Valid @PathVariable("id") Long id) {
	      return pessoaService.recuperarPorId(id);
   }
	

	@RequestMapping(value = { "" , "/" } , method = RequestMethod.GET)
	public List<Pessoa> getClienteByNome(String nome) {
	      return pessoaService.recuperarPorNome(nome);
   }
	
	@RequestMapping(value = "/ex/foos", method = RequestMethod.DELETE)
	@ResponseBody
	public String postFoos() {
	    return "d some Foos";
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity < String > salvar(@Valid @RequestBody Pessoa cliente) { 
    	pessoaService.salvar(cliente);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
	
	@PutMapping
	public @ResponseBody ResponseEntity < String > atualizar(@Valid @RequestBody Pessoa cliente) { 
    	pessoaService.atualizar(cliente);
    	
    	return new ResponseEntity<String>("Response from PUT method", HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id) {
    	pessoaService.excluir(id);
    }
}
