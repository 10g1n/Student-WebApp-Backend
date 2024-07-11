package md.project.student_webapp.service;

import md.project.student_webapp.model.Student;
import md.project.student_webapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public boolean updateStudent(Long id, Student student) {
        Optional<Student> prevStudent = studentRepository.findById(id);

        if (prevStudent.isPresent()) {
            prevStudent.get().setName(student.getName());
            prevStudent.get().setSurname(student.getSurname());
            prevStudent.get().setStudentClass(student.getStudentClass());
            prevStudent.get().setAge(student.getAge());

            studentRepository.save(prevStudent.get());

            return true;
        } else {
            return false;
        }
     }

     public void delete(Long id) {
        studentRepository.deleteById(id);
     }
}
