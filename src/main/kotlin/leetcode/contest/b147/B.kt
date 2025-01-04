package leetcode.contest.b147

import java.util.*

class TaskManager(tasks: List<List<Int>>) {

    private val taskMap = mutableMapOf<Int, Pair<Int, Int>>()

    private val priorityMap = TreeMap<Int, TreeSet<Int>>(compareByDescending { it })

    init {
        for (task in tasks) {
            val (userId, taskId, priority) = task
            add(userId, taskId, priority)
        }
    }

    fun add(userId: Int, taskId: Int, priority: Int) {
        taskMap[taskId] = userId to priority
        priorityMap.computeIfAbsent(priority) { TreeSet(compareByDescending { it }) }.add(taskId)
    }

    fun edit(taskId: Int, newPriority: Int) {
        val (userId, oldPriority) = taskMap[taskId] ?: return

        // 从旧优先级移除任务
        priorityMap[oldPriority]?.remove(taskId)
        if (priorityMap[oldPriority]?.isEmpty() == true) {
            priorityMap.remove(oldPriority)
        }

        // 更新任务优先级
        taskMap[taskId] = userId to newPriority
        priorityMap.computeIfAbsent(newPriority) { TreeSet(compareByDescending { it }) }.add(taskId)

    }

    fun rmv(taskId: Int) {
        val (_, priority) = taskMap[taskId] ?: return

        // 从优先级列表中移除任务
        priorityMap[priority]?.remove(taskId)
        if (priorityMap[priority]?.isEmpty() == true) {
            priorityMap.remove(priority)
        }

        // 从任务映射中删除
        taskMap.remove(taskId)
    }

    fun execTop(): Int {
        if (priorityMap.isEmpty()) return -1

        // 获取最高优先级及其任务集合
        val (highestPriority, taskSet) = priorityMap.firstEntry()

        // 获取最高优先级的最大 taskId
        val topTaskId = taskSet.first()

        // 获取任务所属用户
        val userId = taskMap[topTaskId]?.first ?: -1

        // 删除任务
        taskSet.remove(topTaskId)
        if (taskSet.isEmpty()) {
            priorityMap.remove(highestPriority)
        }
        taskMap.remove(topTaskId)

        return userId
    }

}

/**
 * Your TaskManager object will be instantiated and called as such:
 * var obj = TaskManager(tasks)
 * obj.add(userId,taskId,priority)
 * obj.edit(taskId,newPriority)
 * obj.rmv(taskId)
 * var param_4 = obj.execTop()
 */