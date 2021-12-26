package com.urise.webapp.web;

import com.urise.webapp.exception.EmptyFieldException;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.urise.webapp.util.ResumesForWeb.convertContentListSectionForDB;
import static com.urise.webapp.util.ResumesForWeb.convertListSectionForEditJsp;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view" -> {
                resume = storage.get(uuid);
            }
            case "edit" -> {
                resume = storage.get(uuid);
                convertListSectionForEditJsp(resume);
            }
            case "save" -> {
                resume = new Resume();
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = uuid.length() == 0 ? new Resume("") : storage.get(uuid);
        if (fullName.trim().length() == 0) {
            throw new EmptyFieldException("The name cannot be empty");
        }
        resume.setFullName(fullName);
        for (ContactType contactType : ContactType.values()) {
            String value = request.getParameter(contactType.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContactData(contactType, value);
            } else {
                resume.getContacts().remove(contactType);
            }
        }
        resume.addSection(SectionType.OBJECTIVE, new TextSection(request.getParameter("textSectionObjective")));
        resume.addSection(SectionType.PERSONAL, new TextSection(request.getParameter("textSectionPersonal")));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(convertContentListSectionForDB(request.getParameter("listSectionAchievement"))));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(convertContentListSectionForDB(request.getParameter("listSectionQualifications"))));
        if (uuid.length() == 0) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume?uuid=" + resume.getUuid() + "&action=view");
    }
}