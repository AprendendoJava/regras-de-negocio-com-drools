package org.aprendendojboss.drools;

import java.net.URL;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 
 * Carregando arquivos de regra.  <br />
 * Essa é a forma mais verbosa, mas também mais flexível, pois podemos até mesmo carregar regras de Strings definidas no Java!
 * 
 * @author wsiqueir
 *
 */
public class App {

	private static final String CAMINHO_DRL = "/org/aprendendojboss/drools/OlaDrools.drl";

	public static void main(String[] args) {
		// vamos pegar a URL do nosso arquivo
		URL urlDrl = App.class.getResource(CAMINHO_DRL);
		// leia http://aprendendo-jboss.blogspot.com.br/2016/07/kie-conhecimento-e-tudo.html
		KieServices kServices = KieServices.Factory.get();
		KieFileSystem kf = kServices.newKieFileSystem();
		KieResources recursos = kServices.getResources();
		// vamos carregar o nosso arquivo DRL
		kf.write(recursos.newUrlResource(urlDrl));
		// fazemos o build para o releaseId padrão		
		kServices.newKieBuilder(kf).buildAll();
		ReleaseId releasePadrao = kServices.getRepository().getDefaultReleaseId();
		// finalmente temos o nosso KieContainer!..
		KieContainer kContainer = kServices.newKieContainer(releasePadrao);
		// base de conhecimentos
		KieBase kBase = kContainer.getKieBase();
		// sessão de conhecimento
		KieSession kSession = kBase.newKieSession();
		
		// agora é hora da ação! Inserindo um objeto na memória
		kSession.insert(new Pessoa("William", 28));
		// executando todas as regras que atendam ao requisito dos objetos da memória
		kSession.fireAllRules();
	}
}
