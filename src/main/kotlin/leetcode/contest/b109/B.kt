package leetcode.contest.b109

class SolutionB {
    fun sortVowels(s: String): String {
        val arr = s.toCharArray()
        val v = hashSetOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        val index = ArrayList<Int>()
        val l = ArrayList<Char>()
        for (i in arr.indices) {
            if (arr[i] in v) {
                index.add(i)
                l.add(arr[i])
            }
        }
        l.sort()
        for (i in index.indices) {
            arr[index[i]] = l[i]
        }
        return arr.joinToString("")
    }
}