package br.com.rony.spring.boot.projetos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.rony.spring.boot.projetos.domain.Pessoa;

@Repository
public class PessoaRepository {
	
    @PersistenceContext
    private EntityManager em;

    
    public void salvar(Pessoa cliente) {
        em.persist(cliente);
    }

    
    public void atualizar(Pessoa cliente) {
        em.merge(cliente);
    }

    
    public void excluir(long id) {
        em.remove(em.getReference(Pessoa.class, id));
    }
    
    
    public Pessoa recuperarPorId(long id) {
        return em.find(Pessoa.class, id);
    }
    

    
    public List<Pessoa> recuperarPorNome(String nomePessoa) {
        return em.createQuery("select p from Pessoa p where p.nome like :nome", Pessoa.class)
                .setParameter("nome", nomePessoa)
                .getResultList();
    }
}
