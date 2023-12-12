# Hotel Management System (Java + JDBC)

This project is a comprehensive hotel management system implemented in Java using JDBC (Java Database Connectivity). It handles guest reservations, room management, billing, employee operations, and more.

## Project Structure

The project is organized into four packages:

- **Room**: Manages operations related to rooms.
- **Registration**: Handles check-in, check-out, and billing functionalities.
- **DisplayManager**: Contains display functions utilized by other classes.
- **EmployeeManager**: Manages employee-related operations like hiring, firing, and viewing employee details.

## Installation

1. **Clone the repository:**
git clone https://github.com/Guneeet23/hotel-management-system.git


2. **Setup Database:**
- Create a MySQL database.
- Import the provided SQL file (`project.sql`) to set up tables and initial data.

3. **Configure Database Connection:**
- Modify the JDBC connection details in the project files to match your database configuration.

4. **Compile and Run:**
- Compile the Java files.
- Run the main application file, `menu2.java`.

## Usage

1. **Login System**:
- The system supports two types of login:
  - **Admin Login**: Access administrative functionalities.
  - **Staff Login**: For regular staff members to perform routine tasks.

2. **Functionality**:
- **Admin Access**:
  - Manage room allocations, guest information, employee operations, etc.
- **Staff Access**:
  - Perform check-in, check-out, billing, and other guest-related operations.

## Contributing

Contributions to this project are welcome! Here's how you can contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make modifications and commit changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- Credits to contributors and inspirations from other projects.
- Special thanks to [Author Name] for [specific functionality/inspiration/etc.].

## Support

For questions, suggestions, or issues, please [create an issue](https://github.com/Guneeet23/java_project_sem3/issues).
