package controllers;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.Message;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.EbeanConfig;
import play.mvc.*;
import views.html.add;
import views.html.index;
import views.html.listpage;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<SampleForm> form;
    private final Form<Message> messageForm;
    private final EbeanServer ebeanServer;

    @Inject
    public HomeController(FormFactory formFactory, EbeanConfig ebeanConfig){
        this.form = formFactory.form(SampleForm.class);
        this.messageForm = formFactory.form(Message.class);
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
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

    public CompletionStage<Result> list(){
        int page = 0;
        int pagesize = 5;

        // https://github.com/playframework/play-java-ebean-example/blob/2.6.x/app/controllers/HomeController.java
        return CompletableFuture.supplyAsync(() -> ebeanServer.find(Message.class).orderBy("id")
                .setFirstRow(page * pagesize).setMaxRows(pagesize).findPagedList())
                .thenApply(list -> {

                    for(Message m : list.getList()){
                        System.out.println(m.id);
                    }
                    return ok(listpage.render(list));
                });
    }

    public Result add(){
        return ok(views.html.add.render("please input", messageForm));
    }

    public Result create(){
        final Form<Message> f = messageForm.bindFromRequest();
        if(!f.hasErrors()){
            Message data = f.get();
            data.save();
            return redirect("/add");
        }else{
            return badRequest(add.render("ERROR", messageForm));
        }
    }

}
