package leetcode.contest.c301

class SmallestInfiniteSet() {

    val set = HashSet<Int>()

    val max = 10000

    fun popSmallest(): Int {
        for (i in 1..max) {
            if (i !in set) {
                set.add(i)
                return i
            }
        }
        return -1
    }

    fun addBack(num: Int) {
        set.remove(num)
    }

}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */