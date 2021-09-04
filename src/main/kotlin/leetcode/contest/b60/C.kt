package leetcode.contest.b60

class LockingTree(parent: IntArray) {

    val n = parent.size

    val lockUser = HashMap<Int, Int>()

    val leftMap = HashMap<Int, ArrayList<Int>>()
    val rightMap = HashMap<Int, ArrayList<Int>>()

    init {
        for (i in parent.indices) {
            leftMap[parent[i]] = leftMap.getOrDefault(parent[i], arrayListOf())
            leftMap[parent[i]]!!.add(i)

            rightMap[i] = rightMap.getOrDefault(i, arrayListOf())
            rightMap[i]!!.add(parent[i])
        }
    }

    fun lock(num: Int, user: Int): Boolean {
        if (lockUser.getOrDefault(num, -1) == -1) {
            lockUser[num] = user
            return true
        }
        return false
    }

    fun unlock(num: Int, user: Int): Boolean {
        if (lockUser.getOrDefault(num, -1) == user) {
            lockUser.remove(num)
            return true
        }
        return false
    }

    fun upgrade(num: Int, user: Int): Boolean {
        if (num in lockUser) return false
        fun checkLeft(num: Int, over: Boolean = false): Boolean {
            var ans = false
            println(num)
            for (next in leftMap.getOrDefault(num, arrayListOf())) {
                if (next in lockUser) if (!over) return true else lockUser.remove(next)
                else ans = ans or checkLeft(next)
            }
            return ans
        }

        fun checkRight(num: Int): Boolean {
            var ans = true
            for (next in rightMap.getOrDefault(num, arrayListOf())) {
                if (next in lockUser) return false
                else ans = ans and checkRight(next)
            }
            return ans
        }
        if (!checkLeft(num)) return false
        if (!checkRight(num)) return false
        lockUser[num] = user
        checkLeft(num, true)
        checkRight(num)
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