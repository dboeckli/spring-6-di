# AGENTS.md

## Projekt

`spring-6-di` — Spring-Boot-DI-Lernprojekt (Spring Boot 4.1.1-Parent, Java 25, Paket
`ch.springframeworkguru.spring6di`). Build-/Deploy-Setup-Migration läuft (Onboarding #74). Siehe README.md
für Endpoints/Build/Kubernetes-Anleitung.

## Kommandos

| Zweck | Befehl |
|---|---|
| Format prüfen (spring-javaformat + spotless inkl. shfmt) | `./mvnw validate` |
| Format fixen | `./mvnw spotless:apply spring-javaformat:apply` |
| Tests | `./mvnw test` |
| Build (ohne Docker) | `./mvnw package -Dskip.docker.build=true` |
| Voll-Build (Docker-Image + Helm-Package) | `./mvnw clean install -DskipTests` |

Nach Code-Änderungen immer verifizieren: das relevante Maven-Goal oben ausführen und die Ausgabe als Evidenz
melden (nicht nur „done“).

## Sandbox

- Kit: opencode-sandbox-kit (README → Sandbox). Sandbox-Quirk: vor jedem `./mvnw`
  `export npm_config_bin_links=false` (Spotless/prettier → EPERM im Mount).
- Maven-Auflösung nutzt bei Mount `C:\development\maven-repo:ro` den Host-Cache. Nur echte Maven-Builds sind
  repräsentativ (`mvn dependency:get` ignoriert settings-`<proxies>`).
- Formatting: shfmt `3.13.1` (Spotless `<shfmt>` + CI `mfinelli/setup-shfmt@v4`).

## Hinweise

- Registry-/Migrations-Entscheidungen: opencode-sandbox-kit Buchhaltung #44.
- Onboarding-Drehbuch: opencode-sandbox-kit #45 (dieses Projekt: Issue #74, Branch
  `feature/74-onboarding-build-deploy`).
