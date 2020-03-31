package br.com.rony.spring.boot.projetos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rony.spring.boot.projetos.domain.Pessoa;
import br.com.rony.spring.boot.projetos.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void salvar(Pessoa pessoa) {
    	pessoaRepository.salvar(pessoa);
    }
    
    public void atualizar(Pessoa pessoa) {
    	pessoaRepository.atualizar(pessoa);
    }

    public void excluir(long id) {
    	pessoaRepository.excluir(id);
    }

    @Transactional(readOnly = true)
	public Pessoa recuperarPorId(long id) {
		return pessoaRepository.recuperarPorId(id);
	}

	public List<Pessoa> recuperarPorNome(String nomePessoa) {
		return pessoaRepository.recuperarPorNome(nomePessoa);
	}
}
