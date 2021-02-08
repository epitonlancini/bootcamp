### Swagger docs

http://localhost:8080/swagger.html


### Run using Docker

Package the project:

```bash
mvn package
```

Build a docker image:

```bash
docker build -t ciandt/demo .
```

Start the API server:

```bash
docker run -p 8080:8080 --network="host" ciandt/demo
```
