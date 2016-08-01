package org.aprendendojboss.drools;

import org.aprendendojboss.drools.model.Pessoa;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Carrega o kJAR do repositório maven, mas precisamos antes fazer build do projeto ola-regras
 * 
 * @author wsiqueir
 *
 */
public class App {
	public static void main(String[] args) {
		KieServices kServices = KieServices.Factory.get();
		// nosso artifato
		ReleaseId releaseID = kServices.newReleaseId("org.aprendendojboss.drools", "ola-regras", "1.0");
		KieContainer kContainer = kServices.newKieContainer(releaseID);
		KieBase kBase = kContainer.getKieBase();
		KieSession kSession = kBase.newKieSession();
		kSession.insert(new Pessoa("William", 28));
		kSession.fireAllRules();
	}
}
