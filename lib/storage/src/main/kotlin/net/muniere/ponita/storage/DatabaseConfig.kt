package net.muniere.ponita.storage

public sealed class DatabaseConfig {

    public final data class H2(
        public val name: String
    ) : DatabaseConfig()

    public final data class SQLite(
        public val path: String
    ) : DatabaseConfig()

    public final data class MySQL(
        public val host: String,
        public val port: Int,
        public val username: String,
        public val password: String,
        public val schema: String
    ) : DatabaseConfig()
}
