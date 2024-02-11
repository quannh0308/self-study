package com.quannh.guidetocompetitiveprogramming.chatper4sortingandsearching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input: Given a list of task with deadline 'd'. Finishing a task at time 'x' will give 'd-x' points
 * Output: Output maximum points / scores
 * Solution: For each and every 2 tasks, as the deadlines and durations are fixed, 
 * doing the tasks with shorter duration will always yeild more poits.
 */
public class TaskScheduling {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>() {{
            add(new Task(4, 2));
            add(new Task(3, 10));
            add(new Task(2, 8));
            add(new Task(4, 15));
        }};

        Collections.sort(tasks);
        int currentTime = 0;
        int score = 0;

        for (Task task : tasks) {
            currentTime += task.duration;
            score += task.deadline - currentTime;
        }

        System.out.println("Maximum score: " + score);
    }
}

class Task implements Comparable<Task> {
    public int duration;
    public int deadline;

    public Task(int duration, int deadline) {
        this.duration = duration;
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Task other) {
        return this.duration - other.duration;
    }
}
