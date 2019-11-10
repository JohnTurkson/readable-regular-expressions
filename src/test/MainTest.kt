import dsl.regex
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MainTest {
    
    @BeforeEach
    fun setUp() {
        
    }
    
    @AfterEach
    fun tearDown() {
        
    }
    
    @Test
    fun testOptional() {
        // Literal cannot contain optional, optional must contain a single group type object cases are covered at compile-time
        val sb = StringBuilder()
        regex {
            optional {
                literal { "a" }
            }
        }.render(sb)
        assertEquals("a?", sb.toString())
    }

}
