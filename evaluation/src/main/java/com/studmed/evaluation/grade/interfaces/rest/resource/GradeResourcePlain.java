package com.studmed.evaluation.grade.interfaces.rest.resource;

import java.util.Date;

public record GradeResourcePlain(Long id,
                                 Long value,
                                 String description,
                                 Date createdAt) {}