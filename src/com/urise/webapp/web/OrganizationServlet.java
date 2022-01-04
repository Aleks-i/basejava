package com.urise.webapp.web;

import com.urise.webapp.exception.EmptyFieldException;
import com.urise.webapp.model.OrganizationSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.organization.Link;
import com.urise.webapp.model.organization.Organization;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OrganizationServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        if (uuid.isEmpty()) {
            throw new EmptyFieldException("Enter and save the name");
        }
        Resume resume = storage.get(uuid);

        switch (request.getParameter("sectionType")) {
            case "newExperience" -> {
                setNewOrganizationSection(resume, SectionType.EXPERIENCE, request);
                response.sendRedirect("resume?uuid=" + uuid + "&action=edit");
            }
            case "newEducation" -> {
                setNewOrganizationSection(resume, SectionType.EDUCATION, request);
                response.sendRedirect("resume?uuid=" + uuid + "&action=edit");
            }
            case "editExperience" -> {
                setEditOrganizationSection(resume, SectionType.EXPERIENCE, request);
                response.sendRedirect("resume?uuid=" + uuid + "&action=edit");
            }
            case "editEducation" -> {
                setEditOrganizationSection(resume, SectionType.EDUCATION, request);
                response.sendRedirect("resume?uuid=" + uuid + "&action=edit");
            }
        }
    }

    private void setNewOrganizationSection(Resume resume, SectionType sectionType, HttpServletRequest request) {
        Organization organization = new Organization(request.getParameter("nameOrganization"), request.getParameter("homePage"),
                new Organization.Position(LocalDateTime.parse(request.getParameter("dateStart")).toLocalDate(),
                        LocalDateTime.parse(request.getParameter("dateEnd")).toLocalDate(),
                        request.getParameter("position"), request.getParameter("responsibility")));
        if (resume.getSections().get(sectionType) == null) {
            resume.addSection(sectionType, new OrganizationSection(organization));
        } else {
            ((OrganizationSection) resume.getSections().get(sectionType)).getOrganizations().add(0, organization);
        }
        storage.update(resume);
    }

    private void setEditOrganizationSection(Resume resume, SectionType sectionType, HttpServletRequest request) {
        String idOrganization = request.getParameter("idOrganization");
        String idPosition = request.getParameter("idPosition");
        Organization organization = ((OrganizationSection) resume.getSections().get(sectionType)).getOrganizations().stream()
                .filter(o -> o.getId().equals(idOrganization))
                .findFirst()
                .get();
        organization.setHomePage(new Link(request.getParameter("nameOrganization"), request.getParameter("homePage")));
        Organization.Position position = organization.getPositions().stream()
                .filter(p -> p.getId().equals(idPosition))
                .findFirst()
                .get();
        position.setStartDate(LocalDateTime.parse(request.getParameter("dateStart")).toLocalDate());
        position.setEndDate(LocalDateTime.parse(request.getParameter("dateEnd")).toLocalDate());
        if (sectionType.equals(SectionType.EXPERIENCE)) {
            String description = request.getParameter("description").lines()
                    .map(String::trim)
                    .filter(s -> s.length() > 0)
                    .collect(Collectors.joining("\n"));
            position.setTitle(request.getParameter("position"));
            position.setDescription(description);
        }
        storage.update(resume);
    }
}