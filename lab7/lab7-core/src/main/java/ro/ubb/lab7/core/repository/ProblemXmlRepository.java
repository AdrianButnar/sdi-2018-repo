package ro.ubb.lab7.core.repository;

import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.validators.EntityPresentException;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;
import ro.ubb.laboratory.util.XmlReaderProblem;
import ro.ubb.laboratory.util.XmlWriterProblem;

import java.util.List;
import java.util.Optional;

public class ProblemXmlRepository extends InMemoryRepository<Long,Problem> {
    private String fileName;

    public ProblemXmlRepository(String filenName,Validator<Problem> validator) {
        super(validator);
        this.fileName = filenName;
        loadData();
    }

    private void loadData() {
        List<Problem> problems = new XmlReaderProblem<Long,Problem>(fileName).loadEntities(); //aici trebuie updatat
        for (Problem problem : problems){
            try{
                this.save(problem);
            }
            catch (ValidatorException ex){
                ex.printStackTrace();
            }
        }
    }
    @Override
    public Optional<Problem> save(Problem entity) throws EntityPresentException {
        Optional<Problem> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XmlWriterProblem<Long, Problem>(fileName).writeToFile(entity,super.findAll());
        return optional;
    }
}
