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
  
  def "simple test"() {
    setup:
      def solver = new Solver("012345678")
    
    when:
      def result = solver.solve()
      
    then:
      result != null
      result.moves == 1
      result.calculations == 2
  }
}