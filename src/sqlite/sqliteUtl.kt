package sqlite

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class sqliteUtl(dbFullPath: String) {
    var connS : String = "jdbc:sqlite:$dbFullPath"

    init {
        if (connS.indexOf(".db")<0)
            connS = "$dbFullPath.db"
    }

    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private fun connect(): Connection? { // SQLite connection string
        //val url = "jdbc:sqlite:C://sqlite/db/test.db"
        var conn: Connection? = null
        try {
            conn = DriverManager.getConnection(connS)
        } catch (e: SQLException) {
            println(e.message)
        }
        return conn
    }
}
