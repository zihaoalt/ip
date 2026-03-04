package whoru.tasklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import whoru.exception.InvalidTaskNumberException;
import whoru.exception.WhoruException;
import whoru.task.Task;

import static whoru.utils.formatter.formatErrorMessage;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws WhoruException { // wrap to provide more safety check
        validateIndex(index);
        return tasks.remove(index);
    }

    public Task get(int index) throws WhoruException { // wrap to provide more safety check
        validateIndex(index);
        return tasks.get(index);
    }

    public List<Task> asUnmodifiableList() { // to support enhanced for loop syntax
        return Collections.unmodifiableList(tasks);
    }

    public int size() {
        return tasks.size();
    }

    private void validateIndex(int index) throws WhoruException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}