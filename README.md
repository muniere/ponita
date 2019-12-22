# Ponita

Example application with [ktor](https://ktor.io/).

## Get Started

```bash
# Clone
$ git clone git@github.com:muniere/ponita.git
$ cd ponita

# Launch Web Server
$ ./gradlew :app:web:run

# With Database File
$ DATABASE_FILE=/path/to/your/sqlite/file ./gradlew :app:web:run

# Launch API Server
$ ./gradlew :app:api:run

# With Database File
$ DATABASE_FILE=/path/to/your/sqlite/file ./gradlew :app:api:run
```

## License

This project is under the MIT License - See [LICENSE.md](./LICENSE.md) for details.
