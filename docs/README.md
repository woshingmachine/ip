# TwinBot User Guide

**TwinBot** is a friendly task management chatbot with a conversational GUI. It helps you organize your tasks, set deadlines, and plan events.

## Quick Start

1. Ensure you have JDK 17 installed
2. Download and run the application
3. The TwinBot interface will open with a chat-like window
4. Start typing commands to manage your tasks!

**Tip:** Type `help` at any time to see all available commands.

---

## Features

### 1. Adding a Todo Task

A simple task with just a description. Use this for tasks without a deadline.

**Command:** `todo <description>`

**Example:** 
```
todo Buy groceries
```

**Expected Output:**
```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 task in the list.
```

---

### 2. Adding a Deadline

A task with a deadline date and time.

**Command:** `deadline <description> /by <date time>`

**Supported Date Formats:**
- `yyyy-MM-dd HH:mm` (e.g., `2026-02-28 23:59`)
- `d/M/yyyy HHmm` (e.g., `28/2/2026 2359`)

**Example:**
```
deadline Submit project /by 2026-02-28 23:59
```

**Expected Output:**
```
Got it. I've added this task:
  [D][ ] Submit project (by: Feb 28 2026, 11:59 PM)
Now you have 2 tasks in the list.
```

---

### 3. Adding an Event

A task with a specific start and end date/time.

**Command:** `event <description> /from <start date time> /to <end date time>`

**Note:** Start time must be before the end time.

**Example:**
```
event Team meeting /from 2026-03-01T14:00 /to 2026-03-01T15:00
```

**Expected Output:**
```
Got it. I've added this task:
  [E][ ] Team meeting (from: Mar 1 2026, 2:00 PM to: Mar 1 2026, 3:00 PM)
Now you have 3 tasks in the list.
```

---

### 4. Listing All Tasks

Display all your tasks in a numbered list.

**Command:** `list`

**Example:**
```
list
```

**Expected Output:**
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Submit project (by: Feb 28 2026, 11:59 PM)
3. [E][ ] Team meeting (from: Mar 1 2026, 2:00 PM to: Mar 1 2026, 3:00 PM)
```

**Legend:**
- `[T]` = Todo
- `[D]` = Deadline
- `[E]` = Event
- `[X]` = Completed ✓
- `[ ]` = Not completed

---

### 5. Marking Tasks as Complete

Mark a task as done once you've completed it.

**Command:** `mark <task number>`

**Example:**
```
mark 1
```

**Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] Buy groceries
```

---

### 6. Unmarking Tasks

Mark a completed task as incomplete again.

**Command:** `unmark <task number>`

**Example:**
```
unmark 1
```

**Expected Output:**
```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries
```

---

### 7. Finding Tasks

Search for tasks by keyword. TwinBot will search through all task descriptions.

**Command:** `find <keyword>`

**Example:**
```
find project
```

**Expected Output:**
```
Here are the matching tasks in your list:
1. [D][ ] Submit project (by: Feb 28 2026, 11:59 PM)
```

---

### 8. Tagging Tasks

Add tags to tasks for better organization. Tags are labels like `#important`, `#work`, etc.

**Command:** `tag <task number> <tag>`

**Tag Rules:**
- Must contain only letters, numbers, hyphens, or underscores
- Maximum 50 characters
- Examples: `#work`, `#urgent`, `#project-x`

**Example:**
```
tag 2 #urgent
```

**Expected Output:**
```
Tagged! Here's the task with its new tag:
  [D][ ] Submit project (by: Feb 28 2026, 11:59 PM) #urgent
```

---

### 9. Removing Tags

Remove a tag from a task.

**Command:** `untag <task number> <tag>`

**Example:**
```
untag 2 #urgent
```

**Expected Output:**
```
Untagged! Here's the task:
  [D][ ] Submit project (by: Feb 28 2026, 11:59 PM)
```

---

### 10. Deleting Tasks

Remove a task from your list permanently.

**Command:** `delete <task number>`

**Example:**
```
delete 1
```

**Expected Output:**
```
Noted. I've removed this task:
  [T][X] Buy groceries
Now you have 2 tasks in the list.
```

---

### 11. Getting Help

Display all available commands and their usage.

**Command:** `help`

**Expected Output:**
```
Here are the available commands:
  todo <description>          - Add a todo task
  deadline <desc> /by <date>  - Add a task with a deadline
  event <desc> /from ... /to  - Add an event
  list                        - Show all tasks
  mark <task number>          - Mark a task as done
  unmark <task number>        - Mark a task as not done
  find <keyword>              - Search for tasks
  tag <task number> <tag>     - Add a tag to a task
  untag <task number> <tag>   - Remove a tag from a task
  delete <task number>        - Delete a task
  help                        - Show this help message
  exit                        - Exit the application
```

---

### 12. Exiting the Application

Close TwinBot gracefully.

**Command:** `exit`

---

## Error Handling

TwinBot provides helpful error messages to guide you:

| Error | Solution |
|-------|----------|
| `Unknown command` | Check the command spelling. Type `help` for available commands. |
| `Task number X doesn't exist` | The task number is out of range. Type `list` to see valid task numbers. |
| `Start time must be before end time` | For events, ensure the start time is earlier than the end time. |
| `Please provide a valid tag` | Tags can only contain letters, numbers, `-`, or `_`. Max 50 characters. |
| `Empty command` | You need to provide a command. Type `help` for examples. |

---

## Tips & Tricks

✨ **Pro Tips:**
- Use tags to organize by category (e.g., `#work`, `#personal`, `#deadline-soon`)
- Use `find` to quickly locate tasks related to a project
- Tasks are automatically saved to `data/tasks.txt` after each command
- You can resize the TwinBot window for a better view of your tasks
- Mark tasks as complete as you finish them using `mark`

---

## FAQ

**Q: Where are my tasks saved?**  
A: All tasks are saved automatically in `data/tasks.txt` in the application directory.

**Q: Can I edit a task after creating it?**  
A: Currently, you can delete and recreate a task with the updated information.

**Q: What happens if the date format is wrong?**  
A: TwinBot will display an error message. Use one of the supported date formats listed above.

**Q: Can I have multiple tags on one task?**  
A: You can tag the same task multiple times with different tags using separate `tag` commands.

**Q: Does TwinBot work offline?**  
A: Yes! TwinBot is completely offline and doesn't require an internet connection.
