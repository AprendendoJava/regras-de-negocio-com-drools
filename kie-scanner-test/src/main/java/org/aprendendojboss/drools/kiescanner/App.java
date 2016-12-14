package org.aprendendojboss.drools.kiescanner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.aprendendojboss.drools.model.Pessoa;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App {

	// our module information information
	private static final String G = "org.aprendendojboss.drools";
	private static final String A = "drools-analise-fundos";
	private static final String V = "1.0-SNAPSHOT";

	// modify this to match the file in your system
	private static final String DADOS_PESSOA = "/opt/projects/regras-de-negocio-com-drools/kie-scanner-test/src/main/resources/pessoas.txt";

	public static void main(String[] args) throws Exception {
		KieServices kServices = KieServices.Factory.get();

		ReleaseId releaseID = kServices.newReleaseId(G, A, V);
		KieContainer kContainer = kServices.newKieContainer(releaseID);

		KieScanner kScanner = kServices.newKieScanner(kContainer);
		kScanner.start(5000);
		
		while (true) {
			List<Pessoa> pessoas = carregaPessoas();
			System.out.println();
			if (!pessoas.isEmpty()) {
				KieBase kBase = kContainer.getKieBase("HelloKB");
				KieSession kSession = kBase.newKieSession();
				System.out.println("** Analisando pessoas **");
				pessoas.forEach(kSession::insert);
				kSession.fireAllRules();
			} else {
				System.out.println("** Não há novos dados para analisar **");
			}
			Thread.sleep(5000);
		}
	}

	private static List<Pessoa> carregaPessoas() throws Exception {
		List<Pessoa> pessoas = new ArrayList<>();
		Path path = Paths.get(DADOS_PESSOA);
		Files.lines(path).forEach(l -> {
			String[] campos = l.split(",");
			String nome = campos[0];
			int idade = Integer.valueOf(campos[1]);
			double fundos = Double.parseDouble(campos[2]);
			pessoas.add(new Pessoa(nome, idade, fundos, ""));
		});
		Files.write(path, "".getBytes());
		return pessoas;
	}
}
