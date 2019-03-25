package br.edu.unoesc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import br.edu.unoesc.conexao.ConexaoMongo;
import br.edu.unoesc.model.Concurso;
import br.edu.unoesc.model.Ganhadores;

public class ConcursoMongo implements ConcursoDao {

	private MongoCollection<Concurso> getCollection() {
		return ConexaoMongo.getDatabase().getCollection("loteria", Concurso.class);
	}

	@Override
	public void adicionaConcurso() {
		Scanner sc = new Scanner(System.in);
		Concurso concurso = new Concurso();
		System.out.println("Adicione o número do concurso: ");
		concurso.setNumero(sc.nextLong());
		concurso.setData(new Date());
		System.out.println("Adicione os 7 números sorteados do concurso:");
		List<Integer> lista = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			lista.add(sc.nextInt());
		}
		concurso.setNumeros(lista);
		inserir(concurso);
		sc.close();
	}

	@Override
	public void validaParticipante() {

		// verifica se ganhou
		Concurso c = adicionaParticipante();
		int aux = 0;
		for (int i = 0; i < c.getNumeros().size(); i++) {
			for (int j = 0; j < c.getNumeros().size(); j++) {
				if (c.getGanhadores().getNumeros().get(i) == c.getNumeros().get(j)) {
					aux++;
				}
			}
		}
		if (aux >= 5) {
			System.out.println("Parabens, você é um ganhador!");
			alterar(c);
		} else {
			System.out.println("você não é um ganhador!");
		}
	}

	public Concurso adicionaParticipante() {
		Scanner sc = new Scanner(System.in);
		Ganhadores g = new Ganhadores();
		System.out.println("Qual seu nome?");
		g.setNome(sc.next());
		System.out.println("Qual o número do concurso?");
		Long codigo = sc.nextLong();

		Concurso c = buscarConcurso(codigo);

		List<Integer> lista = new ArrayList<>();
		System.out.println("Adicione seus 7 números");
		for (int i = 0; i < 7; i++) {
			lista.add(sc.nextInt());
		}
		g.setNumeros(lista);
		c.setGanhadores(g);
		sc.close();
		return c;
	}

	@Override
	public void inserir(Concurso dado) {
		getCollection().insertOne(dado);

	}

	@Override
	public void alterar(Concurso dado) {
		getCollection().updateOne(Filters.eq("numero", dado.getNumero()),
				combine(set("ganhadores", dado.getGanhadores())));

	}

	@Override
	public void excluir(Concurso dado) {
		getCollection().deleteOne(Filters.eq(dado.getNumero()));
	}

	@Override
	public Concurso buscarConcurso(Long codigo) {
		return getCollection().find(eq("numero", codigo)).first();

	}

}
