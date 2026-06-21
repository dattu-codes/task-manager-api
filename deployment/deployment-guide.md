# Live Deployment Guide

This project is configured for containerized deployment using Docker. You can deploy it live easily using either Docker Compose or free cloud platforms.

## Option 1: Local Docker Deployment
To run the full stack (API + MySQL database) locally in isolated containers:
1. Make sure Docker Desktop is running.
2. Navigate to this directory and run:
   ```bash
   docker-compose up --build
   ```
3. The API will be live at `http://localhost:8080`.

## Option 2: Live Cloud Hosting (Render or Railway)
To deploy the application live on the cloud for free:

### 1. Database Setup (Aiven or Supabase)
Since the app needs a live MySQL database, spin up a free managed MySQL database on [Aiven.io](https://aiven.io/):
* Create a free account and choose **MySQL**.
* Copy the connection URL, username, and password.

### 2. Backend Deployment on Render
1. Create a free account on [Render](https://render.com/).
2. Click **New +** and select **Web Service**.
3. Connect your GitHub repository `task-manager-api`.
4. Set the following details:
   * **Runtime**: `Docker`
   * **Branch**: `main`
5. In the **Environment Variables** section, add:
   * `SPRING_DATASOURCE_URL` = `jdbc:mysql://<your-aiven-mysql-host>:<port>/<db_name>`
   * `SPRING_DATASOURCE_USERNAME` = `<your-username>`
   * `SPRING_DATASOURCE_PASSWORD` = `<your-password>`
6. Click **Deploy Web Service**. Render will automatically build the `Dockerfile` and deploy the service live with a public URL!
