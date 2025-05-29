package Projects.BarbellBenders.controller;

import Projects.BarbellBenders.dto.WorkoutRequest;
import Projects.BarbellBenders.model.User;
import Projects.BarbellBenders.model.Workout;
import Projects.BarbellBenders.repository.UserRepository;
import Projects.BarbellBenders.repository.WorkoutRepository;
import Projects.BarbellBenders.service.WorkoutService;
import Projects.BarbellBenders.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@SecurityRequirement(name = "bearerAuth")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WorkoutService workoutService;

    @Operation(summary = "Add a new workout")
    @PostMapping("/addWorkout")
    public String addWorkout(@RequestBody WorkoutRequest request, HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        String token = authHeader.substring(7); // remove "Bearer "
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username).orElseThrow();

        Workout workout = new Workout(
                request.exercise,
                request.sets,
                request.reps,
                request.weight,
                request.dateTime,
                user
        );

        workoutRepository.save(workout);
        return "Workout added successfully";
    }

    @Operation(summary = "List all your workouts")
    @GetMapping("/myWorkouts")
    public List<Workout> getUserWorkouts(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username).orElseThrow();

        return workoutRepository.findByUser(user);
    }

    @Operation(summary = "Get a specific workout by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long id, Authentication auth) {
        return workoutService.getWorkoutById(id, auth.getName());
    }

    @Operation(summary = "Change an existing workout")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout, Authentication auth) {
        return workoutService.updateWorkout(id, updatedWorkout, auth.getName());
    }

    @Operation(summary = "Delete an existing workout")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id, Authentication auth) {
        return workoutService.deleteWorkout(id, auth.getName());
    }


}
