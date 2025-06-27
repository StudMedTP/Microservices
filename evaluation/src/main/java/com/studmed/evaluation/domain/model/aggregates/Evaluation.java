package com.studmed.evaluation.domain.model.aggregates;

import com.studmed.evaluation.domain.model.commands.CreateEvaluationCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Evaluation")
public class Evaluation extends AbstractAggregateRoot<Evaluation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String title;

    @Getter
    private String hospitalName;

    @Setter
    @Getter
    private String courseName;

    @Getter
    private String description;

    @Getter
    private String startDate;

    @Getter
    private String evaluationState;

    @Getter
    private String feedback;

    @Getter
    private String teacherName;

    @Getter
    private String evaluationGrade;


    public Evaluation() {
        this.title = Strings.EMPTY;
        this.hospitalName = Strings.EMPTY;
        this.courseName = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.startDate = Strings.EMPTY;
        this.evaluationState = Strings.EMPTY;
        this.feedback = Strings.EMPTY;
        this.teacherName = Strings.EMPTY;
        this.evaluationGrade = Strings.EMPTY;
    }

    public Evaluation(String title, String hospitalName, String courseName, String description, String startDate, String evaluationState, String feedback, String teacherName, String evaluationGrade) {
        this.title = title;
        this.hospitalName = hospitalName;
        this.courseName = courseName;
        this.description = description;
        this.startDate = startDate;
        this.evaluationState = evaluationState;
        this.feedback = feedback;
        this.teacherName = teacherName;
        this.evaluationGrade = evaluationGrade;
    }

    public Evaluation(CreateEvaluationCommand command){
        this();
        this.title = command.title();
        this.hospitalName = command.hospitalName();
        this.courseName = command.courseName();
        this.description = command.description();
        this.startDate = command.startDate();
        this.evaluationState = command.evaluationState();
        this.feedback = command.feedback();
        this.teacherName = command.teacherName();
        this.evaluationGrade = command.evaluationGrade();
    }

    public Evaluation updateEvaluation(String title, String hospitalName, String courseName, String description, String startDate, String evaluationState, String feedback, String teacherName, String evaluationGrade){
        this.title = title;
        this.hospitalName = hospitalName;
        this.courseName = courseName;
        this.description = description;
        this.startDate = startDate;
        this.evaluationState = evaluationState;
        this.feedback = feedback;
        this.teacherName = teacherName;
        this.evaluationGrade = evaluationGrade;
        return this;
    }
}