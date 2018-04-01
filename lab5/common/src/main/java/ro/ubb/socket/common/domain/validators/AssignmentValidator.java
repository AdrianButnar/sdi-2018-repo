package ro.ubb.socket.common.domain.validators;


import ro.ubb.socket.common.domain.Assignment;

public class AssignmentValidator implements Validator<Assignment> {

    @Override
    public void validate(Assignment ass) throws ValidatorException {

        String message = "";

        if (ass.getId() < 0)
            message = message + "Assignment cannot have a negative id!\n";

        if (ass.getProblemID() < 0)
            message = message + "Assignment cannot have a negative problem id!\n";

        if (ass.getStudentID() < 0)
            message = message + "Assignment cannot have a negative student id!\n";

        if (!(message.equals("")))
            throw new ValidatorException(message);

    }
}