package leetcode.contest.c418

class SolutionB {
    fun remainingMethods(n: Int, k: Int, invocations: Array<IntArray>): List<Int> {
        val adjList = Array(n) { mutableListOf<Int>() }
        val reverseAdjList = Array(n) { mutableListOf<Int>() }

        for (invocation in invocations) {
            val from = invocation[0]
            val to = invocation[1]
            adjList[from].add(to)
            reverseAdjList[to].add(from)
        }

        val suspiciousMethods = BooleanArray(n) { false }
        fun dfs(node: Int) {
            if (suspiciousMethods[node]) return
            suspiciousMethods[node] = true
            for (next in adjList[node]) {
                dfs(next)
            }
        }

        dfs(k)

        for (i in 0 until n) {
            if (!suspiciousMethods[i]) {
                for (to in adjList[i]) {
                    if (suspiciousMethods[to]) {
                        return (0 until n).toList()
                    }
                }
            }
        }

        val remainingMethods = mutableListOf<Int>()
        for (i in 0 until n) {
            if (!suspiciousMethods[i]) {
                remainingMethods.add(i)
            }
        }

        return remainingMethods
    }
}