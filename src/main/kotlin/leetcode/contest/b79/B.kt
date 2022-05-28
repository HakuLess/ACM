package leetcode.contest.b79

import utils.print

fun main() {
    val s = SolutionB()
    s.largestWordCount(
        arrayOf("Hello userTwooo", "Hi userThree", "Wonderful day Alice", "Nice day userThree"),
        arrayOf("Alice", "userTwo", "userThree", "Alice")
    ).print()
}

class SolutionB {
    fun largestWordCount(messages: Array<String>, senders: Array<String>): String {
        val map = HashMap<String, Int>()
        var max = 0
        for (i in messages.indices) {
            map[senders[i]] = map.getOrDefault(senders[i], 0) + messages[i].split(" ").count()
            max = maxOf(max, map[senders[i]]!!)
        }
        val ans = ArrayList<String>()
        map.forEach { t, u ->
            if (u == max) {
                ans.add(t)
            }
        }
        ans.sort()
        return ans.last()
    }
}