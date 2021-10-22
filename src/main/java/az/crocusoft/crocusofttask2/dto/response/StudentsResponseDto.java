package az.crocusoft.crocusofttask2.dto.response;


import lombok.ToString;

import java.util.List;

@ToString
public class StudentsResponseDto {
    String name;
    String surname;
    String email;

    List<CoursesResponseDto> courses;

    public StudentsResponseDto(String name,
                               String surname,
                               String email,
                               List<CoursesResponseDto> courses) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CoursesResponseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesResponseDto> courses) {
        this.courses = courses;
    }

    public StudentsResponseDto() {
    }
}
