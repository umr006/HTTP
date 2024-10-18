package servlet;

import dto.FlightDto;
import service.FlightService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;


@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Список перелетов:</h1>");
            writer.write("<ul>");
            flightService.findAll().forEach(flightDto -> {
                writer.write(
                        """
                           <li>
                                <a href="/tikets?flight=%d">%s</a>
                           </li>
                           """.formatted(flightDto.getId(), flightDto.getDescription())
                );
            });
            writer.write("</ul>");
        }
    }
}
