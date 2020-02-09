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

    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    fun insert(name: String?, capacity: Double) {
        val sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { pstmt ->
                    pstmt.setString(1, name)
                    pstmt.setDouble(2, capacity)
                    pstmt.executeUpdate()
                }
            }
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    /**
     * select all rows in the warehouses table
     */
    fun selectAll():List<List<String>> {
        val sql = "SELECT id, name, capacity FROM warehouses"
        val rtn =mutableListOf<List<String>>()
        try {
            connect().use { conn ->
                conn!!.createStatement().use { stmt ->
                    stmt.executeQuery(sql).use { rs ->
                        // loop through the result set
                        while (rs.next()) {
                            val tmp1=mutableListOf<String>()
                            println(
                                rs.getInt("id").toString() + "\t" +
                                        rs.getString("name") + "\t" +
                                        rs.getDouble("capacity")
                            )
                            tmp1.add(rs.getInt("id").toString())
                            tmp1.add(rs.getString("name"))
                            tmp1.add(rs.getDouble("capacity").toString())
                            rtn.add(tmp1)
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println(e.message)
        }
        return rtn
    }

    /**
     * Update data of a warehouse specified by the id
     *
     * @param id
     * @param name name of the warehouse
     * @param capacity capacity of the warehouse
     */
    fun update(id: Int, name: String?, capacity: Double) {
        val sql = ("UPDATE warehouses SET name = ? , "
                + "capacity = ? "
                + "WHERE id = ?")
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { pstmt ->
                    // set the corresponding param
                    pstmt.setString(1, name)
                    pstmt.setDouble(2, capacity)
                    pstmt.setInt(3, id)
                    // update
                    pstmt.executeUpdate()
                }
            }
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    /**
     * Delete a warehouse specified by the id
     *
     * @param id
     */
    fun delete(id: Int) {
        val sql = "DELETE FROM warehouses WHERE id = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { pstmt ->
                    // set the corresponding param
                    pstmt.setInt(1, id)
                    // execute the delete statement
                    pstmt.executeUpdate()
                }
            }
        } catch (e: SQLException) {
            println(e.message)
        }
    }

}
