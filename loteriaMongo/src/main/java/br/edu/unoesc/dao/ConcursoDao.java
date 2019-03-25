package br.edu.unoesc.dao;

import br.edu.unoesc.model.Concurso;

public interface ConcursoDao extends CrudDao<Concurso> {
	void adicionaConcurso();
	void validaParticipante();
	Concurso buscarConcurso(Long codigo);
}
