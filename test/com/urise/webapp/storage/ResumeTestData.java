package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        System.out.println(RESUME);
    }
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";
    public static final String UUID_SAVED = "uuidsaved";

    public static final String UUID_NOT_EXIST = "dummy";
    public static final String FULL_NAME_1 = "Petrov";
    public static final String FULL_NAME_2 = "Sidorov";
    public static final String FULL_NAME_3 = "Ivanov";
    public static final String FULL_NAME_4 = "Petrov";
    public static final String FULL_NAME_SAVED = "Name Saved";
    public static final String FULL_NAME_UPDATED = "Name Updated";

    public static final String FULL_NAME_NOT_EXIST = "";
    public static final int EMPTY_STORAGE_SIZE = 0;
    public static final int STORAGE_SIZE_EXPECTED = 4;
    public static final int STORAGE_SIZE_AFTER_DELETE = 3;

    public static final int STORAGE_SIZE_AFTER_SAVE = 5;
    public static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
    public static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
    public static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    public static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);
    public static final Resume RESUME_SAVED = new Resume(UUID_SAVED, FULL_NAME_SAVED);
    public static final Resume RESUME_UPDATED = new Resume(UUID_1, FULL_NAME_UPDATED);

    public static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST, FULL_NAME_NOT_EXIST);

    public static final List<Resume> EXPECTED_RESUMES = List.of(RESUME_3, RESUME_1, RESUME_4, RESUME_2);
    public static final Resume RESUME = new Resume("First Resume");

    static {
        RESUME.addContactData(Resume.ContactType.PHONENUMBER, "+7(921) 855-0482");
        RESUME.addContactData(Resume.ContactType.SKYPE, "skype:grigory.kislin");
        RESUME.addContactData(Resume.ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME.addContactData(Resume.ContactType.PROFILE, "https://www.linkedin.com/in/gkislin",
                "https://github.com/gkislin", "https://stackoverflow.com/users/548473");
        RESUME.addContactData(Resume.ContactType.URL, "http://gkislin.ru/");

        //text sections
        Resume.TextSection textSectionObjective = RESUME.new TextSection(
                List.of("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        Resume.TextSection textSectionPersonal = RESUME.new TextSection(
                List.of("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );

        //text marker section
        Resume.TextSection textSectionAchievement1 = RESUME.new TextSection(
                List.of(
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                        "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".",
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."
                )
        );
        Resume.TextSection textSectionAchievement2 = RESUME.new TextSection(
                List.of(
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,",
                        "DuoSecurity, Google Authenticator, Jira, Zendesk."
                )
        );
        Resume.TextSection textSectionAchievement3 = RESUME.new TextSection(
                List.of(
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM,",
                        "CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO",
                        "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."
                )
        );
        Resume.TextSection textSectionAchievement4 = RESUME.new TextSection(
                List.of(
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT",
                        "(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."
                )
        );
        Resume.TextSection textSectionAchievement5 = RESUME.new TextSection(
                List.of(
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура,",
                        "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios.",
                        "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)."
                )
        );
        Resume.TextSection textSectionAchievement6 = RESUME.new TextSection(
                List.of(
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,",
                        "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
                )
        );

        Resume.TextSection textSectionQualifications1 = RESUME.new TextSection(
                List.of(
                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"
                )
        );
        Resume.TextSection textSectionQualifications2 = RESUME.new TextSection(
                List.of(
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce"
                )
        );
        Resume.TextSection textSectionQualifications3 = RESUME.new TextSection(
                List.of(
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"
                )
        );
        Resume.TextSection textSectionQualifications4 = RESUME.new TextSection(
                List.of(
                        "MySQL, SQLite, MS SQL, HSQLDB"
                )
        );
        Resume.TextSection textSectionQualifications5 = RESUME.new TextSection(
                List.of(
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,"
                )
        );
        Resume.TextSection textSectionQualifications6 = RESUME.new TextSection(
                List.of(
                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"
                )
        );
        Resume.TextSection textSectionQualifications7 = RESUME.new TextSection(
                List.of(
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA",
                        "(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit,",
                        "Selenium (htmlelements)."
                )
        );
        Resume.TextSection textSectionQualifications8 = RESUME.new TextSection(
                List.of(
                        "Python: Django."
                )
        );
        Resume.TextSection textSectionQualifications9 = RESUME.new TextSection(
                List.of(
                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"
                )
        );
        Resume.TextSection textSectionQualifications10 = RESUME.new TextSection(
                List.of(
                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"
                )
        );
        Resume.TextSection textSectionQualifications11 = RESUME.new TextSection(
                List.of(
                        "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX,",
                        "JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT."
                )
        );
        Resume.TextSection textSectionQualifications12 = RESUME.new TextSection(
                List.of(
                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,"
                )
        );
        Resume.TextSection textSectionQualifications13 = RESUME.new TextSection(
                List.of(
                        "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita,",
                        "pgBouncer."
                )
        );
        Resume.TextSection textSectionQualifications14 = RESUME.new TextSection(
                List.of(
                        "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML,",
                        "функционального программирования"
                )
        );
        Resume.TextSection textSectionQualifications15 = RESUME.new TextSection(
                List.of(
                        "Родной русский, английский \"upper intermediate\""
                )
        );

        Resume.MarkerTextSection markerTextSectionAchievement = RESUME.new MarkerTextSection(
                List.of(textSectionAchievement1, textSectionAchievement2, textSectionAchievement3, textSectionAchievement4,
                        textSectionAchievement5, textSectionAchievement6)
        );

        Resume.MarkerTextSection markerTextSectionQualifications = RESUME.new MarkerTextSection(
                List.of(textSectionQualifications1, textSectionQualifications2, textSectionQualifications3, textSectionQualifications4,
                        textSectionQualifications5, textSectionQualifications6, textSectionQualifications7, textSectionQualifications8,
                        textSectionQualifications9, textSectionQualifications10, textSectionQualifications11, textSectionQualifications12,
                        textSectionQualifications13, textSectionQualifications14, textSectionQualifications15)
        );

        //text url link section
        Resume.UrlLinkSection sectionExperience = RESUME.new UrlLinkSection();
        Resume.UrlLinkSection.OrganizationSection javaOnlineProjectsOrganization = null;
        try {
            javaOnlineProjectsOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://javaops.ru/"),
                    "10/2013 - Сейчас",
                    "Автор проекта.",
                    RESUME.new TextSection(List.of("Создание, организация и проведение Java онлайн проектов и стажировок."))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection wrikeOrganization = null;
        try {
            wrikeOrganization = sectionExperience.new OrganizationSection(
                    new URL("https://www.wrike.com/"),
                    "10/2014 - 01/2016",
                    "Старший разработчик (backend)",
                    RESUME.new TextSection(List.of(
                            "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring,",
                            "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2,",
                            "JWT SSO."
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection RITCenterOrganization = null;
        try {
            RITCenterOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://ritcenter.ru/"),
                    "04/2012 - 10/2014",
                    "Java архитектор",
                    RESUME.new TextSection(List.of(
                            "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование,",
                            "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),",
                            "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS,",
                            "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco",
                            "JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache",
                            "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell",
                            "remote scripting via ssh tunnels, PL/Python"
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection luxoftOrganization = null;
        try {
            luxoftOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://www.luxoft.ru/"),
                    "12/2010 - 04/2012",
                    "Ведущий программист",
                    RESUME.new TextSection(List.of(
                            "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,",
                            "Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для",
                            "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring,",
                            "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection yotaOrganization = null;
        try {
            yotaOrganization = sectionExperience.new OrganizationSection(
                    new URL("https://www.yota.ru/"),
                    "06/2008 - 12/2010",
                    "Ведущий специалист",
                    RESUME.new TextSection(List.of(
                            "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J,",
                            "EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и",
                            "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection enkataOrganization = null;
        try {
            enkataOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://enkata.com/"),
                    "03/2007 - 06/2008",
                    "Разработчик ПО",
                    RESUME.new TextSection(List.of(
                            "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного",
                            "J2EE приложения (OLAP, Data mining)."
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection siemensOrganization = null;
        try {
            siemensOrganization = sectionExperience.new OrganizationSection(
                    new URL("https://www.siemens.com/ru/ru/home.html"),
                    "01/2005 - 02/2007",
                    "Разработчик ПО",
                    RESUME.new TextSection(List.of(
                            "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной",
                            "IN платформе Siemens @vantage (Java, Unix)."
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection alcatelOrganization = null;
        try {
            alcatelOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://www.alcatel.ru/"),
                    "09/1997 - 01/2005",
                    "Инженер по аппаратному и программному тестированию",
                    RESUME.new TextSection(List.of(
                            "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."
                    ))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        sectionExperience.addOrganizations(List.of(javaOnlineProjectsOrganization, wrikeOrganization, RITCenterOrganization,
                luxoftOrganization, yotaOrganization, enkataOrganization, siemensOrganization, alcatelOrganization));

        Resume.UrlLinkSection sectionEducation = RESUME.new UrlLinkSection();
        Resume.UrlLinkSection.OrganizationSection courseraOrganization = null;
        try {
            courseraOrganization = sectionExperience.new OrganizationSection(
                    new URL("https://www.coursera.org/course/progfun"),
                    "03/2013 - 05/2013",
                    "\"Functional Programming Principles in Scala\" by Martin Odersky"
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection luxofttrainingOrganization = null;
        try {
            luxofttrainingOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                    "03/2011 - 04/2011",
                    "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection siemensOrganizationEducation = null;
        try {
            siemensOrganizationEducation = sectionExperience.new OrganizationSection(
                    new URL("http://www.siemens.ru/"),
                    "01/2005 - 04/2005",
                    "3 месяца обучения мобильным IN сетям (Берлин)"
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection alcatelOrganizationEducation = null;
        try {
            alcatelOrganizationEducation = sectionExperience.new OrganizationSection(
                    new URL("http://www.alcatel.ru/"),
                    "09/1997 - 03/1998",
                    "6 месяцев обучения цифровым телефонным сетям (Москва)"
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection ifmoOrganization1 = null;
        try {
            ifmoOrganization1 = sectionExperience.new OrganizationSection(
                    new URL("http://www.ifmo.ru/"),
                    "09/1993 - 07/1996",
                    "Аспирантура (программист С, С++)"
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection ifmoOrganization2 = null;
        try {
            ifmoOrganization2 = sectionExperience.new OrganizationSection(
                    new URL("http://www.ifmo.ru/"),
                    "09/1987 - 07/1993",
                    "Инженер (программист Fortran, C)"
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Resume.UrlLinkSection.OrganizationSection miptOrganization = null;
        try {
            miptOrganization = sectionExperience.new OrganizationSection(
                    new URL("http://www.school.mipt.ru/"),
                    "09/1984 - 06/1987",
                    "Закончил с отличием"
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        sectionEducation.addOrganizations(List.of(courseraOrganization, luxofttrainingOrganization, siemensOrganizationEducation,
                alcatelOrganizationEducation, ifmoOrganization1, ifmoOrganization2, miptOrganization));

        RESUME.getSectionData().put(SectionType.OBJECTIVE, textSectionObjective);
        RESUME.getSectionData().put(SectionType.PERSONAL, textSectionPersonal);
        RESUME.getSectionData().put(SectionType.ACHIEVEMENT, markerTextSectionAchievement);
        RESUME.getSectionData().put(SectionType.QUALIFICATIONS, markerTextSectionQualifications);
        RESUME.getSectionData().put(SectionType.EXPERIENCE, sectionExperience);
        RESUME.getSectionData().put(SectionType.EDUCATION, sectionEducation);
    }

    static {
        List<Resume> allResume = new java.util.ArrayList<>(List.of(RESUME_SAVED, RESUME_UPDATED, RESUME_NOT_EXIST));
        allResume.addAll(EXPECTED_RESUMES);
        allResume.forEach(r -> {
            r.setContactData(RESUME.getContactData());
            r.setSectionData(RESUME.getSectionData());
        });
    }
}
