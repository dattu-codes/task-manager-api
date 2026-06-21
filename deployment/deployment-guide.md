# Deployment Architecture & Strategies

This project is architected to support flexible, modern deployment patterns. I have configured and tested two main deployment pathways to ensure high availability and simplicity in configuration:

## 1. Containerized Multi-Service Deployment (Docker Compose)
To simplify local environment replication and virtual private server (VPS) hosting, I configured a multi-container deployment using Docker Compose. This orchestrates:
* **Backend Application Container**: Built using a multi-stage Dockerfile to minimize the final container size and run in a secure JRE environment.
* **Database Container**: A MySQL 8.0 database instance configured with volume persistence to prevent data loss.

### Setup Command:
From the root of the project, run:
```bash
docker-compose -f deployment/docker-compose.yml up --build
```
This automatically initializes the containers, sets up the private network bridge, and exposes the API on port `8080`.

## 2. Cloud PaaS Deployment (Render / Railway)
The backend is fully decoupled and configured to deploy directly to cloud hosting platforms:
* **Database Layer**: Can be integrated with any managed cloud database instance (such as Aiven, AWS RDS, or Supabase MySQL).
* **Application Layer**: Automatically compiled, built, and deployed using the root `Dockerfile` upon pushes to the `main` branch.

### Configured Environment Variables:
The application dynamically binds connection configurations at runtime using standard system environment variables:
* `SPRING_DATASOURCE_URL`: The JDBC connection URI pointing to the cloud database.
* `SPRING_DATASOURCE_USERNAME`: Database login username.
* `SPRING_DATASOURCE_PASSWORD`: Database login password.
