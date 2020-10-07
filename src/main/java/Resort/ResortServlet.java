package Resort;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResortServlet")
public class ResortServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    res.setContentType("text/plain");
    String urlPath = req.getPathInfo();

    // check we have a URL!
    if (urlPath == null || "".equals(urlPath)) {
      res.setStatus(HttpServletResponse.SC_NOT_FOUND);
      res.getWriter().write("missing paramterers");
      return;
    }

    String[] urlParts = urlPath.split("/");
    // and now validate url path and return the response status code
    // (and maybe also some value if input is valid)

    if (!isUrlValid(urlParts)) {
      res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } else {
      res.setStatus(HttpServletResponse.SC_OK);
      // do any sophisticated processing with urlParts which contains all the url params
      if (urlParts.length == 3) {
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write("Day 10 vertical");
      } else {
        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Information Not Found");
      }
    }
  }

  private boolean isUrlValid(String[] urlPath) {

    switch (urlPath.length) {
      case 3:
          if (!"day".equals(urlPath[1])) return false;
          if (!"top10vert".equals(urlPath[2])) return false;
        return true;
      default:
        return false;
    }
  }
}
