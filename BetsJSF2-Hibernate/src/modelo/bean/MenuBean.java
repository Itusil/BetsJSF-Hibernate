package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class MenuBean {
	private Date fecha;
	private Integer idEvento;
	private Event evento;
	private String preg;
	private String minbet;
	private BLFacade bl= new BLFacadeImplementation();
	private List<Event> eventos=new ArrayList<Event>();
	private List<Question> preguntas = new ArrayList<Question>();
	public String getPreg() {
		return preg;
	}

	public void setPreg(String preg) {
		this.preg = preg;
	}

	public String getMinbet() {
		return minbet;
	}

	public void setMinbet(String minbet) {
		this.minbet = minbet;
	}

	public Event getEvento() {
		return evento;
	}

	public void setEvento(Event evento) {
		this.evento = evento;
	}


	public MenuBean() {
	}

	public List<Question> getPreguntas(){
		return preguntas;
	}
	
	public List<Event> getEventos(){
		return eventos;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public void onDateSelectMostrar(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Fecha escogida:"+this.getFecha().toString()));//event.getObject().toString())
	}
	public void onDateSelect(SelectEvent event) {
		eventos=new ArrayList<Event>();
		Vector<Event> j = bl.getEvents(getFecha());
		Iterator<Event> jit = j.iterator();
		while (jit.hasNext()) {
			eventos.add(jit.next());
		}
	}

	public void printPreguntas () {
		int id_ = getIdEvento();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Id:"+id_));//event.getObject().toString())
	}
	
	public void obtenerEventosFecha() {
		this.eventos = bl.getEvents(fecha);
	}
	
	public void verPreguntas() {
		Integer id_ = getIdEvento();
		System.out.println("id: "+ id_);
		List<Event> etos = getEventos();
		Event e2 = null;
		for(Event e: etos) {
			if(e.getEventNumber()== id_) {
				e2 = e;
			}
		}
		try {
			preguntas = e2.getQuestions();		
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void anadirPregunta() {
		float minimo = Float.parseFloat(getMinbet());
		try {
			bl.createQuestion(evento, preg, minimo);
			this.refrescarEventos();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Pregunta añadida correctamente"));
		} catch (EventFinished e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("ok",
					new FacesMessage("ERROR: El evento ya ha finalizado"));
		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("error",new FacesMessage("ERROR: Existe una pregunta con la misma descripcion"));
		}
	}
	
	public void refrescarEventos() {
		eventos=new ArrayList<Event>();
		Vector<Event> j = bl.getEvents(getFecha());
		Iterator<Event> jit = j.iterator();
		while (jit.hasNext()) {
			eventos.add(jit.next());
		}
	}



}
