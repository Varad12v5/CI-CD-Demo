# Spring Boot CI/CD Pipeline with GitHub Actions & AWS EC2

[![Build Status](https://img.shields.io/github/workflow/status/yourusername/yourrepo/CI-CD/main)](https://github.com/yourusername/yourrepo/actions)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

A modern, production-style **CI/CD pipeline** that automates building and deploying a **Spring Boot application** to **AWS EC2** using **GitHub Actions** and **SSH-based deployment**.

> **Deliver code from commit to cloud—securely, automatically, and reliably.**

---

## 🚀 Quick Start

1. **Clone this repository**
   ```bash
   git clone https://github.com/yourusername/yourrepo.git
   cd yourrepo
   ```
2. **Build locally**
   ```bash
   mvn clean package
   ```
3. **Run locally**
   ```bash
   java -jar target/app.jar
   ```

---

## 📖 Overview

This repository demonstrates an end-to-end CI/CD workflow:
- Developers push code to GitHub
- GitHub Actions builds the Spring Boot application
- The latest artifact is securely deployed to an EC2 instance
- The running application is automatically restarted

All deployments happen **without manual SSH**, passwords, or human intervention.

---

## 🧱 Architecture

```
Local Development
  │
  ├── Git Push
  │
  ▼
GitHub Actions (CI/CD)
  │
  ├── Build JAR (Maven)
  │
  ├── Secure SSH Connection
  │
  ├── Upload Artifact
  │
  ▼
AWS EC2 Instance
  │
  └── Restart Spring Boot Application
```

---

## 🛠️ Tech Stack

| Category      | Technology              |
|--------------|-------------------------|
| Backend      | Spring Boot             |
| Build Tool   | Maven                   |
| CI/CD        | GitHub Actions          |
| Cloud        | AWS                     |
| Compute      | EC2                     |
| Deployment   | SSH + rsync             |
| OS           | Amazon Linux 2023       |
| Java         | Java 17                 |

---

## 📋 Prerequisites

- Java 17+
- Maven
- AWS account & EC2 instance
- SSH key pair
- GitHub repository with Actions enabled

---

## 📁 Repository Structure

```
.
├── src/
├── pom.xml
├── README.md
└── .github/
    └── workflows/
        └── deploy.yml
```

---

## 🔐 Security & Authentication

- SSH **key-based authentication**
- Private key stored securely in **GitHub Secrets**
- Public key added to EC2 `authorized_keys`
- No credentials committed to source control
- No passwords used during deployment

---

## 🔑 GitHub Secrets

| Secret        | Purpose                             |
|--------------|-------------------------------------|
| `EC2_HOST`   | Public IP address of the EC2 instance|
| `EC2_SSH_KEY`| Private SSH key used by GitHub Actions|

---

## 🖥️ EC2 One-Time Setup

Prepare your EC2 instance:
```
/home/ec2-user/
├── app/       # Running application
└── deploy/
    └── deploy.sh
```

### `deploy.sh` Example
```bash
#!/bin/bash
pkill -f ".jar" || true
cp ~/deploy/app.jar ~/app/app.jar
nohup java -jar ~/app/app.jar > ~/app/app.log 2>&1 &
```
This script ensures clean restarts and consistent deployments.

---

## 🔄 CI/CD Workflow

On every push to the master branch:
1. GitHub Actions checks out the source code
2. Java 17 is provisioned
3. Maven builds the Spring Boot JAR
4. Artifact is transferred to EC2 using rsync
5. Deployment script is executed remotely via SSH

This ensures repeatable, reliable deployments.

---

## 🌐 Accessing the Application

Once deployed, the application is available at:
```
http://<EC2_PUBLIC_IP>:8080
```
> _Without an Elastic IP, the EC2 public IP may change when the instance restarts._

---

## ❓ Troubleshooting & FAQ

- **App not reachable?**
  - Check EC2 security group allows inbound traffic on port 8080
  - Ensure the app is running: `ps aux | grep java`
- **Deployment failed?**
  - Review GitHub Actions logs for errors
  - Check SSH key permissions
- **EC2 IP changed?**
  - Update the `EC2_HOST` secret in GitHub

---

## 📈 Future Enhancements

- Attach Elastic IP for a stable endpoint
- Add Nginx as a reverse proxy
- Enable HTTPS with SSL
- Implement zero-downtime deployments
- Dockerize the application
- Deploy using ECS or Kubernetes

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

---

## 📚 References

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [GitHub Actions Docs](https://docs.github.com/en/actions)
- [AWS EC2 Docs](https://docs.aws.amazon.com/ec2/)

---

## 👨‍💻 Author

This project was built as a hands-on DevOps learning exercise to understand real-world CI/CD pipelines using Spring Boot, GitHub Actions, and AWS EC2.

---

## 📬 Contact

For questions or support, open an issue or contact [vbrahmapurkar@gmail.com](mailto:your.email@example.com).

---