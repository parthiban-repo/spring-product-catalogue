# Microservices-based Ecommerce Core Functionalities built on SpringBoot and RESTful APIs

This is a sample project built on Spring Boot, following a microservices architecture and employing RESTful APIs for seamless communication between services. It encompasses core ecommerce functionalities, including product catalog management with CRUD operations and seamless payment processing integration via the Razorpay Payment Gateway.

## Features

### Product Catalog Services

- CRUD operations for managing products.
    - Create a new product
    - List all products with details
    - Get single product details
    - List all categories
    - List all products in specific category
    - Get all products with matching title keywords
    - Pagination: Get products by page size and page number with optional column sorting
    - Update product details
    - Delete a product

### Payment Gateway Integration

- Integration with Razorpay Payment Gateway for secure and seamless transactions.

### Caching with Redis Cache

Implemented Redis cache for the following API services:
- Create a product
- Get product details
- Update a product
- Delete a product

## Frameworks & Tools Used

- Spring Boot: Provides a framework for building Java-based microservices.
- Hibernate: Used for ORM (Object-Relational Mapping) to manage the database.
- MySQL: A relational database management system for storing data.
- Razorpay Payment Gateway: Integrated for secure and efficient payment processing.
- Redis: In-memory data structure store used for caching.
- Postman: API development and testing tool used for testing the RESTful endpoints.