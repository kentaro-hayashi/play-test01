package controllers;

import javassist.tools.rmi.Sample;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.index;

import javax.inject.Inject;

import static play.data.Form.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private  final Form<SampleForm> form;

    @Inject
    public HomeController(FormFactory formFactory){
        this.form = formFactory.form(SampleForm.class);
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render("unko", form));
    }

    public Result send(){
        final Form<SampleForm> f = form.bindFromRequest();
        if(!f.hasErrors()) {
            SampleForm data = f.get();
            String msg = "you typed: " + data.getMessage();
            return ok(index.render(msg, f));
        }else{
            return badRequest(index.render("ERROR", form));
        }
    }

}
