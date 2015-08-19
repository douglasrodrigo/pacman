package pacman

import scala.collection.mutable.PriorityQueue

case class PacmanSearch(pacman: Pacman, food: Food, gridSize: GridSize) {

  lazy val grid: Array[Array[Coordinate]] = Array.ofDim(gridSize._1, gridSize._2)

  def isDefinedAt(point: Point): Boolean = grid.isDefinedAt(point._1) && grid(point._1).isDefinedAt(point._2)

  def add(coordinate: Coordinate) {
    grid(coordinate.x)(coordinate.y) = coordinate
  }

  def get(point: Point): Option[Coordinate] = if (isDefinedAt(point)) Some(grid(point._1)(point._2)) else None

  def legalNeighbours(coord: Coordinate): List[Coordinate] = coord.neighboursPoints.map(get(_)).flatten.filter {neighbour => neighbour.isInstanceOf[Ground] || neighbour.isInstanceOf[Food]}

  private def tracePath(origin: Coordinate, target: Coordinate) {
    aStarSeach(origin, target).filterNot(_ == target).foreach { coordinate => 
      add(Path(coordinate.x, coordinate.y))
    }
  }

  private def visitedPath(coordinate: Coordinate): List[Coordinate] = coordinate.parent match {
    case null => Nil
    case parent => coordinate :: visitedPath(parent)
  }

  private def aStarSeach(origin: Coordinate, target: Coordinate): List[Coordinate] = {

    val openCoordinates = new PriorityQueue[Coordinate]()

    openCoordinates.enqueue(origin)

    while(openCoordinates.nonEmpty) {

      val coordinate = openCoordinates.dequeue

      println(s"top openList ${coordinate}")

      if (coordinate == target) {
        return visitedPath(coordinate)
      } else {
        coordinate.closed = true
        println (s"neighbours ${legalNeighbours(coordinate)}")

        legalNeighbours(coordinate).foreach { neighbour =>
          val realCost = coordinate.g + 1
          val beenVisited = neighbour.visited

          if(!beenVisited || realCost < neighbour.g) {
            neighbour.parent = coordinate
            neighbour.update(realCost, neighbour.cost(target))

            if (!beenVisited) openCoordinates enqueue neighbour
          }
        }
      }
    }
    throw new RuntimeException("No path found")
  }

  def search() {
    tracePath(pacman, food)
  }
}
