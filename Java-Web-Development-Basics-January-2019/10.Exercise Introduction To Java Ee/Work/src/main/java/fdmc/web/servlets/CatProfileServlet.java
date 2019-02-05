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
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private final static String CAT_PROFILE_HTML = "C:\\Users\\User\\Desktop\\Java\\fluffyduffymunchkincats\\src\\main\\resources\\views\\catProfile.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = ((Map<String, Cat>)req
                .getSession()
                .getAttribute("cats"))
                .get(
                        req.getQueryString()
                                .split("=")[1]
                );

        String result="";
        if(cat == null){
            result = this.htmlReader
                    .readHtmlFile("C:\\Users\\User\\Desktop\\Java\\fluffyduffymunchkincats\\src\\main\\resources" +
                            "\\views\\nonExistentCat.html")
                    .replace("{{catName}}", req.getQueryString().split("=")[1]);
        } else {
            result = this.htmlReader
                    .readHtmlFile(CAT_PROFILE_HTML)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());
        }
        resp.getWriter().println(result);
    }
}