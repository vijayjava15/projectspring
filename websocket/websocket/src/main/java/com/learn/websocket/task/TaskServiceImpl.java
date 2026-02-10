package com.learn.websocket.task;

import com.learn.websocket.exception.ResponseUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Object create(Task task) {
        if (task == null) {
            return ResponseUtility.BADREQUEST(null, "task payload is required");
        }
        if (StringUtils.isBlank(task.getTitle())) {
            return ResponseUtility.BADREQUEST(null, "title should not be blank");
        }
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.NEW);
        }
        if (task.getPriority() == null) {
            task.setPriority(TaskPriority.MEDIUM);
        }
        if (task.getProgressPercent() != null) {
            String progressError = validateProgress(task.getProgressPercent());
            if (progressError != null) {
                return ResponseUtility.BADREQUEST(null, progressError);
            }
        }
        String dateError = validateDates(task.getStartDate(), task.getEndDate(), task.getDueDate());
        if (dateError != null) {
            return ResponseUtility.BADREQUEST(null, dateError);
        }
        if (task.getParentTaskId() != null) {
            if (!taskRepository.existsById(task.getParentTaskId())) {
                return ResponseUtility.BADREQUEST(null, "parent task not found");
            }
        }

        Task saved = taskRepository.save(task);
        return ResponseUtility.OK(saved, "task saved successfully");
    }

    @Override
    public Object update(Long id, Task task) {
        if (id == null) {
            return ResponseUtility.BADREQUEST(null, "id is required");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "task not found");
        }
        if (task == null) {
            return ResponseUtility.BADREQUEST(null, "task payload is required");
        }

        Task existing = optionalTask.get();

        if (StringUtils.isNotBlank(task.getTitle())) {
            existing.setTitle(task.getTitle());
        }
        if (StringUtils.isNotBlank(task.getDescription())) {
            existing.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            existing.setStatus(task.getStatus());
        }
        if (task.getPriority() != null) {
            existing.setPriority(task.getPriority());
        }
        if (task.getStartDate() != null) {
            existing.setStartDate(task.getStartDate());
        }
        if (task.getEndDate() != null) {
            existing.setEndDate(task.getEndDate());
        }
        if (task.getDueDate() != null) {
            existing.setDueDate(task.getDueDate());
        }
        if (task.getProgressPercent() != null) {
            String progressError = validateProgress(task.getProgressPercent());
            if (progressError != null) {
                return ResponseUtility.BADREQUEST(null, progressError);
            }
            existing.setProgressPercent(task.getProgressPercent());
        }
        if (task.getParentTaskId() != null) {
            if (task.getParentTaskId().equals(id)) {
                return ResponseUtility.BADREQUEST(null, "parent task cannot be the same as the task");
            }
            if (!taskRepository.existsById(task.getParentTaskId())) {
                return ResponseUtility.BADREQUEST(null, "parent task not found");
            }
            existing.setParentTaskId(task.getParentTaskId());
        }
        if (task.getAssignedUserId() != null) {
            existing.setAssignedUserId(task.getAssignedUserId());
        }

        String dateError = validateDates(existing.getStartDate(), existing.getEndDate(), existing.getDueDate());
        if (dateError != null) {
            return ResponseUtility.BADREQUEST(null, dateError);
        }

        Task saved = taskRepository.save(existing);
        return ResponseUtility.OK(saved, "task updated successfully");
    }

    @Override
    public Object getById(Long id) {
        if (id == null) {
            return ResponseUtility.BADREQUEST(null, "id is required");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "task not found");
        }
        return ResponseUtility.OK(optionalTask.get(), "success");
    }

    @Override
    public Object getAll() {
        return ResponseUtility.OK(taskRepository.findAll(), "success");
    }

    @Override
    public Object delete(Long id) {
        if (id == null) {
            return ResponseUtility.BADREQUEST(null, "id is required");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "task not found");
        }
        taskRepository.deleteById(id);
        return ResponseUtility.OK(null, "task deleted successfully");
    }

    private String validateProgress(Integer progressPercent) {
        if (progressPercent < 0 || progressPercent > 100) {
            return "progress percent must be between 0 and 100";
        }
        return null;
    }

    private String validateDates(Date startDate, Date endDate, Date dueDate) {
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            return "start date should not be after end date";
        }
        if (startDate != null && dueDate != null && dueDate.before(startDate)) {
            return "due date should not be before start date";
        }
        return null;
    }
}
