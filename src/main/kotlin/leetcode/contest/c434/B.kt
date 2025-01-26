package leetcode.contest.c434

import utils.print
import java.util.*

fun main() {
    val s = SolutionB()
    s.countMentions(
        2,
        listOf(
            listOf("MESSAGE", "70", "HERE"),
            listOf("OFFLINE", "10", "0"),
            listOf("OFFLINE", "71", "0")
        )
    ).print()


    s.countMentions(
        3,
        listOf(
            listOf("MESSAGE", "2", "HERE"),
            listOf("OFFLINE", "2", "1"),
            listOf("OFFLINE", "1", "0"),
            listOf("MESSAGE", "61", "HERE")
        )
    ).print()

    // [2,2,2]
    s.countMentions(
        3,
        listOf(
            listOf("MESSAGE", "1", "id0 id1"),
            listOf("MESSAGE", "5", "id2"),
            listOf("MESSAGE", "6", "ALL"),
            listOf("OFFLINE", "5", "2")
        )
    ).print()
}

class SolutionB {
    fun countMentions(numberOfUsers: Int, events: List<List<String>>): IntArray {
        val isOnline = BooleanArray(numberOfUsers) { true }
        val mentions = IntArray(numberOfUsers) { 0 }

        val sorts = hashMapOf<String, Int>().apply {
            put("ONLINE", 0)
            put("OFFLINE", 1)
            put("MESSAGE", 2)
        }

        val pq = PriorityQueue<Triple<String, Int, String>>(compareBy({ it.second }, { sorts[it.first] }))
        events.forEach {
            pq.offer(Triple(it[0], it[1].toInt(), it[2]))
            if (it[0] == "OFFLINE") {
                pq.offer(Triple("ONLINE", it[1].toInt() + 60, it[2]))
            }
        }

        while (pq.isNotEmpty()) {
            val (eventType, ts, mention) = pq.poll()
            when (eventType) {
                "OFFLINE" -> isOnline[mention.toInt()] = false
                "ONLINE" -> isOnline[mention.toInt()] = true
                "MESSAGE" -> {
                    val ids = mention.split(" ")
                    when (mention) {
                        "ALL" -> {
                            for (i in mentions.indices) {
                                mentions[i]++
                            }
                        }
                        "HERE" -> {
                            for (i in mentions.indices) {
                                if (isOnline[i]) {
                                    mentions[i]++
                                }
                            }
                        }
                        else -> {
                            for (userId in ids) {
                                val id = userId.removePrefix("id").toInt()
//                                if (isOnline[id]) {
                                mentions[id]++
//                                }
                            }
                        }
                    }
                }
            }
        }

        return mentions
    }
}