## Email Microservice Project

This repository contains the source code of an email microservice developed with Spring Boot. The microservice follows a Clean Architecture-like approach, with well-defined layers for separation of concerns. It offers functionalities for sending, querying, and managing emails.

### Key Features

- **Send Email**: You can send emails by providing information such as sender, recipient, subject, and body.

- **Query Email by ID**: Retrieve detailed information about an email based on its ID.

- **Query Emails by Owner**: Get a list of emails belonging to a specific owner.

- **List All Emails**: Retrieve a list of all emails stored in the system.

### Architecture

The project follows a clean architecture approach to maintain the separation of concerns and facilitate code maintenance and evolution. It consists of distinct layers:

- **EntryPoint**: REST controllers that handle HTTP requests, mapping them to use cases.

- **UseCase**: Classes that implement the application's business logic.

- **Entity**: Representations of domain objects of the application, such as the `Email` entity.

- **Contract**: Interfaces that define contracts for external components, such as repositories and email gateways.

- **Infrastructure**: Concrete implementations of external contracts, such as email sending using AWS.

- **Data**: Repository implementations that handle data persistence in the database.

### Configuration

The project is configured to use a PostgreSQL database and AWS for email sending. Configuration settings can be found in the `application.properties` file.

### How to Use

To use this email microservice, follow these steps:

1. Clone this repository.

2. Configure the PostgreSQL database and AWS credentials in the `application.properties` file.

3. Run the project using Spring Boot.

4. Access the following URLs to access the functionalities:
   - Send Email: `POST /ms/sending-email`
   - Query Email by ID: `GET /ms/{id}`
   - Query Emails by Owner: `GET /ms/owner/{owner}`
   - List All Emails: `GET /ms`

### Contribution

If you want to contribute to this project, feel free to submit pull requests or report issues. All contributions are welcome!

### License

This project is licensed under the [MIT License](LICENSE).

---

Thank you for using the Email Microservice! If you have any questions or need assistance, don't hesitate to get in touch.
