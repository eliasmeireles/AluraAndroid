package systemplus.com.br.meuempregocom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco implements Serializable {

	protected  String id;
	protected Estado estado;
	protected String cep;
	protected String logradouro;
	protected String numero;
	protected String complemento;
	protected String bairro;
	protected String cidade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco{" +
				"id='" + id + '\'' +
				", estado=" + estado +
				", cep='" + cep + '\'' +
				", logradouro='" + logradouro + '\'' +
				", numero='" + numero + '\'' +
				", complemento='" + complemento + '\'' +
				", bairro='" + bairro + '\'' +
				", cidade='" + cidade + '\'' +
				'}';
	}
}
