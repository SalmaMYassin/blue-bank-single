# Blue Bank Project

A Small Bank Backend Assignment

## To run project:

```bash
docker-compose up
```
_ps. make sure port 8080 on your localhost is available_

Added CI/CD on GitHub Actions

## Exposed APIs:

### Customer APIs:

**Create Customer:**

Path: **POST
/api/v1/customer**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Salma",
    "lastName": "Yassin",
    "email": "salmamyassinn@gmail.com"
}'
```
status: `201 Created`

**Get Customer's Data By Email:**

Path: **GET
/api/v1/customer?email={email}**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/customer?email=salmamyassinn%40gmail.com'
```
status: `200 OK`

```json
{
    "id": 1,
    "firstName": "Salma",
    "lastName": "Yassin",
    "email": "salmamyassinn@gmail.com",
    "accounts": [
        {
            "type": "SAVINGS",
            "createdAt": "2023-03-12T05:52:38.399335",
            "balance": 300.00,
            "transactions": [
                {
                    "id": 1,
                    "createdAt": "2023-03-12T05:52:38.422961",
                    "amount": 300.00,
                    "type": "DEPOSIT"
                }
            ],
            "id": 1
        }
    ]
}
```

**Get Customer's Data By Id:**

Path: **GET
/api/v1/customer/{id}**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/customer/1'
```
status: `200 OK`

```json
{
    "id": 1,
    "firstName": "Salma",
    "lastName": "Yassin",
    "email": "salmamyassinn@gmail.com",
    "accounts": [
        {
            "type": "SAVINGS",
            "createdAt": "2023-03-12T05:52:38.399335",
            "balance": 300.00,
            "transactions": [
                {
                    "id": 1,
                    "createdAt": "2023-03-12T05:52:38.422961",
                    "amount": 300.00,
                    "type": "DEPOSIT"
                }
            ],
            "id": 1
        }
    ]
}
```

### Account APIs:

**Create Account:**

Path: **POST
/api/v1/account**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/account' \
--header 'Content-Type: application/json' \
--data '{
    "customerId":1,
    "initialCredit": 9000,
    "type": "SAVINGS"
}'
```
status: `201 Created`

**Deposit to Account:**

Path: **POST
/api/v1/account/deposit?accountId={id}&amount={amount}**

Example:
```bash
curl --location --request POST 'host.docker.internal:8080/api/v1/account/deposit?accountId=52&amount=400'
```
status: `200 OK`

**Withdraw from Account**

Path: **POST
/api/v1/account/withdraw?accountId={id}&amount={amount}**

Example:
```bash
curl --location --request POST 'host.docker.internal:8080/api/v1/account/withdraw?accountId=52&amount=1000'
```
status: `200 OK`

**Get Customer Accounts By CustomerId:**

Path: **GET
/api/v1/account/{id}**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/account/1'
```
status: `200 OK`

```json
[
    {
        "type": "SAVINGS",
        "createdAt": "2023-03-12T03:25:48.803672",
        "balance": 8000.00,
        "transactions": [
            {
                "id": 52,
                "createdAt": "2023-03-12T03:25:48.923193",
                "amount": 9000.00,
                "type": "DEPOSIT"
            },
            {
                "id": 53,
                "createdAt": "2023-03-12T03:26:52.349793",
                "amount": 1000.00,
                "type": "WITHDRAW"
            }
        ],
        "id": 52
    }
]
```

**Get Account Balance:**

Path: **GET
/api/v1/account/{id}**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/account/balance/52'
```
status: `200 OK`

```json
8000.00
```

### Transaction APIs:

**Get Account's Transactions by AccountId (Pageable):**

Path: **GET
/api/v1/transaction?accountId={id}&page={page}&size={size}**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/transaction?accountId=52&page=0&size=4'
```
status: `200 OK`

```json
{
  "content": [
    {
      "id": 52,
      "createdAt": "2023-03-12T03:25:48.923193",
      "amount": 9000.00,
      "type": "DEPOSIT"
    },
    {
      "id": 53,
      "createdAt": "2023-03-12T03:26:52.349793",
      "amount": 1000.00,
      "type": "WITHDRAW"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 4,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "size": 4,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

**Get Transaction By TransactionId:**

Path: **GET
/api/v1/transaction/{id}**

Example:
```bash
curl --location 'host.docker.internal:8080/api/v1/transaction/52'
```
status: `200 OK`

```json
{
  "id": 52,
  "createdAt": "2023-03-12T03:25:48.923193",
  "amount": 9000.00,
  "type": "DEPOSIT"
}
```

## Steps:
1. Register
2. Create Account
3. Deposit, withdraw, check account

## Could be improved:
1. Add authentication
2. Get the customer_id from the authenticated customer
3. Increase unit testcases coverage
4. Break project into microservices
5. Add API gateway to manage instances
6. CQRS


