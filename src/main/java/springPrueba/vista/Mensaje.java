package springPrueba.vista;

import org.springframework.web.servlet.ModelAndView;



public class Mensaje extends ModelAndView {
	public Mensaje(Tipo tipo, String mensaje) {
		super("message");
		
		this.addObject("message", mensaje);	
		this.addObject("messageClass", tipo.ordinal());
	}
	
	enum Tipo {
		ERROR, SUCCESS, WARNING, INFO;
	}

}
