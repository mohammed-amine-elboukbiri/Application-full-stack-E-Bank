# Partie 1 : 🏦 Architecture Microservices – Customer & E-Bank

Application basée sur une **architecture microservices** avec Spring Boot et Spring Cloud. Elle gère des clients (`Customer-Service`) et leurs comptes bancaires (`EBank-Service`), avec communication inter-services, tolérance aux pannes, passerelle d'accès et découverte de services.

---

## 📋 Table des matières

- [Objectifs](#-objectifs)
- [Architecture](#-architecture)
- [Technologies](#-technologies)
- [Structure du projet](#-structure-du-projet)
- [Les services](#-les-services)
- [Communication avec OpenFeign](#-communication-avec-openfeign)
- [Fault Tolerance avec Resilience4J](#-fault-tolerance-avec-resilience4j)
- [Lancer le projet](#-lancer-le-projet)
- [Tests avec Swagger-UI](#-tests-avec-swagger-ui)

---

## 🎯 Objectifs

- [x] Développer le service **Customer-Service**
- [x] Développer le service **EBank-Service**
- [x] Établir la communication entre les deux services avec **OpenFeign**
- [x] Mettre en place la **tolérance aux pannes** avec le Circuit Breaker **Resilience4J**
- [x] Développer la **Gateway** (Spring Cloud Gateway)
- [x] Développer le **Discovery Service** (Eureka Server)
- [x] Tester les services backend avec un client REST : **Swagger-UI**

---

## 🏗 Architecture


| Service            | Rôle                                         | Port   |
|--------------------|----------------------------------------------|--------|
| Discovery Service  | Registre des services (Eureka Server)        | `8761` |
| Gateway            | Point d'entrée unique, routage dynamique     | `9999` |
| Customer-Service   | Gestion des clients                          | `8086` |
| EBank-Service      | Gestion des comptes bancaires                | `8087` |



---

## 🛠 Technologies

- **Java 21**
- **Spring Boot 4.1.2**
- **Spring Cloud** (Netflix Eureka, Gateway, OpenFeign)
- **Resilience4J** (Circuit Breaker)
- **Spring Data JPA** + base de données **H2** 
- **Lombok**
- **springdoc-openapi** (Swagger-UI)
- **Maven**

---

## 📁 Structure du projet

<img width="521" height="454" alt="Screenshot 2026-11-21 at 13 36 55" src="https://github.com/user-attachments/assets/5907cc72-1d0b-4aca-9e1d-c7a1f21f6c90" />

---

## 🧩 Les services

### 1. Discovery Service

Serveur Eureka permettant l'enregistrement et la découverte dynamique des microservices.

---

### 2. Customer-Service

Gère le cycle de vie des clients (CRUD).

---

### 3. EBank-Service

Gère les comptes bancaires et les associe à un client (récupéré depuis `Customer-Service`).

---

### 4. Gateway

Point d'entrée unique de l'application. Le routage est dynamique grâce à Eureka.

### Exemples d'accès via la gateway :

**Vers E-Bank :**

**Affichage de tous les compte :** 

<img width="527" height="961" alt="Screenshot 2026-11-20 at 22 51 11" src="https://github.com/user-attachments/assets/8e8b9a64-2f57-4053-85b5-5dc73620f82f" />


**Affichage d'un compte par son id :**

<img width="721" height="270" alt="Screenshot 2026-11-20 at 22 51 32" src="https://github.com/user-attachments/assets/bd5bbfe6-f302-427c-904a-358451ee8d81" />


**Vers Customers :**


**Affichage de tous les clients :** 

<img width="503" height="361" alt="Screenshot 2026-11-20 at 23 00 48" src="https://github.com/user-attachments/assets/5e7aaa0c-eeb9-497e-ad9a-4572632e0b8f" />

---


## 🔗 Communication avec OpenFeign

`EBank-Service` appelle `Customer-Service` de manière déclarative grâce à **OpenFeign**.

---

## 🛡 Fault Tolerance avec Resilience4J

Un **Circuit Breaker** protège `EBank-Service` en cas d'indisponibilité de `Customer-Service`. Si le service client est en panne ou trop lent, le circuit s'ouvre et une **méthode de repli (fallback)** est exécutée à la place de l'erreur.

**États du Circuit Breaker :**

| État          | Comportement                                                       |
|---------------|--------------------------------------------------------------------|
| `CLOSED`      | Les appels passent normalement                                     |
| `OPEN`        | Les appels sont bloqués, le fallback est exécuté                   |
| `HALF_OPEN`   | Quelques appels test pour vérifier si le service est rétabli       |

**Tester la résilience :**

1. Démarrer tous les services.
2. Appeler `GET /accounts/{id}` → les données du client s'affichent.
3. **Arrêter `Customer-Service`**.
4. Rappeler `GET /accounts/{id}` → la réponse de **fallback** est renvoyée.

<img width="753" height="277" alt="Screenshot 2026-11-20 at 22 52 03" src="https://github.com/user-attachments/assets/3d618b09-148e-4d43-a3b0-06927fa56fe0" />


---

## 🚀 Lancer le projet

### Ordre de démarrage

#### 2. Discovery Service (en premier)


#### 3. Customer-Service


#### 4. EBank-Service


#### 5. Gateway (en dernier)

```
Vérifier que tous les services sont enregistrés sur <http://localhost:8761>.

```

<img width="1492" height="959" alt="Screenshot 2026-11-20 at 17 53 25" src="https://github.com/user-attachments/assets/ffbc37c1-930d-45cb-ace1-6dfb295f5a77" />


---

## 🧪 Tests avec Swagger-UI

Swagger-UI permet de :

- visualiser tous les endpoints exposés,
- envoyer des requêtes `GET`, `POST`, `PUT`, `DELETE`,
- inspecter les réponses et les codes HTTP.

**Test du requête Get de tous les comptes**.

<img width="1462" height="998" alt="Screenshot 2026-11-20 at 22 58 12" src="https://github.com/user-attachments/assets/2c07feb6-ec05-4465-8024-8b40297e8701" />

**Test du requête Post qui permet d'ajouter un compte**.

<img width="1423" height="913" alt="Screenshot 2026-11-20 at 22 59 11" src="https://github.com/user-attachments/assets/ec3b891d-93c2-4651-a8db-c997c0f89f25" />

**Test du requête Get d'un compte par son id**.

<img width="1444" height="850" alt="Screenshot 2026-11-20 at 22 59 29" src="https://github.com/user-attachments/assets/a2599b7c-e1e2-4505-8fb6-f3023132e57f" />

---
