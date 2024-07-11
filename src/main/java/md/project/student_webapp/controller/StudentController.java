package md.project.student_webapp.controller;

import md.project.student_webapp.model.Student;
import md.project.student_webapp.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentsById(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentService.getById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(optionalStudent.get());
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> opStudent = studentService.getById(id);

        if (opStudent.isPresent()) {
            studentService.updateStudent(id, student);
            return ResponseEntity.ok().body(opStudent.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable Long id) {
        studentService.delete(id);
    }
}
