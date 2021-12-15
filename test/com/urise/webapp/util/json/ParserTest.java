package com.urise.webapp.util.json;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.ResumeTestData.RESUME_1;

public class ParserTest {
    @Test
    public void testResume() {
        String json = Parser.write(RESUME_1);
        System.out.println(json);
        Resume resume = Parser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() {
        AbstractSection section1 = new TextSection("Objective1");
        String json = Parser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = Parser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}