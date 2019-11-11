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
        assertEquals("a(?:bc(?:d)?)?", regex {
            literal { "a" }
            optional {
                literal { "b" }
                literal { "c" }
                optional {
                    regex { literal { "d" } }
                }
            }
        }.toString())
    }
}
