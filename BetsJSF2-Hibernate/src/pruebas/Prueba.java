package pruebas;

import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import modelo.HibernateUtil;

public class Prueba {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event e = new Event(3, "Probando", UtilDate.newDate(2020,7,1));
		Question q = new Question(2, "Preg de pru", 5.2f, e);
		session.save(e);
		session.save(q);
		
		session.getTransaction().commit(); 
		System.out.println("Acabado");}
}
