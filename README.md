# E-commerce-backend
this is a backend code base for a e-commerce. Built using spring boot

# Order and Product Management API

A RESTful API built with Spring Boot for managing inventory (products) and processing customer transactions (orders). 

## Technology Stack
* **Language:** Java
* **Framework:** Spring Boot (Spring Web)
* **Database / ORM:** Spring Data JPA, Hibernate, Jakarta Persistence
* **Utilities:** Lombok, FasterXML Jackson (JSON Serialization)
* **Validation:** Jakarta Bean Validation

## Entity Architecture
* **Product:** Stores inventory details including category, price, and available stock.
* **Order:** Tracks customer transactions, status, and total price. Includes an auto-generated timestamp (`@PrePersist`).
* **OrderItem:** Acts as a junction table between Orders and Products, locking in the quantity and `priceAtPurchase` at the time of the transaction.

## API Endpoints

### Products (`/api/products`)
| Method | Endpoint | Description | Request Body |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/products` | Retrieve a list of all products | None |
| `GET` | `/api/products/{id}` | Retrieve a specific product by its ID | None |
| `POST` | `/api/products` | Create a new product | `Product` JSON |
| `PUT` | `/api/products/{id}` | Update an existing product by ID | `Product` JSON |
| `DELETE`| `/api/products/{id}` | Delete a product by its ID | None |

### Orders (`/api/orders`)
| Method | Endpoint | Description | Request Body |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/orders` | Create a new order | `OrderRequest` JSON |

## Data Validation Rules
**Product Constraints:**
* `name`: Must not be blank.
* `price`: Required, must be strictly greater than 0.0.
* `stockQuantity`: Required, must be 0 or greater.

**Order Constraints:**
* `customerName`: Cannot be null.
* `customerEmail`: Cannot be null.
* `status` (mapped as `staus`): Cannot be null.
* `totalPrice`: Cannot be null.

## Serialization Notes
* Entity relations utilize `@JsonManagedReference` on the `Order` side and `@JsonBackReference` on the `OrderItem` side to prevent infinite recursion during JSON serialization. 
* The `Product` entity uses `@JsonIgnore` on its `orderItems` list to omit relational order data when fetching product details.
