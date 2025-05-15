# JesusFromNorth Clinic System - Backend

A secure Spring Boot application for the JesusFromNorth Clinic System, providing REST API services with JWT-based authentication.

## Table of Contents

- [Overview](#overview)
- [Security Implementation](#security-implementation)
- [Getting Started](#getting-started)
- [Authentication Flow](#authentication-flow)
- [API Endpoints](#api-endpoints)
- [Security Best Practices](#security-best-practices)
- [Environment Setup](#environment-setup)
- [Troubleshooting](#troubleshooting)

## Overview

This Spring Boot application serves as the backend for the JesusFromNorth Clinic System. It provides secure REST APIs for user authentication, clinic management, and patient data operations.

**Technologies Used:**
- Java 23
- Spring Boot 3.4.4
- Spring Security
- JWT Authentication
- MySQL Database
- Maven

## Security Implementation

The application implements a comprehensive security approach:

### JWT-Based Authentication

- **Stateless Authentication**: Using JWT for secure, stateless authentication
- **Token-based Authorization**: Role-based access control (RBAC)
- **Refresh Token Mechanism**: Long-lived refresh tokens to obtain new access tokens
- **Token Expiration**: Configurable expiration times for both access and refresh tokens

### Key Security Components

1. **SecurityConfiguration**: 
   - Configures security filter chains
   - Establishes CORS policies
   - Defines authentication manager
   - Sets up stateless session management

2. **JwtService**:
   - Handles token generation and validation
   - Manages claims extraction
   - Secures token signing with configurable secret keys

3. **JwtAuthenticationFilter**:
   - Intercepts incoming requests
   - Extracts and validates JWT tokens
   - Sets authentication in the security context

4. **Environment Variable Management**:
   - Secure handling of credentials and secrets
   - Environment-specific configuration

## Getting Started

### Prerequisites

- JDK 23 or later
- Maven 3.8+ or compatible build tool
- MySQL 8.0+ database server
- Git

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-organization/JesusFromNorth_BackEnd.git
   cd JesusFromNorth_BackEnd/clinic_system
   ```

2. Set up environment variables (see [Environment Setup](#environment-setup))
   ```bash
   # Create .env file from template
   cp .env.example .env
   # Edit .env with your configurations
   ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The server will start on port 8080 (default) or the port specified in your environment variables.

## Authentication Flow

### Login Process

1. Client sends credentials to `/auth/login` endpoint
2. Server validates credentials using Spring Security's AuthenticationManager
3. If valid, server generates:
   - Access token (short-lived, default: 24h)
   - Refresh token (long-lived, default: 7 days)
4. Both tokens are returned to the client along with user information

### Using Authentication

1. Client includes the access token in the Authorization header of subsequent requests:
   ```
   Authorization: Bearer <access_token>
   ```

2. The JwtAuthenticationFilter intercepts the request, validates the token, and sets up the security context

### Token Refresh Process

When the access token expires:

1. Client sends refresh token to `/auth/refresh-token` endpoint
2. Server validates the refresh token
3. If valid, server issues a new access token
4. Client uses the new access token for subsequent requests

## API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/auth/login` | Authenticate user and get tokens | Public |
| POST | `/auth/refresh-token` | Get new access token using refresh token | Public |
| GET | `/auth/user-info` | Get current user information | Authenticated |

### Protected Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| * | `/admin/**` | Admin-only endpoints | ADMIN |
| * | `/user/**` | User accessible endpoints | USER, ADMIN |
| * | All other endpoints | Any other API endpoints | Authenticated |

## Security Best Practices

1. **Token Storage**:
   - Store access tokens in memory or session storage (not localStorage)
   - Store refresh tokens in HttpOnly cookies

2. **Token Validation**:
   - Always validate tokens on the server side
   - Check token expiration and signature

3. **Error Handling**:
   - Do not expose sensitive information in error messages
   - Log security events appropriately

4. **Secrets Management**:
   - Never hardcode secrets in source code
   - Use environment variables and proper secret management solutions
   - Rotate secrets periodically in production

5. **Authorization**:
   - Validate user permissions for each sensitive operation
   - Implement proper role checks on endpoints

## Environment Setup

### Required Environment Variables

The application uses environment variables for configuration. Copy `.env.example` to `.env` and adjust the values:

```bash
# Basic environment variables explanation:
PORT=8080                    # Application port
DB_URL=jdbc:mysql://...      # Database URL
DB_USERNAME=username         # Database username
DB_PASSWORD=password         # Database password
JWT_SECRET=secretkey         # JWT signing key (must be strong in production)
JWT_EXPIRATION=86400000      # JWT expiration in ms (24h)
CORS_ALLOWED_ORIGINS=http://localhost:3000  # Frontend URL
```

See `.env.example` for a complete list of available configurations.

### Setting Up for Different Environments

1. **Development**:
   - Use default settings in `.env.example`
   - Local database with simple credentials

2. **Testing**:
   - Use separate test database
   - Mock sensitive services

3. **Production**:
   - Use strong, unique secrets
   - Configure proper CORS for production domains
   - Set up HTTPS
   - Use a secret management solution

## Troubleshooting

### Common Issues

1. **Invalid Token Errors**:
   - Check if the token has expired
   - Verify that the token is correctly formed
   - Ensure the JWT_SECRET is consistent

2. **Authentication Failed**:
   - Verify credentials
   - Check database connection
   - Inspect server logs for detailed errors

3. **CORS Errors**:
   - Make sure your frontend origin is listed in allowed origins
   - Check if the requests include proper headers

### Getting Help

For additional help or to report security issues, please contact the security team at [security@example.com](mailto:security@example.com).

