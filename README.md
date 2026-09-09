# Spring 6 Application

## Overview

This project is a Spring Boot 4.1.1 (Spring Framework 6) application — a DI learning project.

## Prerequisites

- Java 25
- Maven 3.x

## Development

This project uses Spring Boot 4.1.1 and Java 25. It's configured with various plugins and dependencies to support:
- Actuator for monitoring and managing the application
- Logbook for HTTP request and response logging
- Docker image building and publishing
- Code coverage with JaCoCo
- Git commit information

## Building

- To build the project: `mvn clean install`
- To run tests: `mvn test`
- To build a Docker image: `mvn clean install` is creating a local image

## CI/CD

The project includes a CI/CD profile for GitHub Actions. When activated, it enables Docker image publishing to GitHub Packages and Docker Hub.

## Logging

The application uses Logbook and Logstash for enhanced logging capabilities.

## Deployment

### Deployment with Kubernetes

To run maven filtering for destination target/k8s

```bash
mvn clean install -DskipTests 
```

Deployment goes into the default namespace.

To deploy all resources:

```bash
kubectl apply -f target/k8s/
```

To remove all resources:

```bash
kubectl delete -f target/k8s/
```

Check

```bash
kubectl get deployments -o wide
kubectl get pods -o wide
```

You can use the actuator rest call to verify via port 30080

### Deployment with Helm

Be aware that we are using a different namespace here (not default).

To run maven filtering for destination target/helm

```bash
mvn clean install -DskipTests 
```

Go to the directory where the tgz file has been created after 'mvn install'

```powershell
cd target/helm/repo
```

unpack

```powershell
$file = Get-ChildItem -Filter *.tgz | Select-Object -First 1
tar -xvf $file.Name
```

install

```powershell
$APPLICATION_NAME = Get-ChildItem -Directory | Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } | Select-Object -ExpandProperty Name
helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME --namespace spring-6-di --create-namespace --wait --timeout 5m --debug --render-subchart-notes
```

show logs

```powershell
kubectl get pods -l app.kubernetes.io/name=$APPLICATION_NAME -n spring-6-di
```

replace $POD with pods from the command above

```powershell
kubectl logs $POD -n spring-6-di --all-containers
```

test

```powershell
helm test $APPLICATION_NAME --namespace spring-6-di --logs
```

uninstall

```powershell
helm uninstall $APPLICATION_NAME --namespace spring-6-di
```

delete all

```powershell
kubectl delete all --all -n spring-6-di
```

create busybox sidecar

```powershell
kubectl run busybox-test --rm -it --image=busybox:1.36 --namespace=spring-6-di --command -- sh
```

You can use the actuator rest call to verify via port 30080

## Sandbox

Development in an isolated Docker sandbox via [opencode-sandbox-kit](https://github.com/dboeckli/opencode-sandbox-kit).
Prerequisites: `sbx` CLI, secrets (`sbx secret set github` + `sbx secret set github-maven`), IntelliJ-MCP registration
(`sbx mcp add idea --url http://localhost:64615/stream --skip-ssrf-check`).

Start (PowerShell) — multiline, with `--static-mcp idea`, pinned template version and a read-only host Maven cache
(no re-download of cached dependencies):

```powershell
sbx run opencode --name spring-6-di `
    --static-mcp idea `
    --kit "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=opencode-agent" `
    -t docker/sandbox-templates:opencode-docker-0.5.0 `
    "C:\development\projects\spring-6-di" `
    "$env:USERPROFILE\.kube:ro" `
    "C:\development\maven-repo:ro"
```

