package pacman

abstract class Coordinate(val symbol: String) extends Ordered[Coordinate] {

  def x: Int

  def y: Int

  var g = 0

  var h = 0

  var f = 0

  var visited = false

  var closed = false

  var parent: Coordinate = null

  def update(cost: Int, estimatedCost: => Int) {
    g = cost
    if (h == 0) h = estimatedCost
    f = g + h
    visited = true
  }

  def cost(coordinate: Coordinate): Int = Math.abs(coordinate.x - this.x) + Math.abs(coordinate.y - this.y)

  def left: Point = (this.x - 1, this.y)

  def right: Point = (this.x + 1, this.y)

  def top: Point = (this.x, this.y - 1)

  def bottom: Point = (this.x, this.y + 1)

  def neighboursPoints: List[Point] = List(left, right, top, bottom)

  override def compare(that: Coordinate) = that.f compare f
}

case class Wall(val x: Int, val y: Int) extends Coordinate("█")

case class Ground(val x: Int, val y: Int) extends Coordinate("·")

case class Pacman(val x: Int, val y: Int) extends Coordinate("☺")

case class Food(val x: Int, val y: Int) extends Coordinate("ϖ")

case class Path(val x: Int, val y: Int) extends Coordinate("*")
