# 📚 BookStore Microservices – Now in Docker-Flavored Goodness 🐳

Welcome, curious dev! You’ve stumbled upon the **BookStore**, a land where microservices roam free, books are just API calls away, and your Docker daemon cries (happy) tears of joy.

This project is built to prove that even books need microservices. We've broken everything down into adorable little Spring Boot services, slapped them into Docker containers, and told them to play nice through Docker Compose.

---

## 🧩 What's Inside This Marvelous Mess?

Service Name       : Description
------------------ : ----------------------------------------------------------------------
config-server      : The bossy librarian. Tells every service what settings to use. No excuses.
user-service       : Manages the humans. Hopefully non-evil. Stores their secrets and snacks (in Redis).
book-service       : Guardian of the sacred scrolls (and trashy novels). Talks to Kafka when excited.
order-service      : Where wallets cry. Places orders and screams to kafka when it is about to die.
eureka-server      : The social butterfly. Knows where everyone is, like WhatsApp with trust issues.
api-gateway        : The nightclub bouncer. Checks IDs, blocks troublemakers, and directs traffic.
kafka              : The town crier. Screams messages for everyone, even if they’re not listening yet.
redis              : The forgetful genius. Remembers everything quickly... until reboot.


---

## 🛠️ Pre-requisites

Before you go full microservice mode, make sure you have:

- ✅ Docker
- ✅ Docker Compose
- ✅ Git
- ✅ A sense of humor

---

## 🚀 How to Launch the BookStore Empire

1. Clone this circus:

\`\`\`bash
git clone https://github.com/your-username/bookstore-microservices.git
cd bookstore-microservices
\`\`\`

2. Make sure you have a `config-repo/` directory with YAML configs for each service:

\`\`\`sql
config-repo/
├── application.yml
├── user-service.yml
├── book-service.yml
├── order-service.yml
\`\`\`

3. Time to unleash the Kraken:

\`\`\`bash
docker-compose up --build
\`\`\`

Grab a coffee. Docker is brewing microservices ☕🐳
