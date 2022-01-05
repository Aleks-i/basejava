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
        String uuid = request.getParameter("uuid");
        String idOrganization = request.getParameter("idOrganization");
        String action = request.getParameter("action");

        switch (action) {
            case "editExperienceOrganization" -> {
                Organization organizationExperience = getOrganization(SectionType.EXPERIENCE, storage.get(uuid), idOrganization);
                organizationExperience.setUuid(uuid);
                request.setAttribute("organization", organizationExperience);
            }
            case "editEducationOrganization" -> {
                Organization organizationEducation = getOrganization(SectionType.EDUCATION, storage.get(uuid), idOrganization);
                organizationEducation.setUuid(uuid);
                request.setAttribute("organization", organizationEducation);
            }
        }
        request.getRequestDispatcher("editExperienceOrganization".equals(action) ? "WEB-INF/jsp/editexperiencesection.jsp" : "WEB-INF/jsp/editeducationsection.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String idOrganization = request.getParameter("idOrganization");
        if (uuid.isEmpty()) {
            throw new EmptyFieldException("Enter and save the name");
        }
        Resume resume = storage.get(uuid);

        String action = request.getParameter("action");
        switch (action) {
            case "newExperienceOrganization" -> {
                setNewOrganizationSection(resume, SectionType.EXPERIENCE, request);
                response.sendRedirect("resume?uuid=" + uuid + "&action=edit");
            }
            case "newEducationOrganization" -> {
                setNewOrganizationSection(resume, SectionType.EDUCATION, request);
                response.sendRedirect("resume?uuid=" + uuid + "&action=edit");
            }
            case "editExperienceOrganization" -> {
                setEditOrganizationSection(resume, SectionType.EXPERIENCE, request);
                response.sendRedirect("organization?uuid=" + uuid + "&idOrganization=" + idOrganization + "&action=editExperienceOrganization");
            }
            case "editEducationOrganization" -> {
                setEditOrganizationSection(resume, SectionType.EDUCATION, request);
                response.sendRedirect("organization?uuid=" + uuid + "&idOrganization=" + idOrganization + "&action=editEducationOrganization");
            }
            case "newExperiencePosition" -> {
                addNewPosition(resume, SectionType.EXPERIENCE, request);
                response.sendRedirect("organization?uuid=" + uuid + "&idOrganization=" + idOrganization + "&action=editExperienceOrganization");
            }
            case "newEducationPosition" -> {
                addNewPosition(resume, SectionType.EDUCATION, request);
                response.sendRedirect("organization?uuid=" + uuid + "&idOrganization=" + idOrganization + "&action=editEducationOrganization");
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }

    private void setNewOrganizationSection(Resume resume, SectionType sectionType, HttpServletRequest request) {
        Organization organization = new Organization(request.getParameter("nameOrganization"), request.getParameter("homePage"),
                new Organization.Position(LocalDateTime.parse(request.getParameter("dateStart")).toLocalDate(),
                        LocalDateTime.parse(request.getParameter("dateEnd")).toLocalDate(),
                        request.getParameter("title"), request.getParameter("description")));
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
        Organization organization = getOrganization(sectionType, resume, idOrganization);
        organization.setHomePage(new Link(request.getParameter("nameOrganization"), request.getParameter("homePage")));
        Organization.Position position = getPosition(sectionType, resume, idOrganization, idPosition);
        position.setStartDate(LocalDateTime.parse(request.getParameter("dateStart")).toLocalDate());
        position.setEndDate(LocalDateTime.parse(request.getParameter("dateEnd")).toLocalDate());
        position.setTitle(request.getParameter("title"));
        if (sectionType.equals(SectionType.EXPERIENCE)) {
            String description = request.getParameter("description").lines()
                    .map(String::trim)
                    .filter(s -> s.length() > 0)
                    .collect(Collectors.joining("\n"));
            position.setDescription(description);
        }
        storage.update(resume);
    }

    private void addNewPosition(Resume resume, SectionType sectionType, HttpServletRequest request) {
        String idOrganization = request.getParameter("idOrganization");
        Organization.Position position = new Organization.Position(
                LocalDateTime.parse(request.getParameter("dateStart")).toLocalDate(),
                LocalDateTime.parse(request.getParameter("dateEnd")).toLocalDate(),
                request.getParameter("title"),
                request.getParameter("description")
        );
        getOrganization(sectionType, resume, idOrganization).getPositions().add(0, position);
        storage.update(resume);
    }

    private Organization getOrganization(SectionType sectionType, Resume resume, String idOrganization) {
        return ((OrganizationSection) resume.getSections().get(sectionType)).getOrganizations().stream()
                .filter(o -> o.getId().equals(idOrganization))
                .findFirst()
                .get();
    }

    private Organization.Position getPosition(SectionType sectionType, Resume resume, String idOrganization, String idPosition) {
        return getOrganization(sectionType, resume, idOrganization).getPositions().stream()
                .filter(p -> p.getId().equals(idPosition))
                .findFirst()
                .get();
    }
}