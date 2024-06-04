package jp.ac.it_college.std.s23006.exposed.dsl

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect(
        url = "jdbc:mariadb://127.0.01:3306/example",
        driver = "org.mariadb.jdbc.Driver",
        user = "root",
        password = "maria"
    )
    transaction {
        addLogger(StdOutSqlLogger)

        println("----5.4.3----")
        val user = UserTable.selectAll().where {
            UserTable.id eq 101
        }.single()
        println(user)
    }
}

object UserTable : IntIdTable("user") {
    val name = varchar("name", 16)
    val age = integer("age")
    val profile = varchar("profile", 64)
}