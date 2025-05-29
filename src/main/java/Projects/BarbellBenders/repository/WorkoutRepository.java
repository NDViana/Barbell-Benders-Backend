package Projects.BarbellBenders.repository;

import Projects.BarbellBenders.model.User;
import Projects.BarbellBenders.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);
}
