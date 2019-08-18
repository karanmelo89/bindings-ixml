package ixml.bindings_ixml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wfr.rules.Variant;

public class AppTest {
    
	public static void main(String[] args) throws Exception{
			
		ArrayList<ArrayList<String>> params = new ArrayList<ArrayList<String>>();
		ArrayList<String> parametro = new ArrayList<String>();
		parametro.add("id");
		parametro.add("41847448");
		params.add(parametro);
		parametro = new ArrayList<String>();
		parametro.add("emitente_nome");
		parametro.add("PAULO BRITO OLIVEIRA EPP");
		params.add(parametro);
//		com.aplication.sdk.bindings.ixml.Bindings.obterDocumentos("61ED66F60C06203EC14DFD0C0C0E2632C457D44735372908", null, params);

//		com.aplication.sdk.bindings.ixml.Bindings.consultarDocumentoSefaz(new Variant(""), new Variant(""), null);

//		com.aplication.sdk.bindings.ixml.Bindings.retornoDocumentoSefaz(new Variant("35181182489641000116550030000000031000000339"));
		
	}
}
