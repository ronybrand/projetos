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

import br.com.rony.spring.boot.projetos.domain.Projeto;
import br.com.rony.spring.boot.projetos.error.ExcecaoRegraNegocio;
import br.com.rony.spring.boot.projetos.service.ProjetoService;

@RestController
@Validated
@RequestMapping( value = "/portifolio/projeto")
public class ProjetoResource {
	@Autowired
	private ProjetoService projetoService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
	public Projeto getClienteById(
			   @Valid @PathVariable("id") Long id) {
	      return projetoService.recuperarPorId(id);
   }
	

	@RequestMapping(value = { "" , "/" } , method = RequestMethod.GET)
	public List<Projeto> getClienteByNome(String nome) {
	      return projetoService.recuperarPorNome(nome);
   }
	
	@PostMapping
	public @ResponseBody ResponseEntity < String > salvar(@Valid @RequestBody Projeto projeto) throws ExcecaoRegraNegocio  { 
    	projetoService.salvar(projeto);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
	
	@PutMapping
	public @ResponseBody ResponseEntity < String > atualizar(@Valid @RequestBody Projeto projeto) throws ExcecaoRegraNegocio  { 
    	projetoService.atualizar(projeto);
    	
    	return new ResponseEntity<String>("Response from PUT method", HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@Valid @PathVariable("id") Long id) throws ExcecaoRegraNegocio {
    	projetoService.excluir(id);
    }
}
