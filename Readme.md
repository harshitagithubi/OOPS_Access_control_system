# 🔐 Enterprise Access Control & Permission Management System

A production-ready authorization system demonstrating advanced Object-Oriented Programming principles and design patterns in Core Java.

## 🎯 Project Overview

This project implements a comprehensive **Role-Based Access Control (RBAC)** system that showcases:
- Clean architecture and SOLID principles
- Design patterns (Strategy, Dependency Injection, Factory)
- Enterprise-level authorization logic
- Comprehensive audit logging
- Extensible and scalable design

## 💡 OOPS Concepts Demonstrated

### Encapsulation
- All fields are private with controlled access
- Immutable objects for security (User, Role, Permission)

### Abstraction
- `AccessPolicy` interface for pluggable authorization strategies
- `Resource` abstract class for different resource types
- `AuditLogger` interface for flexible logging implementations

### Polymorphism
- Multiple `AccessPolicy` implementations (RBAC, ABAC future)
- Different `Resource` types (Document, Report, SystemConfig)

### Inheritance
- Resource hierarchy with specialized types
- Proper use of abstract classes

### Composition
- User HAS-A Set of Roles
- Role HAS-A Set of Permissions
- AccessManager HAS-A AccessPolicy and AuditLogger

## 🔧 Design Patterns Used

| Pattern | Implementation | Purpose |
|---------|---------------|---------|
| **Strategy** | `AccessPolicy` interface | Interchangeable authorization algorithms |
| **Dependency Injection** | `AccessManager` constructor | Loose coupling, testability |
| **Factory** | Resource creation | Centralized object creation |
| **Observer** | Audit logging | Event notification |

## 📦 Package Structure
```
com.accesscontrol/
├── model/           # Domain entities (User, Role, Permission)
├── policy/          # Authorization strategies
├── service/         # Business logic (AccessManager, UserManagement)
├── audit/           # Logging infrastructure
├── exception/       # Custom exceptions
└── demo/            # Demonstration application
```

## 🚀 How to Run

### Prerequisites
- Java 17 or higher
- VS Code with Extension Pack for Java

### Steps
1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/access-control-system.git
cd access-control-system
```

2. Open in VS Code
```bash
code .
```

3. Run the demo
- Navigate to `src/com/accesscontrol/demo/AccessControlDemo.java`
- Press `F5` or click "Run" above the main method

## 📊 Sample Output
```
=== Access Control System Demo ===

[ALLOWED] Admin John accessed SYSTEM_CONFIG for DELETE action
[DENIED] Guest Alice attempted to DELETE DOCUMENT - Access Denied
[ALLOWED] Manager Bob accessed REPORT for READ action

=== Audit Log ===
2024-12-24 10:30:15 | USER: admin-001 | ACTION: DELETE | RESOURCE: config-123 | RESULT: ALLOWED
2024-12-24 10:30:16 | USER: guest-002 | ACTION: DELETE | RESOURCE: doc-456 | RESULT: DENIED
```

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Professional Java project structure
- ✅ Clean Code principles
- ✅ SOLID design principles
- ✅ Real-world authorization patterns
- ✅ Exception handling best practices
- ✅ Documentation and code comments
- ✅ Git version control

## 🔮 Future Enhancements

- [ ] Attribute-Based Access Control (ABAC)
- [ ] Time-based permissions
- [ ] Resource ownership model
- [ ] Permission inheritance hierarchies
- [ ] JSON/XML configuration support

## 👨‍💻 Author

Harshita Gupta 
Harshitaigcs@gmail.com
