package Projects.BarbellBenders.service;

import Projects.BarbellBenders.model.Workout;
import Projects.BarbellBenders.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public ResponseEntity<?> getWorkoutById(Long id, String username) {
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isEmpty() || !workout.get().getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found or unauthorized");
        }
        return ResponseEntity.ok(workout.get());
    }

    public ResponseEntity<?> updateWorkout(Long id, Workout updatedWorkout, String username) {
        Optional<Workout> existing = workoutRepository.findById(id);
        if (existing.isEmpty() || !existing.get().getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found or unauthorized");
        }

        Workout workout = existing.get();
        workout.setExercise(updatedWorkout.getExercise());
        workout.setSets(updatedWorkout.getSets());
        workout.setReps(updatedWorkout.getReps());
        workout.setWeight(updatedWorkout.getWeight());
        workout.setDateTime(updatedWorkout.getDateTime());

        workoutRepository.save(workout);
        return ResponseEntity.ok("Workout updated successfully");
    }

    public ResponseEntity<?> deleteWorkout(Long id, String username) {
        Optional<Workout> existing = workoutRepository.findById(id);
        if (existing.isEmpty() || !existing.get().getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found or unauthorized");
        }

        workoutRepository.deleteById(id);
        return ResponseEntity.ok("Workout deleted successfully");
    }
}
