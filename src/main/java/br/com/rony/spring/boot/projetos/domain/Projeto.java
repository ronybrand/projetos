package br.com.rony.spring.boot.projetos.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "projeto")
public class Projeto {
	public enum Risco {
	    BAIXO, MEDIO, ALTO
	}
	public enum Status {
		EM_ANALISE("em análise"),
		ANALISE_REALIZADA("análise realizada"),
		ANALISE_APROVADA("análise aprovada"),
		INICIADO("iniciado"),
		PLANEJADO("planejado"),
		EM_ANDAMENTO("em andamento"),
		ENCERRADO("encerrado"),
	    CANCELADO("cancelado");
		
	    private String descricao;
	 
	    Status(String descricao) {
	        this.descricao = descricao;
	    }
	 
	    public String getDescricao() {
	        return descricao;
	    }
	}	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false, length = 200)
    private String nome;
    
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Column(name = "data_previsao_fim")
	private Date dataPrevisaoFim;
	
	@Column(name = "data_fim")
	private Date dataFim;
	
    @Column(length = 5000)
    private String descricao;
    

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private Status status;

    private Float orcamento;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private Risco risco;
    
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "membros", 
      joinColumns = @JoinColumn(name = "idprojeto"), 
      inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    Set<Pessoa> membros;

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(Date dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Float getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Float orcamento) {
		this.orcamento = orcamento;
	}

	public Risco getRisco() {
		return risco;
	}

	public void setRisco(Risco risco) {
		this.risco = risco;
	}

	public Pessoa getGerente() {
		return gerente;
	}

	public void setGerente(Pessoa gerente) {
		this.gerente = gerente;
	}

	public Set<Pessoa> getMembros() {
		return membros;
	}

	public void setMembros(Set<Pessoa> membros) {
		this.membros = membros;
	}
}
