package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
//@Interceptors({LogInterceptador.class})
public class AutorDao {

	@PersistenceContext
	private EntityManager manager;

	@PostConstruct
	void aposCriacao() {
		System.out.println("AutorDAO foi criado");
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void salva(Autor autor) {

		System.out.println("Salvando o autor " + autor.getNome());

//		try {
//			Thread.sleep(20000); // 20s
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		manager.persist(autor);

		System.out.println("Salvou o autor " + autor.getNome());
	}

	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = manager.find(Autor.class, autorId);
		return autor;
	}

}
