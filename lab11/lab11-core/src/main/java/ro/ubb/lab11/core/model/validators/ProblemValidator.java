package ro.ubb.lab11.core.model.validators;

import ro.ubb.lab11.core.model.Problem;

public class ProblemValidator implements Validator<Problem> {

    @Override
    public void validate(Problem s) throws ValidatorException {

        String message = "";

        //s.getName().chars().filter((c)->{c != ''})

        if (s.getId() < 0)
            message = message + "Problem cannot have a negative id!\n";

        if (s.getNumber() == 0)
            message = message + "Problem should have a number!\n";

        if (s.getText().equals(""))
            message = message + "Problem text cannot be left empty!\n";


        if (!(message.equals("")))
            throw new ValidatorException(message);

    }
}
