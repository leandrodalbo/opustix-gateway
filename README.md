# Events Gateway (Spring Cloud Gateway)

A Spring Boot-based API Gateway with JWT authentication via Keycloak, routing, and CORS support.

---

## Features

- Routes requests to Keycloak and Ticketera microservices
- Secures APIs with OAuth2 JWT Resource Server using Keycloak
- Supports CORS for frontend apps 
- Path prefix stripping for clean forwarding

---

## Configuration

- **Server port:** `8081`
- **JWT Issuer URI:** Configurable via `KEYCLOAK_REALM_URI` environment variable  

### Routes

| ID                    | Path Pattern                 | Forward URI           | Filters         |
|-----------------------|------------------------------|-----------------------|-----------------|
| `keycloak-auth`       | `/auth/**`                   | `http://localhost:8080` | StripPrefix=1   |
| `public-ticketera-api`| `/api/public/ticketera/**`   | `http://localhost:8082` | StripPrefix=2   |
| `private-ticketera-api`| `/api/private/ticketera/**` | `http://localhost:8082` | StripPrefix=2   |

