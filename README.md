# 🏨 Hotel Management System – Java, JDBC, MySQL

A **console-based hotel reservation system** that allows users to book, update, view, and delete room reservations using a dynamic CLI interface. \
Built with **Java and JDBC**, integrated with **MySQL** for data persistence.

---

## 🚀 Features

- ✅ Reserve a room by entering guest details and room number  
- 🔍 View all current reservations in a clean tabular format  
- 🔐 Retrieve room number using reservation ID and guest name  
- ✏️ Update existing reservations with new details  
- 🗑️ Delete reservations securely  
- 💾 MySQL database integration using JDBC  
- 🖥️ User-friendly console interface with input validation

---

## 🛠️ Tech Stack

- **Language**: Java  
- **Database**: MySQL  
- **Connector**: JDBC  
- **IDE**: Eclipse / IntelliJ  
- **Design**: Modular methods for each feature

---

## 🧪 Sample Table Structure (`setup.sql`)

```sql
CREATE TABLE RESERVATIONS (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  GUEST VARCHAR(50),
  ROOM INT,
  CONTACT VARCHAR(20),
  DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
---
## 💡 Future Enhancements

🌐 GUI-based frontend using JavaFX or Swing

📅 Room availability calendar

📊 Reports & analytics dashboard

📩 Email/SMS reservation confirmation system

---
## 👩‍💻 Author
Janavi Gopu

🔗 [Github](github.com/janavigopu/HotelManagementSystem)

🔗 [LinkedIn](https://www.linkedin.com/in/janavig/)

---
## 📜 License
This project is open-source and available under the [MIT License]().
