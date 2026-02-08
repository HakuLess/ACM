package leetcode.contest.c488

class SolutionB {
    fun mergeAdjacent(nums: IntArray): List<Long> {

        val stack = ArrayDeque<Long>()

        for (x in nums) {
            stack.addLast(x.toLong())

            while (stack.size >= 2) {
                val last = stack.removeLast()
                val secondLast = stack.removeLast()

                if (last == secondLast) {
                    stack.addLast(last + secondLast)
                } else {
                    stack.addLast(secondLast)
                    stack.addLast(last)
                    break
                }
            }
        }

        return stack.toList()
    }
}