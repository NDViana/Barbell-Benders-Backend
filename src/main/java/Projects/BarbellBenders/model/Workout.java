package Projects.BarbellBenders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String exercise;
    @Setter
    private int sets;
    @Setter
    private int reps;
    @Setter
    private double weight;

    @Setter
    private LocalDateTime dateTime;

    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Workout() {

    }

    public Workout(String exercise, int sets, int reps, double weight, LocalDateTime dateTime, User user) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.dateTime = dateTime;
        this.user = user;
    }


}
