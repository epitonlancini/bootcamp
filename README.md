"# bootcamp"

## Quick Start

Make sure you have all requirements installed on your computer. Then, you may start the server using either a [Docker container](#run-using-docker) or in your [local machine](#run-local).

### Run using Docker

Build a docker image:

```bash
docker build -t ciandt/demo .
```

Start the API server:

```bash
docker run -p 8080:8080 --network="host" ciandt/demo
```
