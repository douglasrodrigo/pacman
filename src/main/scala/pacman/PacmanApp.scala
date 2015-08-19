package pacman

import java.io.FileReader

object PacmanApp extends App {
  val pacmanSearch = Parser(new FileReader(args.head))
  pacmanSearch.search()
  pacmanSearch.grid.foreach(line => println(line.map(_.symbol).mkString("")))
}
