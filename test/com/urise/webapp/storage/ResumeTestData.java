package com.urise.webapp.storage;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.util.List;
import java.util.Set;

public class ResumeTestData {

    public static void main(String[] args) {
        System.out.println(TEMPLATE_TEST_RESUME);
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
    public static final Resume TEMPLATE_TEST_RESUME = new Resume("First Resume");

    static {
        TEMPLATE_TEST_RESUME.addContactData(ContactType.PHONENUMBER, "+7(921) 855-0482");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.SKYPE, "skype:grigory.kislin");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.EMAIL, "gkislin@yandex.ru");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.PROFILE, "https://www.linkedin.com/in/gkislin",
                "https://github.com/gkislin", "https://stackoverflow.com/users/548473");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.URL, "http://gkislin.ru/");

        //text sections
        TEMPLATE_TEST_RESUME.addTextSection(SectionType.OBJECTIVE,
                List.of("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        TEMPLATE_TEST_RESUME.addTextSection(SectionType.PERSONAL,
                List.of("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );

        //text marker section
        TEMPLATE_TEST_RESUME.addMarkerSection(SectionType.ACHIEVEMENT, Set.of(
                List.of(
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                        "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".",
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."
                ),
                List.of(
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,",
                        "DuoSecurity, Google Authenticator, Jira, Zendesk."
                ),
                List.of(
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM,",
                        "CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO",
                        "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."
                ),
                List.of(
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT",
                        "(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."
                ),
                List.of(
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура,",
                        "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios.",
                        "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)."
                ),
                List.of(
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,",
                        "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
                )
        ));

        TEMPLATE_TEST_RESUME.addMarkerSection(SectionType.QUALIFICATIONS, Set.of(
                List.of(
                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"
                ),
                List.of(
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce"
                ),
                List.of(
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"
                ),
                List.of(
                        "MySQL, SQLite, MS SQL, HSQLDB"
                ),
                List.of(
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,"
                ),
                List.of(
                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"
                ),
                List.of(
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA",
                        "(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit,",
                        "Selenium (htmlelements)."
                ),
                List.of(
                        "Python: Django."
                ),
                List.of(
                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"
                ),
                List.of(
                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"
                ),
                List.of(
                        "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX,",
                        "JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT."
                ),
                List.of(
                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,"
                ),
                List.of(
                        "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita,",
                        "pgBouncer."
                ),
                List.of(
                        "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML,",
                        "функционального программирования"
                ),
                List.of(
                        "Родной русский, английский \"upper intermediate\""
                )
        ));

        //url link section expreience
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("http://javaops.ru/"),
                "10/2013 - Сейчас",
                "Автор проекта.",
                List.of("Создание, организация и проведение Java онлайн проектов и стажировок.")
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("https://www.wrike.com/"),
                "10/2014 - 01/2016",
                "Старший разработчик (backend)",
                List.of(
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring,",
                        "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2,",
                        "JWT SSO."
                )
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("http://ritcenter.ru/"),
                "04/2012 - 10/2014",
                "Java архитектор",
                List.of(
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование,",
                        "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),",
                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS,",
                        "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco",
                        "JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache",
                        "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell",
                        "remote scripting via ssh tunnels, PL/Python"
                )
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("http://www.luxoft.ru/"),
                "12/2010 - 04/2012",
                "Ведущий программист",
                List.of(
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,",
                        "Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для",
                        "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring,",
                        "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
                )
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("https://www.yota.ru/"),
                "06/2008 - 12/2010",
                "Ведущий специалист",
                List.of(
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J,",
                        "EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и",
                        "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"
                )
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("http://enkata.com/"),
                "03/2007 - 06/2008",
                "Разработчик ПО",
                List.of(
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного",
                        "J2EE приложения (OLAP, Data mining)."
                )
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("https://www.siemens.com/ru/ru/home.html"),
                "01/2005 - 02/2007",
                "Разработчик ПО",
                List.of(
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной",
                        "IN платформе Siemens @vantage (Java, Unix)."
                )
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EXPERIENCE,
                new String("http://www.alcatel.ru/"),
                "09/1997 - 01/2005",
                "Инженер по аппаратному и программному тестированию",
                List.of(
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."
                )
        );

        //url link section expreience
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("https://www.coursera.org/course/progfun"),
                "03/2013 - 05/2013",
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                null
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                "03/2011 - 04/2011",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("http://www.siemens.ru/"),
                "01/2005 - 04/2005",
                "3 месяца обучения мобильным IN сетям (Берлин)",
                null
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("http://www.alcatel.ru/"),
                "09/1997 - 03/1998",
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                null
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("http://www.ifmo.ru/"),
                "09/1993 - 07/1996",
                "Аспирантура (программист С, С++)"
                , null
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("http://www.ifmo.ru/"),
                "09/1987 - 07/1993",
                "Инженер (программист Fortran, C)",
                null
        );
        TEMPLATE_TEST_RESUME.addUrlLinkSection(SectionType.EDUCATION,
                new String("http://www.school.mipt.ru/"),
                "09/1984 - 06/1987",
                "Закончил с отличием",
                null
        );
    }

    static {
        List<Resume> allResume = new java.util.ArrayList<>(List.of(RESUME_SAVED, RESUME_UPDATED, RESUME_NOT_EXIST));
        allResume.addAll(EXPECTED_RESUMES);
        allResume.forEach(r -> {
            r.setContactData(TEMPLATE_TEST_RESUME.getContactData());
            r.setSectionData(TEMPLATE_TEST_RESUME.getSectionData());
        });
    }
}