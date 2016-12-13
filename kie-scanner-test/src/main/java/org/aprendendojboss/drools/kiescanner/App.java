package org.aprendendojboss.drools.kiescanner;

import java.util.Date;

import org.aprendendojboss.drools.model.Pessoa;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App {
	
	
	public static void main(String[] args) throws InterruptedException {
		KieServices kServices = KieServices.Factory.get();
		
		ReleaseId releaseID = kServices.newReleaseId("org.aprendendojboss.drools", "drools-analise-fundos", "1.0-SNAPSHOT");
		KieContainer kContainer = kServices.newKieContainer(releaseID);
		
		KieScanner kScanner = kServices.newKieScanner(kContainer);
		kScanner.start(5000);
		
		while(true) {
			System.out.println();
			System.out.println("** Analisando pessoa **");
			KieBase kBase = kContainer.getKieBase("HelloKB");
			KieSession kSession = kBase.newKieSession();
			Pessoa p = new Pessoa("William", 20, 10500);
			kSession.insert(p);
			kSession.fireAllRules();
			System.out.println(p);
			System.out.println("Sleeping at " + new Date());
			Thread.sleep(5000);
		}
	}
}
