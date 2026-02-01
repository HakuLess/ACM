package leetcode.contest.c487

class RideSharingSystem() {

    private val drivers = ArrayDeque<Int>()
    private val riders = ArrayDeque<Int>()
    private val waitingRiders = HashSet<Int>()

    fun addRider(riderId: Int) {
        riders.addLast(riderId)
        waitingRiders.add(riderId)
    }

    fun addDriver(driverId: Int) {
        drivers.addLast(driverId)
    }

    fun cancelRider(riderId: Int) {
        waitingRiders.remove(riderId)
    }

    fun matchDriverWithRider(): IntArray {
        while (riders.isNotEmpty() && !waitingRiders.contains(riders.first())) {
            riders.removeFirst()
        }

        if (drivers.isEmpty() || riders.isEmpty()) {
            return intArrayOf(-1, -1)
        }

        val driverId = drivers.removeFirst()
        val riderId = riders.removeFirst()
        waitingRiders.remove(riderId)

        return intArrayOf(driverId, riderId)
    }

}

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * var obj = RideSharingSystem()
 * obj.addRider(riderId)
 * obj.addDriver(driverId)
 * var param_3 = obj.matchDriverWithRider()
 * obj.cancelRider(riderId)
 */