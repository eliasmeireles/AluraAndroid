package systemplus.com.br.meuempregocom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Curriculo {

	protected String id;
	protected Usuario usuario;
	protected String estadoCivil;
	protected String celular;
	protected String telefoneResidencial;
	protected String objetivo;
	protected Date dataCadastro;
	protected Date dataDeAtualizacao;
	protected List<CurriculoExperiencia> curriculoExperiencia;
	protected List<CurriculoFormacao> curriculoFormacao;
	protected List<CurriculoInformacoesAdicionais> informacoesAdicionais;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataDeAtualizacao() {
		return dataDeAtualizacao;
	}

	public void setDataDeAtualizacao(Date dataDeAtualizacao) {
		this.dataDeAtualizacao = dataDeAtualizacao;
	}
	
	public List<CurriculoExperiencia> getCurriculoExperiencia() {
		return curriculoExperiencia;
	}

	public void setCurriculoExperiencia(List<CurriculoExperiencia> curriculoExperiencia) {
		this.curriculoExperiencia = curriculoExperiencia;
	}

	public List<CurriculoFormacao> getCurriculoFormacao() {
		return curriculoFormacao;
	}

	public void setCurriculoFormacao(List<CurriculoFormacao> curriculoFormacao) {
		this.curriculoFormacao = curriculoFormacao;
	}

	public List<CurriculoInformacoesAdicionais> getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(List<CurriculoInformacoesAdicionais> informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}
}
