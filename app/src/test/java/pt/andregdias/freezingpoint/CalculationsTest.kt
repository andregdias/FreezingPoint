package pt.andregdias.freezingpoint

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculationsTest {

  private val wineEntries =
    listOf(
      Wine(id = 0, alcohol = 12.81, volume = 17),
      Wine(id = 1, alcohol = 13.5, volume = 33),
      Wine(id = 2, alcohol = 15.0, volume = 5)
    )

  @Test
  fun totalVolume_isCorrect() {
    assertEquals(FreezingPoints(wineEntries).totalVolume, 55)
  }

  @Test
  fun average_isCorrect() {
    assertEquals(FreezingPoints(wineEntries).average, 13.42309091, 0.000000001)
  }

  @Test
  fun whiteAndRose_isCorrect() {
    assertEquals(FreezingPoints(wineEntries).freezingWhite, 4.711545455, 0.000000001)
  }

  @Test
  fun red_isCorrect() {
    assertEquals(FreezingPoints(wineEntries).freezingRed, 5.211545455, 0.000000001)
  }
}
