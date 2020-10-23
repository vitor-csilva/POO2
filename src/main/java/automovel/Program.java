package automovel;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("automovel");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
			Marca ford	= new Marca(null, "Ford");
			Marca fiat	= new Marca(null, "Fiat");
			
			Modelo mustang	= new Modelo(null, "MUSTANG", 480, ford);
			Modelo fusion	= new Modelo(null, "FUSION", 180, ford);
			Modelo 	linea = new Modelo(null, "LINEA", 154, fiat);
			Modelo toro = new Modelo(null, "TORO", 200, fiat);

			Automovel carroVitor	= new Automovel(null, 2020, 2021, "COR PRETO", 270000.00, 1000, mustang);
			Automovel carroDory	= new Automovel(null, 2019, 2020, "COR PRATA", 120000.00, 2500, toro);
			
			em.persist(ford);
			em.persist(fiat);
			em.persist(mustang);
			em.persist(fusion);
			em.persist(linea);
			em.persist(toro);
			em.persist(carroVitor);
			em.persist(carroDory);

		em.getTransaction().commit();
		
		Query qryMarca = em.createQuery("select m from Marca m");
		List<Marca> marcas = qryMarca.getResultList();
		System.out.println("***** MARCAS CADASTRADAS *****");
		for (Marca m : marcas) {
			System.out.println("Id: " + m.getId() + " Marca: " + m.getNome());
		}
		
		System.out.println("----------------------------------------");
		
		
		Query qryModelo = em.createQuery("select m from Modelo m");
		List<Modelo> modelos = qryModelo.getResultList();
		System.out.println("***** MODELOS CADASTRADOS *****");
		for (Modelo m : modelos) {
			System.out.println("Id: " + m.getId() + " Descrição: " + m.getDescricao() + " CV: " + m.getPotencia() +
								" Marca: " + m.getMarca().getNome());
		}

		System.out.println("----------------------------------------");		
		
		Query qryAutomovel = em.createQuery("select m from Automovel m");
		List<Automovel> autos = qryAutomovel.getResultList();
		System.out.println("***** AUTOMOVEIS CADASTRADOS *****");
		for (Automovel a : autos) {
			System.out.println("Id: " + a.getId() + " Ano/Modelo: " + a.getAnoFabricacao() + "/" + a.getAnoModelo() + 
								" Obs: " + a.getObservacoes() + " Preço: " + a.getPreco() + 
								" Km: " + a.getKilometragem() + " Modelo: " + a.getModelo().getDescricao());
		}
		
		em.close();
		emf.close();
	}
}
