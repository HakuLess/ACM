package leetcode.contest.b60

import utils.NTreeNode
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LockingTree(parent: IntArray) {

    val n = parent.size

    val nodeList = Array<NTreeNode>(n) { i -> NTreeNode(i) }

    init {
        for (i in parent.indices) {
            if (parent[i] != -1) {
                nodeList[parent[i]].children.add(nodeList[i])
                nodeList[i].parent = nodeList[parent[i]]
            }
        }
    }

    val lockedMap = HashMap<NTreeNode, Int>()

    fun lock(num: Int, user: Int): Boolean {
        if (lockedMap.getOrDefault(nodeList[num], -1) == -1) {
            lockedMap[nodeList[num]] = user
            return true
        }
        return false
    }

    fun unlock(num: Int, user: Int): Boolean {
        if (lockedMap.getOrDefault(nodeList[num], -1) == user) {
            lockedMap.remove(nodeList[num])
            return true
        }
        return false
    }

    fun upgrade(num: Int, user: Int): Boolean {
        val cur = nodeList[num]
        if (cur in lockedMap) return false
        var parent = cur.parent
        while (parent != null) {
            if (parent in lockedMap) return false
            parent = parent.parent
        }

        val unLock = ArrayList<NTreeNode>()
        val queue: Queue<NTreeNode> = LinkedList()
        queue.add(cur)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                node.children.forEach {
                    queue.offer(it)
                    if (it in lockedMap) {
                        unLock.add(it)
                    }
                }
            }
        }
        if (unLock.isEmpty()) return false
        lockedMap[cur] = user
        unLock.forEach {
            lockedMap.remove(it)
        }
        return true
    }

}

/**
 * Your LockingTree object will be instantiated and called as such:
 * var obj = LockingTree(parent)
 * var param_1 = obj.lock(num,user)
 * var param_2 = obj.unlock(num,user)
 * var param_3 = obj.upgrade(num,user)
 */