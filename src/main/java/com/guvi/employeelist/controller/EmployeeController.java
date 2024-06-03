package com.guvi.employeelist.controller;


import com.guvi.employeelist.entity.Employee;
import com.guvi.employeelist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //handler for home page
    @GetMapping("/")
    public String index(Model model) {
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("empList", list);
        return "index";
    }

    //handler to add a New employee
    @GetMapping("/NewEmployeeForm/{id}")
    public String NewEmployeeForm(Model model) {
        //create an employee object
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        //resolve thymleaf template
        return "new_employee";
    }

    //save the data of employee->post method-
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        //redirect to home page
        return "redirect:/";

    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee"; // Thymeleaf template name
    }


    @PostMapping("/updateEmployees/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee updatedEmployee) {
        employeeService.updateEmployee(id, updatedEmployee);
        return "redirect:/"; // Redirect to employee list page
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }


    @GetMapping("/viewEmployee/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        // Retrieve the employee by ID
        Employee employee = employeeService.getEmployeeById(id);
        // Add the employee to the model to pass it to the view
        model.addAttribute("employee", employee);
        // Return the name of the view to render
        return "view_employee";
    }
}









