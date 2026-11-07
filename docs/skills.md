# skills.md

# Adam's Engineering Profile

## Primary Stack

* Java (advanced)
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* PostgreSQL
* Apache Kafka
* Docker
* Testcontainers
* Maven
* Git
* IntelliJ IDEA
* MapStruct

## Development Philosophy

I value:

* Clean Architecture
* Domain Driven Design where it brings value
* Simple solutions over clever solutions
* Readable and maintainable code
* Business-oriented design
* High testability
* Explicit business rules
* SOLID principles used pragmatically

Avoid unnecessary abstractions and overengineering.

## Code Style

Prefer:

* Constructor injection
* Immutable DTOs (Java records)
* Bean Validation
* MapStruct for mapping
* Transaction boundaries in the service layer
* Rich domain model when justified
* Small focused classes
* Meaningful method names

Avoid:

* Field injection
* God classes
* Deep inheritance
* Premature optimization
* Excessive design patterns

## Architecture

I prefer microservices.

Typical architecture:

* API Gateway
* Auth Service
* Order Service
* Kafka communication
* Outbox Pattern
* JWT authentication
* PostgreSQL per service

I am interested in:

* Event-driven architecture
* Distributed systems
* Reliability
* Scalability
* Security

## Testing

Prefer:

* Unit tests
* Integration tests
* Testcontainers
* Kafka integration tests
* MockMvc
* Mockito

When proposing tests:

* Explain why each test exists.
* Focus on business scenarios.
* Cover edge cases.

## How to Explain Things

Assume I already know Java and Spring.

Do NOT spend time explaining basics.

Instead:

* Explain trade-offs.
* Explain why a solution is good.
* Explain when it becomes a bad solution.
* Mention common production pitfalls.
* Give real-world examples.

Challenge my assumptions if there is a better approach.

## Code Reviews

When reviewing my code:

* Be strict.
* Point out architectural issues.
* Suggest improvements.
* Explain consequences.
* Do not accept mediocre solutions.

## Business Analysis

When discussing features:

* Start from business rules.
* Separate business logic from technical implementation.
* Identify edge cases.
* Consider future extensibility.

## Career Goals

Current interests:

* Senior Backend Engineering
* System Design
* Event-Driven Architecture
* AI integration in Java
* Spring AI
* SaaS products
* Automation platforms powered by AI

## Preferred Responses

I prefer answers that are:

* Practical
* Detailed
* Production-oriented
* Based on real experience
* Focused on maintainability

Do not simplify problems unnecessarily.

Treat me as an experienced backend developer who wants senior-level discussions rather than beginner tutorials.

## Filozofia projektu API Gateway / ekosystemu mikroserwisów

Projekty typu API Gateway, Auth Service, Config Server, Discovery Server itd. w ramach "Shop" to świadomy sandbox do nauki wzorców Spring Cloud i budowy realistycznego systemu produkcyjnego — nie MVP z ograniczonym budżetem.

Dlatego:

* Nie odradzaj wzorca tylko dlatego, że jest "za duży" na 2-3 serwisy. Jeśli świadomie chcę odtworzyć system produkcyjny (Config Server, Service Discovery, Circuit Breaker, Load Balancer), to jest to celowe, nie overengineering.
* Możesz i powinieneś zaznaczyć, kiedy coś nie jest jeszcze potrzebne do samej nauki danego wzorca w danej fazie — ale finalną decyzję o tempie/kolejności zostaw mnie.
* Traktuj to jako środowisko do eksperymentowania z production-grade patterns, nie jako produkt komercyjny.
