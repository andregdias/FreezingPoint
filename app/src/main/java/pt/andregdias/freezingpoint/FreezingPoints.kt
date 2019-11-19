package pt.andregdias.freezingpoint

class FreezingPoints(wines: List<Wine>) {
  val totalVolume: Int
  val average: Double
  val freezingWhite: Double
  val freezingRed: Double

  init {
    totalVolume = wines.map { e -> e.volume }.sum()
    average = wines.map { e -> e.alcohol / totalVolume * e.volume }.sum()
    freezingWhite = freezingPoint(average, -2.0)
    freezingRed = freezingPoint(average, -1.5)
  }

  private fun freezingPoint(average: Double, diff: Double): Double {
    return average / 2 + diff
  }
}
