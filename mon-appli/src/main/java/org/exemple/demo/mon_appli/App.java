package org.exemple.demo.mon_appli;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		//System.out.println("Hello World!");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbrdvmedecins2");
		// entityManager
		EntityManager em = emf.createEntityManager();
		// scanner clavier
		Scanner clavier = new Scanner(System.in);
		// boucle de saisie des requêtes JPQL
		System.out.println("Requete JPQL sur la base dbrdvmedecins2 (* pour arrêter) :");
		String requete = clavier.nextLine();
		while (!requete.trim().equals("*")) {
			try {
				// affichage résultat requête
				for (Object o : em.createQuery(requete).getResultList()) {
					System.out.println(o);
				}
			} catch (Exception e) {
				System.out.println("L'exception suivante s'est produite : " + e);
			}
			// on vide le contexte de persistance
			em.clear();
			// nouvelle requête
			System.out.println("---------------------------------------------");
			System.out.println("Requete JPQL sur la base dbrdvmedecins2 (* pour arrêter) :");
			requete = clavier.nextLine();
		}
		// fermeture des ressources
		em.close();
		emf.close();
		clavier.close();
	}
}
