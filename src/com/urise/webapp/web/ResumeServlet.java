package com.urise.webapp.web;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Resume> resumeList = storage.getAllSorted();

        PrintWriter writer = response.getWriter();
        writer.println("<table>\n" +
                "<body>\n" +
                "<tr>\n" +
                "<th>UUID</th>\n" +
                "<th>Full Name</th>\n" +
                "</tr>");

        for (Resume r : resumeList) {
            writer.println("<tr>");
            writer.println("<td>" + r.getUuid() + "</td>");
            writer.println("<td>" + r.getFullName() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</body\n" +
                "</table>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}