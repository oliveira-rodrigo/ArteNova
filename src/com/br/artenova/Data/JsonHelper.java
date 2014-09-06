package com.br.artenova.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.br.artenova.Data.*;

public class JsonHelper {

	public String jsonString = "";
	public OGrupoRepository ogrupoRep = null;
	public List<String> imagens = null;

	public JsonHelper(JSONObject jsonData, SQLiteDatabaseHelper dbHelper) {

		try {
			
			imagens = new ArrayList<String>();
			
			JSONObject jsonGrupo = jsonData.getJSONObject("Grupo");
			JSONObject jsonVersao = jsonData.getJSONObject("Versao");
			JSONArray jsonAcabamentos = jsonData.getJSONArray("Acabamentos");
			JSONArray jsonAcabamentosEmpresas = jsonData
					.getJSONArray("AcabamentosEmpresas");
			JSONArray jsonBanners = jsonData.getJSONArray("Banners");
			JSONArray jsonCategoriasAcabamentos = jsonData
					.getJSONArray("CategoriasAcabamentos");
			JSONArray jsonDepartamentos = jsonData
					.getJSONArray("Departamentos");
			JSONArray jsonEmpresas = jsonData.getJSONArray("Empresas");
			JSONArray jsonNoticias = jsonData.getJSONArray("Noticias");
			JSONArray jsonProdutos = jsonData.getJSONArray("Produtos");
			JSONArray jsonProdutosImagens = jsonData
					.getJSONArray("ProdutosImagens");
			JSONArray jsonProdutosInfos = jsonData
					.getJSONArray("ProdutosInfos");

			// grupo
			//Log.d("Grupo", jsonGrupo.get("Descricao").toString());
			ogrupoRep = new OGrupoRepository(dbHelper);
			ogrupoRep.saveOrUpdate(jsonGrupo, imagens);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void downloadImagens(List<String> imagens)
	{
		for (String img : imagens) {
			
		}
	}

}
