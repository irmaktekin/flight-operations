# Seat Management and Reservation API

This project is a RESTful API developed for a flight reservation system. The API provides functionality for managing flights and seats, creating reservations, and handling existing bookings.

## Features

- **Flight Management**  
  Create, read, update, and delete (CRUD) operations for flights.

- **Seat Management**  
  Add, remove, and update seat information for flights.

- **Seat Reservation**  
  Reserve seats on flights while ensuring concurrency control to avoid double bookings.

- **Reservation Management**  
  Manage reservations, including viewing and canceling bookings.

## Technology Stack

- **Backend**: Java 11, Spring Boot  
- **Database**: H2
- **Build Tool**: Maven  
- **Other Tools**: Spring Data JPA, Hibernate

## Getting Started

### Prerequisites

- Java 11 or later
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/seat-reservation-api.git
   cd seat-reservation-api
