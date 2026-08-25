# 💱 Currency Exchange API

A REST API built with **Java and Spring Boot** that converts currencies using real-time exchange rates from an external API.

This project was developed to practice REST API development, external API integration with `RestClient`, DTOs, exception handling, input validation, and clean backend architecture.

---

## 📑 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [How It Works](#-how-it-works)
- [API Endpoint](#-api-endpoint)
- [Error Handling](#-error-handling)
- [How to Run](#-how-to-run)
- [Technologies](#-technologies)
- [Author](#-author)
- [Next Steps](#-next-steps)

---

## 🚀 Features

- Currency conversion between supported currencies
- Real-time exchange rates
- Integration with an external exchange rate API
- Currency code normalization
- Input validation
- Global exception handling
- Standardized error responses
- External API error handling
- API key stored securely using environment variables

---

## 🏗 Architecture

The application follows a layered structure:

```text
Client Request
      ↓
Controller
      ↓
Service
      ↓
ExchangeRateClient
      ↓
External Exchange Rate API
```

### Controller

Responsible for receiving HTTP requests and extracting the request parameters.

### Service

Contains the business logic, input validation, and currency conversion logic.

### Client

Responsible for communicating with the external exchange rate API using Spring `RestClient`.

### DTO

Defines the data exchanged between the external API, application layers, and client.

### Exception

Contains custom exceptions and the global exception handler responsible for standardized API error responses.

---

## ⚙️ How It Works

The client sends:

```http
GET /api/exchange/convert?from=USD&to=BRL&amount=100
```

The application:

1. Receives the request.
2. Validates the input parameters.
3. Normalizes the currency codes.
4. Requests the exchange rates from the external API.
5. Retrieves the rate for the target currency.
6. Calculates the converted amount.
7. Returns a structured response.

Example response:

```json
{
  "from": "USD",
  "to": "BRL",
  "amount": 100,
  "rate": 5.19,
  "convertedAmount": 519.00,
  "lastUpdatedAt": "Fri, 21 Aug 2026 00:00:01 +0000"
}
```

---

## 🔗 API Endpoint

### Convert Currency

```http
GET /api/exchange/convert
```

### Query Parameters

| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `from` | String | Yes | Source currency code |
| `to` | String | Yes | Target currency code |
| `amount` | BigDecimal | Yes | Amount to convert |

Example:

```http
GET http://localhost:8080/api/exchange/convert?from=USD&to=BRL&amount=100
```

---

## ⚠️ Error Handling

The API uses a global exception handler to provide standardized error responses.

Example:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Currency not found: ABC",
  "path": "/api/exchange/convert",
  "timestamp": "2026-08-25T17:02:44"
}
```

Handled scenarios include:

- Invalid source currency
- Invalid target currency
- Empty currency parameters
- Missing request parameters
- Invalid amount type
- Amount less than or equal to zero
- External API server errors
- Unexpected internal server errors

### HTTP Status Codes

| Status | Description |
|--------|-------------|
| `200 OK` | Currency conversion completed successfully |
| `400 Bad Request` | Invalid input or request parameters |
| `502 Bad Gateway` | Error received from the external API |
| `500 Internal Server Error` | Unexpected internal application error |

---

## 💻 How to Run

### Prerequisites

Make sure you have installed:

- Java 21+
- Maven
- Git

### 1. Clone the repository

```bash
git clone YOUR_REPOSITORY_URL
```

Enter the project directory:

```bash
cd currencyexchange
```

### 2. Configure the API key

The project uses an environment variable to protect the external API key.

On macOS/Linux:

```bash
export EXCHANGE_API_KEY=your_api_key
```

Do not commit your real API key to GitHub.

### 3. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

### 4. Test the API

You can test it using Postman or another HTTP client:

```http
GET http://localhost:8080/api/exchange/convert?from=USD&to=BRL&amount=100
```

---

## 🛠 Technologies

- Java
- Spring Boot
- Spring Web
- Spring RestClient
- Jackson
- Maven
- ExchangeRate API
- Postman
- Git
- GitHub

---

## 👨‍💻 Author

**Pedro Rodrigo**

Fullstack developer focused on Java and Spring Boot.

GitHub: `pedrorodrigomartins`

---

## 🔮 Next Steps

Possible future improvements:

- Unit tests with JUnit and Mockito
- Integration tests
- Swagger / OpenAPI documentation
- Docker support
- Caching exchange rates
- Improved logging
- API monitoring
- Request rate limiting

---

## 📚 What I Learned

During the development of this project, I practiced:

- Building REST APIs with Spring Boot
- Consuming external APIs with `RestClient`
- Working with DTOs and Java Records
- JSON mapping with Jackson
- Working with `BigDecimal`
- Using environment variables for sensitive data
- Handling HTTP status codes
- Creating custom exceptions
- Implementing global exception handling with `@RestControllerAdvice`
- Validating request parameters
- Separating responsibilities between Controller, Service, and Client layers