package leetcode.normal

import java.util.*

class Solution1298 {
    fun maxCandies(
        status: IntArray,
        candies: IntArray,
        keys: Array<IntArray>,
        containedBoxes: Array<IntArray>,
        initialBoxes: IntArray
    ): Int {
        val n = status.size
        val hasBox = BooleanArray(n)
        val hasKey = BooleanArray(n)
        val opened = BooleanArray(n)

        val queue: Queue<Int> = LinkedList<Int>()

        for (box in initialBoxes) {
            hasBox[box] = true
            if (status[box] == 1) queue.offer(box)
        }


        var ans = 0

        while (queue.isNotEmpty()) {
            val box = queue.poll()
            if (opened[box]) continue
            opened[box] = true
            ans += candies[box]

            // 拿到钥匙
            for (key in keys[box]) {
                hasKey[key] = true
                if (hasBox[key] && !opened[key]) {
                    queue.add(key)
                }
            }

            // 拿到盒子
            for (contained in containedBoxes[box]) {
                hasBox[contained] = true
                if (status[contained] == 1 || hasKey[contained]) {
                    queue.add(contained)
                }
            }
        }

        return ans
    }
}