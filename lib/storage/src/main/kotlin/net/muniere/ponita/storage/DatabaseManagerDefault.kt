package net.muniere.ponita.storage

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.muniere.ponita.storage.table.Messages
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger
import java.sql.Connection

public final class DatabaseManagerDefault(
    private val config: DatabaseConfig,
    private val log: Logger
) : DatabaseManager {

    public override fun bootstrap() {
        val config = when (val conf = this.config) {
            is DatabaseConfig.H2 -> HikariConfig().also {
                it.driverClassName = "org.h2.Driver"
                it.jdbcUrl = "jdbc:h2:mem:${conf.name}"
                it.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                it.maximumPoolSize = 5
                it.isAutoCommit = false
            }
            is DatabaseConfig.SQLite -> HikariConfig().also {
                it.driverClassName = "org.sqlite.JDBC"
                it.jdbcUrl = "jdbc:sqlite:${conf.path}"
                it.maximumPoolSize = 5
                it.isAutoCommit = false
            }
            is DatabaseConfig.MySQL -> HikariConfig().also {
                it.driverClassName = "com.mysql.jdbc.Driver"
                it.jdbcUrl = "jdbc:mysql://${conf.host}:${conf.port}/${conf.schema}"
                it.username = conf.username
                it.password = conf.password
                it.maximumPoolSize = 5
                it.isAutoCommit = false
            }
        }

        config.validate()

        Database.connect(
            HikariDataSource(config)
        )

        when (this.config) {
            is DatabaseConfig.H2 -> {
                // do nothing
            }
            is DatabaseConfig.SQLite -> {
                TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
            }
            is DatabaseConfig.MySQL -> {
                // do nothing
            }
        }

        this.log.debug("Connected to Database: ${config.jdbcUrl}")

        transaction {
            SchemaUtils.create(Messages)
        }
    }
}
