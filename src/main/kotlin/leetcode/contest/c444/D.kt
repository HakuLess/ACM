package leetcode.contest.c444

import java.util.*

class SolutionD {
    fun minimumPairRemoval(nums: IntArray): Int {
        val n = nums.size

        val nodes = LinkedList<Node>()
        for (i in nums.indices) {
            nodes.add(Node(nums[i].toLong(), null, null, i, i))
        }
        for (i in 0 until n - 1) {
            nodes[i].next = nodes[i + 1]
            nodes[i + 1].prev = nodes[i]
        }

        // 需要抹平反向的次数
        var cnt = 0
        var cur: Node? = nodes[0]
        while (cur?.next != null) {
            if (cur.value > cur.next!!.value) {
                cnt++
            }
            cur = cur.next
        }
        if (cnt == 0) return 0

        // 和最小，最左边
        val heap = PriorityQueue<TripleSum>(compareBy({ it.sum }, { it.left.start }))
        cur = nodes[0]
        while (cur?.next != null) {
            val nextNode = cur.next!!
            heap.add(TripleSum(cur.value + nextNode.value, cur, nextNode))
            cur = nextNode
        }

        var ans = 0
        while (heap.isNotEmpty() && cnt > 0) {
            val (sumVal, left, right) = heap.poll()

            // 已失效的 直接跳过
            if (left.next != right || right.prev != left) continue

            val prevNode = left.prev
            val nextNode = right.next

            if (prevNode != null && prevNode.value > left.value) {
                cnt--
            }
            if (left.value > right.value) {
                cnt--
            }
            if (nextNode != null && right.value > nextNode.value) {
                cnt--
            }

            // 合并后，新节点插入
            val newNode = Node(
                sumVal,
                prevNode,
                nextNode,
                left.start,
                right.end,
            )

            if (prevNode != null) {
                prevNode.next = newNode
            } else {
                if (nodes.isNotEmpty() && nodes[0] == left) {
                    nodes[0] = newNode
                }
            }
            if (nextNode != null) {
                nextNode.prev = newNode
            }
            if (prevNode != null && prevNode.value > newNode.value) {
                cnt++
            }
            if (nextNode != null && newNode.value > nextNode.value) {
                cnt++
            }

            if (prevNode != null) {
                heap.add(TripleSum(prevNode.value + newNode.value, prevNode, newNode))
            }
            if (nextNode != null) {
                heap.add(TripleSum(newNode.value + nextNode.value, newNode, nextNode))
            }

            ans++

            if (cnt == 0) return ans
        }

        return ans
    }
}

// 总和 + 左节点 + 右节点
data class TripleSum(
    val sum: Long,
    val left: Node,
    val right: Node,
)

data class Node(
    var value: Long,
    var prev: Node?,
    var next: Node?,
    val start: Int,
    val end: Int,
)