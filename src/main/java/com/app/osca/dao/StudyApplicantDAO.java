package com.app.osca.dao;

import com.app.osca.mapper.StudyApplicantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudyApplicantDAO {
    private final StudyApplicantMapper studyApplicantMapper;
}
