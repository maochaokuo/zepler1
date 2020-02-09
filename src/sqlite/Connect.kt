package zepler.com.misc.sqlite

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 *
 * @author sqlitetutorial.net
 */
object Connect {
    /**
     * zepler.com.misc.sqlite.Connect to a sample database
     */
    fun connect() {
        var conn: Connection? = null
        try { // db parameters
            val url = "jdbc:sqlite:db/zeplerDB.db"
            // create a connection to the database
            conn = DriverManager.getConnection(url)
            println("Connection to SQLite has been established.")
            println(123)
        } catch (e: SQLException) {
            println(e.message)
        } finally {
            try {
                conn?.close()
            } catch (ex: SQLException) {
                println(ex.message)
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    @JvmStatic
    fun main(args: Array<String>) {
        connect()
    }
}