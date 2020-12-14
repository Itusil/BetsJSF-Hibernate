package dataAccess;

import java.util.Calendar;
import java.util.Locale;

import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import modelo.HibernateUtil;

public class HibernateDataAccess {
	public HibernateDataAccess(boolean initializeMode) {
		if(initializeMode) {
			initializeDB();
		}
	}
	
	public HibernateDataAccess() {
		new HibernateDataAccess(false);
	}
	
	public void initializeDB(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Calendar today = Calendar.getInstance();

		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}  

		Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17));
		Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
		Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
		Event ev4=new Event(4, "Alaves-Deportivo", UtilDate.newDate(year,month,17));
		Event ev5=new Event(5, "Espanol-Villareal", UtilDate.newDate(year,month,17));
		Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
		Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
		Event ev8=new Event(8, "Girona-Leganes", UtilDate.newDate(year,month,17));
		Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
		Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

		Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
		Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
		Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
		Event ev14=new Event(14, "Alaves-Deportivo", UtilDate.newDate(year,month,1));
		Event ev15=new Event(15, "Espanol-Villareal", UtilDate.newDate(year,month,1));
		Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


		Event ev17=new Event(17, "Malaga-Valencia", UtilDate.newDate(year,month,28));
		Event ev18=new Event(18, "Girona-Leganes", UtilDate.newDate(year,month,28));
		Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
		Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));

		Question q1;
		Question q2;
		Question q3;
		Question q4;
		Question q5;
		Question q6;

		if (Locale.getDefault().equals(new Locale("es"))) {
			q1=ev1.addQuestion("¿Quien ganara el partido?",1);
			q2=ev1.addQuestion("¿Quien metera gol?",2);
			q3=ev11.addQuestion("¿Quien ganara el partido?",1);
			q4=ev11.addQuestion("¿Cuantos goles marcaran?",2);
			q5=ev17.addQuestion("¿Quien ganara el partido?",1);
			q6=ev17.addQuestion("¿Habra goles en la primera parte?",2);
		}
		else if (Locale.getDefault().equals(new Locale("en"))) {
			q1=ev1.addQuestion("Who will win the match?",1);
			q2=ev1.addQuestion("Who will score first?",2);
			q3=ev11.addQuestion("Who will win the match?",1);
			q4=ev11.addQuestion("How many goals will be scored in the match?",2);
			q5=ev17.addQuestion("Who will win the match?",1);
			q6=ev17.addQuestion("Will there be goals in the first half?",2);
		}			
		else {
			q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
			q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
			q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
			q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
			q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
			q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

		}


		session.save(q1);
		session.save(q2);
		session.save(q3);
		session.save(q4);
		session.save(q5);
		session.save(q6);


		session.save(ev1);
		session.save(ev2);
		session.save(ev3);
		session.save(ev4);
		session.save(ev5);
		session.save(ev6);
		session.save(ev7);
		session.save(ev8);
		session.save(ev9);
		session.save(ev10);
		session.save(ev11);
		session.save(ev12);
		session.save(ev13);
		session.save(ev14);
		session.save(ev15);
		session.save(ev16);
		session.save(ev17);
		session.save(ev18);
		session.save(ev19);
		session.save(ev20);			

		System.out.println("session initialized");

		session.getTransaction().commit();
	}
	
		private void createAndStoreQuestion(String question, float betMinimum) { 
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Question q = new Question();

		q.setQuestion(question);
		q.setBetMinimum(betMinimum);
				
		session.save(q);
		session.getTransaction().commit();
	}
}
