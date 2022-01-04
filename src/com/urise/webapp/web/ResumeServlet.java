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
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

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
                resume.setContacts(new EnumMap<>(ContactType.class));
                resume.setSections(new EnumMap<>(SectionType.class));
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp")
                .forward(request, response);
    }

    private void convertListSectionForEditJsp(Resume resume) {
        convertListSection(resume, SectionType.ACHIEVEMENT);
        convertListSection(resume, SectionType.QUALIFICATIONS);
    }

    private void convertListSection(Resume resume, SectionType sectionType) {
        ListSection listSection = (ListSection) resume.getSections().get(sectionType);
        if (listSection == null) {
            listSection = new ListSection("");
        } else {
            listSection.setItems(List.of(listSection.getItems().stream()
                    .map(String::new)
                    .collect(Collectors.joining("\n\n"))));
        }
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
        String contentTextSectionObjective = request.getParameter("textSectionObjective");
        String contentTextSectionPersonal = request.getParameter("textSectionPersonal");
        String contentListSectionAchievement = request.getParameter("listSectionAchievement");
        String contentListSectionQualifications = request.getParameter("listSectionQualifications");
        if (contentTextSectionObjective.trim().length() > 0) {
            resume.addSection(SectionType.OBJECTIVE, new TextSection(contentTextSectionObjective));
        } else {
            resume.getSections().remove(SectionType.OBJECTIVE);
        }
        if (contentTextSectionPersonal.trim().length() > 0) {
            resume.addSection(SectionType.PERSONAL, new TextSection(contentTextSectionPersonal));
        } else {
            resume.getSections().remove(SectionType.PERSONAL);
        }
        if (contentListSectionAchievement.trim().length() > 0) {
            resume.addSection(SectionType.ACHIEVEMENT, new ListSection(convertContentListSectionForDB(contentListSectionAchievement)));
        } else {
            resume.getSections().remove(SectionType.ACHIEVEMENT);
        }
        if (contentListSectionQualifications.trim().length() > 0) {
            resume.addSection(SectionType.QUALIFICATIONS, new ListSection(convertContentListSectionForDB(contentListSectionQualifications)));
        } else {
            resume.getSections().remove(SectionType.QUALIFICATIONS);
        }
        if (uuid.length() == 0) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume?uuid=" + resume.getUuid() + "&action=edit");
    }

    private List<String> convertContentListSectionForDB(String contet) {
        return contet.lines()
                .filter(s -> s.trim().length() > 0)
                .collect(Collectors.toList());
    }
}