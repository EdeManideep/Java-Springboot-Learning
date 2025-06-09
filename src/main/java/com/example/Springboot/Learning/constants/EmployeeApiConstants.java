package com.example.Springboot.Learning.constants;

public class EmployeeApiConstants {
    // Common
    public static final String ERROR_INVALID_ID = "Id must be a valid integer value";
    public static final String RESPONSE_INVALID_ID = "Invalid ID format";
    public static final String PARAM_PAGE_NUMBER_MIN = "Page number must be 0 or greater";
    public static final String PARAM_PAGE_NUMBER_MIN_SIZE = "Page size must be at least 1";

    // Get all employees constants
    public static final String GET_ALL_EMPLOYEES_SUMMARY = "Get all employees with pagination";
    public static final String GET_ALL_EMPLOYEES_DESCRIPTION = "Returns a paginated list of employees with optional sorting by column and direction";

    public static final String RESPONSE_EMPLOYEES_LIST_SUCCESS = "Successfully retrieved list";
    public static final String RESPONSE_EMPLOYEES_NOT_FOUND = "Employees not there (No Content)";
    public static final String RESPONSE_INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String MESSAGE_NO_EMPLOYEES = "No Employees Present";
    public static final String PARAM_SORT_BY_DESC = "Column name to sort by";
    public static final String PARAM_SORT_DIRECTION_DESC = "Sort direction: asc or desc";
    public static final String PARAM_PAGE_NUMBER_DESC = "Page number (zero-based index)";
    public static final String PARAM_PAGE_SIZE_DESC = "Number of records per page";
    public static final String LOG_EMPLOYEES_FETCHING = "Fetching all employees - page {}, size {}, sort by {}, direction {}";
    public static final String LOG_EMPLOYEES_NOT_FOUND = "No employees found for the given page/filters.";

    // Get employee Id
    public static final String GET_EMPLOYEE_SUMMARY = "Get employee by ID";
    public static final String GET_EMPLOYEE_DESCRIPTION = "Fetch a single employee using their ID";
    public static final String RESPONSE_EMPLOYEE_FOUND = "Employee found";
    public static final String RESPONSE_EMPLOYEE_NOT_FOUND = "Employee not there with given id (No Content)";
    public static final String PARAM_EMPLOYEE_ID_DESC = "ID of the employee to be fetched";

    // Create employee constants
    public static final String CREATE_EMPLOYEE_SUMMARY = "Create a new employee";
    public static final String CREATE_EMPLOYEE_DESCRIPTION = "Adds a new employee using the provided data";
    public static final String RESPONSE_EMPLOYEE_CREATED = "Employee successfully created";
    public static final String RESPONSE_INVALID_INPUT = "Invalid input data";
    public static final String RESPONSE_INTERNAL_ERROR = "Internal server error";
    public static final String PARAM_CREATE_EMPLOYEE_DESC = "Employee data for creation";

    // Update employee constants
    public static final String UPDATE_EMPLOYEE_SUMMARY = "Update an existing employee";
    public static final String UPDATE_EMPLOYEE_DESCRIPTION = "Updates employee details based on the provided ID";
    public static final String RESPONSE_EMPLOYEE_UPDATED = "Employee updated successfully";
    public static final String RESPONSE_EMPLOYEE_NOT_FOUND_UPDATE = "Employee not there with given id to update (No Content)";
    public static final String PARAM_EMPLOYEE_ID_UPDATE_DESC = "ID of the employee to update";
    public static final String PARAM_EMPLOYEE_UPDATE_DATA_DESC = "Updated employee data";

    // Delete employee constants
    public static final String DELETE_EMPLOYEE_SUMMARY = "Delete employee by ID";
    public static final String DELETE_EMPLOYEE_DESCRIPTION = "Removes an employee from the system using their ID";
    public static final String RESPONSE_EMPLOYEE_DELETED = "Employee deleted successfully";
    public static final String RESPONSE_EMPLOYEE_NOT_FOUND_DELETE = "Employee not there with given id to delete (No Content)";
    public static final String PARAM_EMPLOYEE_ID_DELETE_DESC = "ID of the employee to delete";

    // Search employee by name constants
    public static final String SEARCH_EMPLOYEE_SUMMARY = "Search employees by name";
    public static final String SEARCH_EMPLOYEE_DESCRIPTION = "Returns a list of employees whose names match the given keyword (case-insensitive)";
    public static final String RESPONSE_EMPLOYEE_SEARCH_SUCCESS = "Successfully retrieved matching employees";
    public static final String RESPONSE_EMPLOYEE_SEARCH_NOT_FOUND = "No employees found with the given name";
    public static final String PARAM_EMPLOYEE_NAME_SEARCH_DESC = "Name to search";
    public static final String MESSAGE_EMPLOYEE_SEARCH_NOT_FOUND = "No matching employees found";
    public static final String LOG_EMPLOYEE_SEARCH = "Searching employees with name containing: {}";
    public static final String LOG_EMPLOYEE_SEARCH_NOT_FOUND = "No employees found matching name: {}";
    public static final String RESPONSE_EMPLOYEE_NOT_FOUND_SEARCH_BY_NAME = "Employees not there (No Content)";
}
