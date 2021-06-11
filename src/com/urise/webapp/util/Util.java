package com.urise.webapp.util;

import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    private Util() {
    }

    public static List<Resume> getSortedResumeList(List<Resume> resumes) {
        return resumes.stream()
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }
}
