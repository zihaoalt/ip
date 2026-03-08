# Whoru User Guide

Whoru is a desktop task tracker application for managing tasks. It is designed for users who prefer typing commands instead of clicking through menus. Whoru helps you keep track of your tasks quickly and efficiently through a Command Line Interface (CLI).

---

## Quick Start

1. Ensure that Java is installed on your computer.
2. Download the project `.jar` file.
3. Place the `.jar` file in the folder you want to use as the home folder for Whoru.
4. Open a terminal in that folder.
5. Run the app with:

   `java -jar whoru.jar`

6. Type your command and press Enter to use the chatbot.

Example:
- `todo read book`
- `list`

---

## Features

### Notes about the command format

- Words in `UPPER_CASE` are parameters to be supplied by the user.  
  Example: in `delete INDEX`, `INDEX` is the task number.
- Items in square brackets are optional.
- Commands should be typed exactly as shown.
- Dates should be entered in `yyyy-MM-dd` format where applicable.
- After each valid command, Whoru will show a response message.
- Changes are saved automatically.

---

### Adding a todo task: `todo`

Adds a todo task.

Format:
`todo DESCRIPTION`

Example:
`todo read software engineering notes`

---

### Adding a deadline task: `deadline`

Adds a task with a deadline.

Format:
`deadline DESCRIPTION /by DATE`

Example:
`deadline submit CS2113 iP /by 2026-03-18`

---

### Adding an event task: `event`

Adds a task with a start and end date.

Format:
`event DESCRIPTION /from START_DATE /to END_DATE`

Example:
`event project meeting /from 2026-03-18 /to 2026-03-20`

---

### Listing all tasks: `list`

Shows all tasks currently stored in Whoru.

Format:
`list`

Example:
`list`

---

### Marking a task as done: `mark`

Marks the specified task as done.

Format:
`mark INDEX`

Example:
`mark 2`

---

### Marking a task as not done: `unmark`

Marks the specified task as not done.

Format:
`unmark INDEX`

Example:
`unmark 2`

---

### Deleting a task: `delete`

Deletes the specified task.

Format:
`delete INDEX`

Example:
`delete 3`

---

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format:
`find KEYWORD`

Example:
`find report`

---

### Exiting the program: `bye`

Exits Whoru.

Format:
`bye`

Example:
`bye`

---

## Command Summary

| Action | Format | Example |
|--------|--------|---------|
| Add todo | `todo DESCRIPTION` | `todo read book` |
| Add deadline | `deadline DESCRIPTION /by DATE` | `deadline submit report /by 2026-03-18` |
| Add event | `event DESCRIPTION /from START_DATE /to END_DATE` | `event meeting /from 2026-03-18 /to 2026-03-20` |
| List tasks | `list` | `list` |
| Mark task | `mark INDEX` | `mark 1` |
| Unmark task | `unmark INDEX` | `unmark 1` |
| Delete task | `delete INDEX` | `delete 2` |
| Find task | `find KEYWORD` | `find exam` |
| Exit | `bye` | `bye` |

---

## FAQ

**Q: Where are my tasks stored?**  
A: Your tasks are stored under /data/Whoru.txt.

**Q: Do I need to save manually?**  
A: No. Whoru saves automatically after changes are made.

**Q: What date format should I use for deadlines and events?**  
A: Use `yyyy-MM-dd`, for example `2026-03-18`.

**Q: What if I type an invalid command?**  
A: Whoru will show an error message and prompt you to try again.