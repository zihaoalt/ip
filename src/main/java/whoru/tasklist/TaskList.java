package whoru.tasklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import whoru.exception.InvalidTaskNumberException;
import whoru.exception.WhoruException;
import whoru.task.Task;

/**
 * Stores and manages the tasks in the application.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing a copy of the given tasks.
     *
     * @param tasks Tasks to be stored in the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index Zero-based index of the task to remove.
     * @return Task that was removed.
     * @throws WhoruException If the index is invalid.
     */
    public Task remove(int index) throws WhoruException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Zero-based index of the task to retrieve.
     * @return Task at the specified index.
     * @throws WhoruException If the index is invalid.
     */
    public Task get(int index) throws WhoruException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns an unmodifiable view of the task list.
     *
     * @return Unmodifiable list of tasks.
     */
    public List<Task> asUnmodifiableList() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    private void validateIndex(int index) throws WhoruException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}