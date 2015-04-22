package springPrueba.vista;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springPrueba.vista.Mensaje.Tipo;

@Controller
public class MainController implements ErrorController {
	@Value("${error.path:/error}")
	private String errorPath;
	
	@RequestMapping("/")
	public String redirect() {
		return "principal";
		// return "redirect:/lista";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request) {
		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		String errorMessage = null;
		if (throwable != null) {
			errorMessage = throwable.getMessage();
		}

		String errorCode = request.getAttribute(
				"javax.servlet.error.status_code").toString();
		return new Mensaje(Tipo.ERROR, "Error código " + errorCode + ": "
				+ errorMessage);
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}
}
