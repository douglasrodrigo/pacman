package pacman

import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.Positional

object Parser extends RegexParsers {

  case class PositionValue(value: String) extends Positional

  def coordValue: Parser[Int] = """(\d+)""".r ^^ { _.toInt }

  def coordTuple: Parser[(Int, Int)] = "(" ~ coordValue ~ "," ~ coordValue ~ ")" ^^ { case (_ ~ x ~ _ ~ y ~ _) => (x, y)}
  
  def pacman: Parser[Pacman] = "P=" ~ coordTuple  ^^ { case (_ ~ coord) => Pacman(coord._1, coord._2)}

  def food: Parser[Food] = "C=" ~ coordTuple  ^^ { case (_ ~ coord) => Food(coord._1, coord._2)}

  def gridSize: Parser[GridSize] = coordValue ~ "x" ~ coordValue  ^^ { case (x ~ _ ~ y) => (x, y)}

  def tokenGrid: Parser[PositionValue] = positioned("""[█·☺ϖ]""".r ^^ { PositionValue(_) })

  def coordinate: Parser[Coordinate] = tokenGrid ^^ { case token => 
    val row = token.pos.line - 4
    val col = token.pos.column - 1

    token.value match {
      case "█" => Wall(row, col)
      case "·" => Ground(row, col)
      case "☺" => Pacman(row, col)
      case "ϖ" => Food(row, col)
    }
  }

  def grid: Parser[List[Coordinate]] = rep(coordinate)

  def pacmanGrid: Parser[PacmanSearch] = pacman ~ food ~ gridSize ~ grid ^^ { case pacman ~ food ~ gridSize ~ grid => 
    val pacmanSearch = PacmanSearch(pacman, food, gridSize)
    grid.foreach(pacmanSearch.add(_))
    pacmanSearch
  }

  def apply(input: java.io.Reader): PacmanSearch = parseAll(pacmanGrid, input) match {
    case Success(result, _) => result
    case failure : NoSuccess => scala.sys.error(s"Mapa inválido: ${failure.msg}")
  }

}
