package br.com.rony.spring.boot.projetos.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rony.spring.boot.projetos.domain.Pessoa;
import br.com.rony.spring.boot.projetos.domain.Projeto;
import br.com.rony.spring.boot.projetos.error.ExcecaoRegraNegocio;
import br.com.rony.spring.boot.projetos.repository.ProjetoRepository;

@Service
@Transactional
public class ProjetoService {
	private static final String EXCEPTION_MEMBRO_NAO_FUNCIONARIO= "Há membro que não é funcionário.";
	private static final String EXCECAO_EXCLUIR_PROJETO= "Projeto iniciado, em andamento ou encerrado não pode ser excluido";
    @Autowired
    private ProjetoRepository projetoRepository;
    
    @Autowired
    private PessoaService pessoaService;

    public void salvar(Projeto projeto) throws ExcecaoRegraNegocio {
    	verificaMembrosSaoFuncionario(projeto.getMembros());
    	projetoRepository.salvar(projeto);
    }
    
    public void atualizar(Projeto projeto) throws ExcecaoRegraNegocio  {
    	verificaMembrosSaoFuncionario(projeto.getMembros());
    	projetoRepository.atualizar(projeto);
    }

    public void excluir(long id)  throws ExcecaoRegraNegocio {
    	Projeto projeto = projetoRepository.recuperarPorId(id);
    	if (projeto.getStatus() == Projeto.Status.INICIADO ||
    			projeto.getStatus() == Projeto.Status.EM_ANDAMENTO ||
    			projeto.getStatus() == Projeto.Status.ENCERRADO) {
			throw new ExcecaoRegraNegocio(EXCECAO_EXCLUIR_PROJETO);
    	}
    	projetoRepository.excluir(id);
    }

    @Transactional(readOnly = true)
	public Projeto recuperarPorId(long id) {
		return projetoRepository.recuperarPorId(id);
	}

	public List<Projeto> recuperarPorNome(String nomeProjeto) {
		return projetoRepository.recuperarPorNome(nomeProjeto);
	}
	
	private void verificaMembrosSaoFuncionario (Set<Pessoa> membros) throws ExcecaoRegraNegocio {
		for (Pessoa pessoa : membros) {
    		pessoa = pessoaService.recuperarPorId(pessoa.getId());
    		if (!pessoa.isFuncionario()) {
    			throw new ExcecaoRegraNegocio(EXCEPTION_MEMBRO_NAO_FUNCIONARIO);
    		}
    	}
	}
}
