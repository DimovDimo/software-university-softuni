package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    private final static String CATS_CREATE_HTML =
            "C:\\Users\\User\\Desktop\\Java\\fluffyduffymunchkincats\\src\\main\\resources\\views\\catCreate.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatCreateServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println(this.htmlReader.readHtmlFile(CATS_CREATE_HTML));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cat cat = new Cat();
        cat.setName(
                req.getParameter("name")
        );
        cat.setBreed(
                req.getParameter("breed")
        );
        cat.setColor(
                req.getParameter("color")
        );
        cat.setAge(
                Integer.parseInt(req.getParameter("age"))
        );

        if(req.getSession()
                .getAttribute("cats") == null){
            req.getSession()
                    .setAttribute("cats", new LinkedHashMap<>());
        }

        ((Map<String,Cat>)req
                .getSession()
                .getAttribute("cats"))
                .putIfAbsent(cat.getName(), cat);

        resp.sendRedirect(String
                .format("/cats/profile?catName=%s", cat.getName()));
    }
}