package com.makeitworkch10.pacemakers.pelicare.service;
import com.makeitworkch10.pacemakers.pelicare.dto.NewTaskDTO;
import com.makeitworkch10.pacemakers.pelicare.dto.TaskDTO;
import com.makeitworkch10.pacemakers.pelicare.exception.ResourceNotFoundException;
import com.makeitworkch10.pacemakers.pelicare.model.CareCircle;
import com.makeitworkch10.pacemakers.pelicare.model.Task;
import com.makeitworkch10.pacemakers.pelicare.repository.CareCircleRepository;
import com.makeitworkch10.pacemakers.pelicare.repository.TaskRepository;
import com.makeitworkch10.pacemakers.pelicare.service.mappers.TaskDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maaike Feenstra <mk.feenstra@st.hanze.nl><
 * <p>
 * servicelayer for the task entity
 */

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CareCircleRepository careCircleRepository;
    private final TaskDTOMapper taskDTOMapper;

    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskDTOMapper)
                .collect(Collectors.toList());
    }

    public TaskDTO getTask(Long id) throws ResourceNotFoundException {
        return taskRepository.findById(id)
                .map(taskDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task with id not found"
                ));
    }

//    public TaskDTO createTask(Task task) {
//        Long taskId = taskRepository.save(task).getId();
//        Task newTask = taskRepository.getReferenceById(taskId);
//        return new TaskDTO(newTask.getId(), newTask.getTitle(), newTask.getDescription());
//    }

    public TaskDTO saveTask(NewTaskDTO newTaskDTO) {
        Task task = new Task();
        task.setTitle(newTaskDTO.getTitle());
        task.setDescription(newTaskDTO.getDescription());
        task.setCareCircle(careCircleRepository.findById(newTaskDTO.getCareCircleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Care Circle of this new task coul not be found")));
        Long taskId = taskRepository.save(task).getId();
        TaskDTO savedTask = taskRepository.findById(taskId)
                .map(taskDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id not found"));
        return  savedTask;
    }


}
