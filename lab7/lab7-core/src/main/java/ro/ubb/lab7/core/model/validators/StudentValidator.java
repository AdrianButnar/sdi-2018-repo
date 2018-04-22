package ro.ubb.lab7.core.model.validators;

import ro.ubb.laboratory.domain.Student;

public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student s) throws ValidatorException {

        String message = "";

        if (s.getId()<0)
            message = message + "Student cannot have a negative id!\n";

        if(s.getName().equals(""))
            message = message + "Student name cannot be left empty!\n";

        if(s.getSerialNumber().equals(""))
            message = message + "Serial number cannot be left empty!\n";

        if (!(message.equals("")))
            throw new ValidatorException(message);

    }


}