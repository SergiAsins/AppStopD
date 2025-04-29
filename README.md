# StopD - Backend system 🏠⚡

> "Because housing is a right, not just a business." 🏠
> 
StopD is an app project for managing eviction cases and building community support. It helps tenants and activists organize and get notified about upcoming evictions cases.

## What's Inside

- **User System**: Register, login, and manage profiles
- **Case Management**: Create, track, and organize eviction cases
- **Community Features**: Users can mark they'll attend evictions (like a support system)
- **Security**: Protected endpoints with user/admin roles

## Tech Stack

- Java 17
- Spring Boot 3
- Hibernate/JPA
- H2 Database (for development)
- Basic Auth Security

## How It Works

1. Users register and create profiles
2. Tenants/admin can post eviction cases
3. Others can "attend" cases to show support
4. Everyone gets notified when eviction dates approach

## Setup

1. Clone the repo
2. Configure your database in `application.properties`
3. Run with `mvn spring-boot:run`

## API Endpoints
| **Method** | **Endpoint** | **Description** | **Body Example** |
| --- | --- | --- | --- |
| **POST** | **`/api/v1/register`** | Register a new user | **`{"username": "user1", "password": "pass123"}`** |
| **GET** | **`/api/v1/login`** | Login and get user info | - |
| **POST** | **`/api/v1/profiles`** | Create user profile | **`{"name": "John", "email": "john@test.com"}`** |
| **PUT** | **`/api/v1/profiles/modify`** | Update your profile | **`{"phone": "123456789"}`** |
| **GET** | **`/api/v1/profiles/{email}`** | Get profile by email/ID | - |
| **POST** | **`/api/v1/cases`** | Create new eviction case | **`{"address": "123 Main St", "status": "PENDING"}`** |
| **POST** | **`/api/v1/cases/attend/{caseId}`** | Attend/join a case | - |
| **GET** | **`/api/v1/cases`** | Get all cases | - |
| **GET** | **`/api/v1/cases/by-id/{id}`** | Get case by ID | - |
| **GET** | **`/api/v1/cases/by-city?city=Barcelona`** | Filter cases by city | - |
| **GET** | **`/api/v1/cases/by-region?region=Catalonia`** | Filter cases by region | - |
| **GET** | **`/api/v1/cases/by-status?status=ACTIVE`** | Filter cases by status | - |
| **PUT** | **`/api/v1/cases/{caseId}`** | Update case details | **`{"status": "ACTIVE"}`** |
| **DELETE** | **`/api/v1/cases/{caseId}`** | Delete a case | - |