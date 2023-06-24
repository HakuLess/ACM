package leetcode.contest.b107

class SolutionC {
    fun minimizeConcatenatedLength(words: Array<String>): Int {
        var ans = 0
        words.forEach {
            ans += it.length
        }

        val cur = ArrayList<Triple<Char, Char, Int>>()
        cur.add(Triple(words[0].first(), words[0].last(), 0))
        for (i in 1 until words.size) {
            val next = ArrayList<Triple<Char, Char, Int>>()
            val first = words[i].first()
            val last = words[i].last()
            cur.forEach {
                next.add(
                    Triple(
                        it.first, last, it.third + (if (first == it.second) {
                            1
                        } else {
                            0
                        })
                    )
                )

                next.add(
                    Triple(
                        first, it.second, it.third + (if (last == it.first) {
                            1
                        } else {
                            0
                        })
                    )
                )
            }
            next.sortBy { -it.third }
            val seen = HashSet<String>()
            cur.clear()
            cur.addAll(next.filter {
                ("${it.first}_${it.second}" !in seen).also { t ->
                    seen.add("${it.first}_${it.second}")
                }
            })
        }

        var max = 0
        cur.forEach {
            max = maxOf(max, it.third)
        }
        return ans - max
    }
}