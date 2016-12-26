package org.aprendendojboss.decisiontable;

import java.util.Arrays;
import java.util.List;
import org.aprendendojboss.decisiontable.model.Candidato;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class VerificaCandidatos {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		// Para facilitar o teste eu estou pegando o kie container do classpath, 
		// mas poderia ser do maven também (como já exploramos aqui)
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		
		KieSession ksession = kContainer.newKieSession("statelessKieSession");
		List<Candidato> candidatos =  Arrays.asList(
				new Candidato("William", 28, 1600),
				new Candidato("Luana", 27, 5000),
				new Candidato("Antônio", 1, 0)
		);
		candidatos.forEach(ksession::insert);
		ksession.fireAllRules();
		candidatos.forEach(System.out::println);
	}
}
