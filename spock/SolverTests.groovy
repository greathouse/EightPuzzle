import spock.lang.*
import com.greenmoonsoftware.eightpuzzle.*

class SolverTests extends spock.lang.Specification {
  def "canary test"() {
    setup:
      def solver = new Solver("1234567890")
    
    when:      
      def result = solver.solve()
    
    then:
      result != null
      result.moves == 0
      result.calculations == 1
  }
  
  def "simple test to validate equal or better performance during refactoring"() {
    setup:
      def solver = new Solver(puzzle)
    
    when:
      def result = solver.solve()
      
    then:
      result != null
      result.moves <= moves
      result.calculations <= calculations
    where:
      puzzle      | moves | calculations
      "123450786" | 1     | 2
      "123405786" | 2     | 3
      "012345678" | 76    | 199
  }
}