package Skier;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Skier.SkierServlet")
public class SkierServlet extends HttpServlet {

  protected void doPost(HttpServletRequest req, HttpServletResponse res)
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
    System.out.println(urlPath);
    // and now validate url path and return the response status code
    // (and maybe also some value if input is valid)

    if (!isUrlValid(urlParts)) {
      res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } else {
      res.setStatus(HttpServletResponse.SC_OK);
      // do any sophisticated processing with urlParts which contains all the url params
      if (urlParts.length == 2) {
        res.setStatus(HttpServletResponse.SC_CREATED);
        res.getWriter().write("{}");
      } else {
        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Information Not Found");
      }
    }
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("application/json");
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
      // do any sophisticated processing with urlParts which contains all the url params
      if (urlParts.length == 6) {
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write("{}");
      } else if (urlParts.length == 3) {
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write("{}");
      } else {
        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Information Not Found");
      }
    }
  }

  private boolean isUrlValid(String[] urlPath) {
    // urlPath  = "/1/seasons/2019/day/1/skier/123"
    // urlParts = [, 1, seasons, 2019, day, 1, skier, 123]

    switch (urlPath.length) {
      case 6:
        try {
          if (!"days".equals(urlPath[2])) return false;
          Integer.parseInt(urlPath[3]);
          if (!"skiers".equals(urlPath[4])) return false;
          Integer.parseInt(urlPath[5]);
        } catch (NumberFormatException e) {
          return false;
        }
        return true;
      case 3:
        try {
          Integer.parseInt(urlPath[1]);
          if (!"vertical".equals(urlPath[2])) return false;
        } catch (NumberFormatException e) {
          return false;
        }
        return true;
      case 2:
        if (!"liftrides".equals(urlPath[1])) return false;
        return true;
      default:
        return false;
    }
  }
}
