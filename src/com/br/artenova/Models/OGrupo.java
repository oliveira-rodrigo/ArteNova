package com.br.artenova.Models;

import com.orm.SugarRecord;

import android.content.Context;

public class OGrupo extends SugarRecord<OGrupo> {

	private Integer ID;
	private String Descricao;
	private String Imagem_1;
	private String Imagem_2;
	private String Imagem_3;

	public OGrupo(Context context) {
	}

	public OGrupo(Integer ID, String Descricao, String Imagem_1, String Imagem_2,
			String Imagem_3) {
		this.ID = ID;
		this.Descricao = Descricao;
		this.Imagem_1 = Imagem_1;
		this.Imagem_2 = Imagem_2;
		this.Imagem_3 = Imagem_3;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
	}

	public String getImagem_1() {
		return Imagem_1;
	}

	public void setImagem_1(String Imagem_1) {
		this.Imagem_1 = Imagem_1;
	}

	public String getImagem_2() {
		return Imagem_2;
	}

	public void setImagem_2(String Imagem_2) {
		this.Imagem_2 = Imagem_2;
	}

	public String getImagem_3() {
		return Imagem_3;
	}

	public void setImagem_3(String Imagem_3) {
		this.Imagem_3 = Imagem_3;
	}
}
