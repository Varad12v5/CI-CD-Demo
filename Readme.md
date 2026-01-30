# Spring Boot CI/CD Pipeline with GitHub Actions & AWS EC2

A production-style **CI/CD pipeline** that automatically builds and deploys a **Spring Boot application** to an **AWS EC2 instance** using **GitHub Actions** and **SSH-based deployment**.

This project demonstrates how real-world teams automate application delivery without manual server access.

---

## 📖 Overview

This repository showcases an end-to-end CI/CD workflow where:

- Developers push code to GitHub
- GitHub Actions builds the Spring Boot application
- The latest artifact is securely deployed to an EC2 instance
- The running application is automatically restarted

All deployments happen **without manual SSH**, passwords, or human intervention.

---

## 🧱 Architecture

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


---

## 🛠️ Tech Stack

| Category | Technology |
|--------|------------|
| Backend | Spring Boot |
| Build Tool | Maven |
| CI/CD | GitHub Actions |
| Cloud Provider | AWS |
| Compute | EC2 |
| Deployment | SSH + rsync |
| OS | Amazon Linux 2023 |
| Java | Java 17 |

---

## 📁 Repository Structure

.
├── src/
├── pom.xml
├── README.md
└── .github/
└── workflows/
└── deploy.yml


---

## 🔐 Security & Authentication

- SSH **key-based authentication**
- Private key stored securely in **GitHub Secrets**
- Public key added to EC2 `authorized_keys`
- No credentials committed to source control
- No passwords used during deployment

---

## 🔑 GitHub Secrets

The following secrets are configured in the GitHub repository:

| Secret | Purpose |
|------|---------|
| `EC2_HOST` | Public IP address of the EC2 instance |
| `EC2_SSH_KEY` | Private SSH key used by GitHub Actions |

---

## 🖥️ EC2 One-Time Setup

The EC2 instance is prepared once with the following structure:

/home/ec2-user/
├── app/ # Running application
└── deploy/
└── deploy.sh


### `deploy.sh`

```bash
#!/bin/bash

pkill -f ".jar" || true
cp ~/deploy/app.jar ~/app/app.jar
nohup java -jar ~/app/app.jar > ~/app/app.log 2>&1 &
This script ensures clean restarts and consistent deployments.

🔄 CI/CD Workflow
On every push to the master branch:

GitHub Actions checks out the source code

Java 17 is provisioned

Maven builds the Spring Boot JAR

Artifact is transferred to EC2 using rsync

Deployment script is executed remotely via SSH

This ensures repeatable, reliable deployments.

🌐 Accessing the Application
Once deployed, the application is available at:

http://<EC2_PUBLIC_IP>:8080
Note: Without an Elastic IP, the EC2 public IP may change when the instance restarts.

🚀 Why This Approach?
Fully automated deployments

No manual server access

Secure and scalable

Mirrors real-world DevOps practices

Easy to extend for containers or orchestration platforms

📈 Future Enhancements
Attach Elastic IP for a stable endpoint

Add Nginx as a reverse proxy

Enable HTTPS with SSL

Implement zero-downtime deployments

Dockerize the application

Deploy using ECS or Kubernetes

👨‍💻 Author
This project was built as a hands-on DevOps learning exercise to understand real-world CI/CD pipelines using Spring Boot, GitHub Actions, and AWS EC2.


---

### ✅ What this README does right

- Clean structure
- No unnecessary emojis
- Professional tone
- Clear architecture
- Interview-friendly explanations
- Production mindset

This is **exactly** what a solid GitHub project README should look like.

---

Next up (as promised):

👉 **Elastic IP** — stable address, no more broken deployments  
Just say **“Elastic IP now”** and we’ll lock it down 🔒