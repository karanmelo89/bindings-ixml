package com.aplication.sdk.bindings.ixml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import wfr.rules.Variant;


public class Bindings {
/*
	public static String getAuthorization(String key){	
		String authorization = "Bearer " + key.toString();
		
		return authorization;
	}
*/
	public static Variant obterDocumentos(Variant chaveApi, Variant listaCampos, Variant listaFiltro) throws Exception{
				
		List<String> campos;
		ArrayList<ArrayList<String>> filtro;

		String endpoint = "https://api.ixml.com.br/v2/documentos";
        
		HttpClient httpClient = HttpClientBuilder.create().build();		
		
		URIBuilder builder = new URIBuilder(endpoint);
		if(listaCampos.getObject()  instanceof ArrayList || listaCampos.isNull()){
			campos = (ArrayList<String>)listaCampos.getObject();
			if(campos != null){
				builder.setParameter("campos", campos.toString().replace("[", "").replace("]", ""));		
			}
		} else { 
			throw new Exception("Deve ser fornecido uma lista de campos.");
		}
		if(listaFiltro.getObject() instanceof ArrayList || listaFiltro.isNull()){
			if(!listaFiltro.isNull()){
				filtro = (ArrayList<ArrayList<String>>)listaFiltro.getObject();			
				for(int x = 0; x < filtro.size(); x++){
					builder.setParameter(filtro.get(x).get(0), filtro.get(x).get(1));
				}
			}
		} else { 
			throw new Exception("Deve ser fornecido uma lista de lista contendo filtro e valor.");
		}
		
        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + chaveApi.toString());

        HttpResponse response = httpClient.execute(httpGet);
        
        BufferedReader rd = new BufferedReader(
        		new InputStreamReader(response.getEntity().getContent()));

        	StringBuffer result = new StringBuffer();
        	String line = "";
        	while ((line = rd.readLine()) != null) {
        		result.append(line);
        	}
        
        return new Variant(result);
	}
	
	public static Variant consultarDocumentoSefaz(Variant chaveApi, Variant chaveAcesso, Variant cnpjEmpresa) throws Exception{
		
 	    String endpoint = "https://api.ixml.com.br/v2/consulta/sefaz";
        
		HttpClient httpClient = HttpClientBuilder.create().build();		
        HttpPost httpPost = new HttpPost(endpoint);
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.addHeader(HttpHeaders.AUTHORIZATION,  "Bearer " + chaveApi.toString());        

    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("chave_acesso", chaveAcesso.toString().replaceAll(" ", "")));
    	params.add(new BasicNameValuePair("cnpj_empresa", cnpjEmpresa.toString()));
    	
    	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
    	
    	httpPost.setEntity(entity);
    	
        HttpResponse response = httpClient.execute(httpPost);
        
        BufferedReader rd = new BufferedReader(
        		new InputStreamReader(response.getEntity().getContent()));

        	StringBuffer result = new StringBuffer();
        	String line = "";
        	while ((line = rd.readLine()) != null) {
        		result.append(line);
        	}
        
        return new Variant(result);
	}
	
	public static Variant retornoDocumentoSefaz(Variant chaveApi, Variant idConsulta) throws Exception{
		
 	    String endpoint = "https://api.ixml.com.br/v2/retorno/sefaz";
        
		HttpClient httpClient = HttpClientBuilder.create().build();		

		URIBuilder builder = new URIBuilder(endpoint);
		builder.setParameter("id_consulta", idConsulta.toString());
		
        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        httpGet.addHeader(HttpHeaders.AUTHORIZATION,  "Bearer " + chaveApi.toString());

        HttpResponse response = httpClient.execute(httpGet);
        
        BufferedReader rd = new BufferedReader(
        		new InputStreamReader(response.getEntity().getContent()));

        	StringBuffer result = new StringBuffer();
        	String line = "";
        	while ((line = rd.readLine()) != null) {
        		result.append(line);
        	}
        
        return new Variant(result);
	}
}
