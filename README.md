# 📚 Library Management System

<div align="center">

![GitHub stars](https://img.shields.io/github/stars/yasith-1/javaFX-library-management-system?style=for-the-badge&logo=github&color=yellow)
![GitHub forks](https://img.shields.io/github/forks/yasith-1/javaFX-library-management-system?style=for-the-badge&logo=github&color=blue)
![GitHub issues](https://img.shields.io/github/issues/yasith-1/javaFX-library-management-system?style=for-the-badge&logo=github&color=red)
![GitHub license](https://img.shields.io/github/license/yasith-1/javaFX-library-management-system?style=for-the-badge&logo=github&color=green)

<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif" width="100%">

</div>

## 📋 Overview

A comprehensive **Library Management System** built with JavaFX that provides complete CRUD operations for managing library resources. This system streamlines library operations with an intuitive interface and robust functionality.

### ✨ Key Features

- 📖 **Book Management** - Add, update, delete, and search books
- 👥 **Member Management** - Handle member registrations and profiles  
- ✍️ **Author Management** - Organize author information and their works
- 🏷️ **Category Management** - Classify books by categories
- 💰 **Fine Management** - Track and manage library fines
- 📊 **Report Generation** - Generate detailed reports with download options
- 🔍 **Advanced Search** - Quick and efficient search functionality
- 💾 **Data Persistence** - Reliable database integration

---

## 🚀 Quick Start

### Prerequisites

- ☕ **Java 11+** 
- 🛠️ **Maven 3.6+**
- 💾 **MySQL Database**

### 📥 Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yasith-1/javaFX-library-management-system.git
   cd javaFX-library-management-system
   ```

2. **Configure Database:**

![2025-06-27 (LMS)](https://github.com/user-attachments/assets/d590203a-b255-40d4-801c-ffa0e2aa3b83)


4. **Build and Run:**
   ```bash
   mvn clean install
   ```

---

## 🛠️ Technology Stack

<div align="center">

| Technology | Purpose | Version |
|------------|---------|---------|
| ![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white) | Frontend Framework | Latest |
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) | Backend Language | 22+ |
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white) | Database | 8.0+ |
| ![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white) | Build Tool | 3.6+ |

</div>

---

## 📱 Application Screenshots

<div align="left">
<details>
<summary>🖼️ Main Dashboard</summary>
  
![dashboard](https://github.com/user-attachments/assets/8e15fecd-aeed-4900-909b-7956f1baba57)

</details>

<details>

<summary>🖼️ Login </summary>

![login](https://github.com/user-attachments/assets/fe3ac46d-d998-4943-be68-8e04d9953554)

</details>

<details>

<summary>🖼️ Add Book </summary>

![add-book](https://github.com/user-attachments/assets/2483ab7f-00ed-40b8-b455-815bf41ce93d)

</details>

<details>

<summary>🖼️ Manage Book </summary>

![manage-book](https://github.com/user-attachments/assets/77ab1184-0ebf-485c-bf2a-83f8328787cf)

</details>

<details>

<summary>🖼️ Manage Categories </summary>

![manage-categories](https://github.com/user-attachments/assets/c67696a9-ff23-4a89-904c-40b17172cfb8)

</details>

<details>

<summary>🖼️ Manage Authors </summary>

![manage-author](https://github.com/user-attachments/assets/203ed4f5-26e9-4c03-8364-c6057580dcd8)

</details>

<details>

<summary>🖼️ Manage Members </summary>

![manage-member](https://github.com/user-attachments/assets/3129f016-04a8-4f3f-9b0c-ac84013b53d6)

</details>

<details>

<summary>🖼️ Issue Book </summary>

![issue-book](https://github.com/user-attachments/assets/6ecd9872-6bae-4bec-9ecc-c648e043c045)

</details>

<details>

<summary>🖼️ Manage Issue Book </summary>

![manage-issue-book](https://github.com/user-attachments/assets/6a8a7246-5772-4043-816a-3819a5d4241e)

</details>

<details>

<summary>🖼️ Manage Return Book </summary>

![return-book](https://github.com/user-attachments/assets/6d50a175-2671-4261-a28c-d93ee0a2d010)

</details>

<details>

<summary>🖼️ Make Fine </summary>

![fine](https://github.com/user-attachments/assets/9583180b-453d-48f3-a176-de250d04ed70)

</details>

<details>

<summary>🖼️ Non-paid Members </summary>

![non-paid-members](https://github.com/user-attachments/assets/8b5d7d01-2c9d-432b-a98c-2c16c3c0da83)

</details>

<details>

<summary>🖼️ Report Center </summary>

![report-center](https://github.com/user-attachments/assets/148ad5fa-75fb-4b95-9edb-cd355f1af733)

</details>


*Clean and intuitive interface for easy navigation*

</div>

---

## 📁 Project Structure

```
📦 javaFX-library-management-system/
├── 📜 .gitignore
├── 📁 .idea/
│   ├── 📜 .gitignore
│   ├── 📜 encodings.xml
│   ├── 📜 misc.xml
│   └── 📜 vcs.xml
├── 📜 pom.xml
└── 📁 src/
    └── 📁 main/
        ├── 📁 java/
        │   ├── 🚨 alert/              # Alert utilities
        │   ├── 🎮 controller/         # UI Controllers
        │   ├── 💾 database/           # Database configuration
        │   ├── 📊 dto/                # Data Transfer Objects
        │   ├── 🏗️ entity/             # Entity classes
        │   ├── 📚 repository/         # Data access layer
        │   │   └── 🔧 custom/
        │   │       └── ⚙️ impl/
        │   ├── 🔧 service/            # Business logic
        │   │   └── 🔧 custom/
        │   │       └── ⚙️ impl/
        │   ├── 🛠️ util/               # Utility classes
        │   ├── 🏁 Main.java           # Application entry point
        │   └── 🚀 Starter.java        # Application starter
        └── 📁 resources/
            ├── 📊 er-diagram/         # Database diagrams
            ├── 🖼️ image/              # Application images
            ├── 📋 reports/            # Report templates
            └── 🎨 view/               # FXML files
```

---

## 🎯 Core Functionalities

<table>
<tr>
<td width="50%">

### 📚 Library Operations
- ➕ Add new books to inventory
- ✏️ Edit existing book details
- 🗑️ Remove books from system
- 🔍 Search books by various criteria
- 📋 View detailed book information

</td>
<td width="50%">

### 👥 Member Services  
- 📝 Register new members
- 👤 Update member profiles
- 📊 Track borrowing history
- 💳 Manage membership status
- 🔔 Send notifications

</td>
</tr>
<tr>
<td width="50%">

### 📊 Reporting & Analytics
- 📈 Generate usage reports
- 💾 Export data to various formats
- 📅 Track library statistics
- 📋 Create custom reports
- 📊 Visual data representation

</td>
<td width="50%">

### 🔧 System Management
- ⚙️ User access control
- 🛡️ Data backup and restore
- 🔍 System monitoring
- 📱 Responsive design
- 🚀 Performance optimization

</td>
</tr>
</table>

---

## 📞 Contact & Support

<div align="center">

### 👨‍💻 Developer : Yashith Prabhashwara

[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:yasithprabaswara1@gmail.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/yashith-prabhashwara-a1aa471a6/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/yasith-1)

</div>

---

## 🙏 Acknowledgments

- Thanks to all contributors who helped improve this project
- Special thanks to the JavaFX community for excellent documentation
- Inspired by modern library management needs

---

<div align="center">

### 🌟 If you found this project helpful, please give it a star! 🌟

<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&height=80&section=footer"/>

**Made with ❤️ by [Yasith Prabaswara](https://github.com/yasith-1)**

</div>
