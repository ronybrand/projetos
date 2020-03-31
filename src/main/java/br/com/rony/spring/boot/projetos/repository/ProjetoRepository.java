package br.com.rony.spring.boot.projetos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.rony.spring.boot.projetos.domain.Projeto;

@Repository
public class ProjetoRepository {
	
    @PersistenceContext
    private EntityManager em;

    
    public void salvar(Projeto cliente) {
        em.persist(cliente);
    }

    
    public void atualizar(Projeto cliente) {
        em.merge(cliente);
    }

    
    public void excluir(long id) {
        em.remove(em.getReference(Projeto.class, id));
    }
    
    
    public Projeto recuperarPorId(long id) {
        return em.find(Projeto.class, id);
    }
    

    
    public List<Projeto> recuperarPorNome(String nomeProjeto) {
        return em.createQuery("select c from Projeto c where c.nome like :nome", Projeto.class)
                .setParameter("nome", nomeProjeto)
                .getResultList();
    }
}
