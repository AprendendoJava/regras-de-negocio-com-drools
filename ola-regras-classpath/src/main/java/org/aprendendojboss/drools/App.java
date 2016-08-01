package org.aprendendojboss.drools;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 
 * Executa regras do classpath
 * 
 * @author wsiqueir
 *
 */
public class App {
	public static void main(String[] args) {
		KieServices kServices = KieServices.Factory.get();
		// só funciona se tivermos META-INF/kmodule.xml no classpath!
		KieContainer kContainer = kServices.getKieClasspathContainer();
		KieBase kBase = kContainer.getKieBase();
		KieSession kSession = kBase.newKieSession();
		kSession.insert(new Pessoa("William", 28));
		kSession.fireAllRules();
	}
}
