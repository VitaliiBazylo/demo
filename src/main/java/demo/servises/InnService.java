package demo.servises;

import com.example.demo.InnRepository;
import com.example.demo.model.UserInn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InnService {
    @Autowired
    InnRepository repository;
    @Autowired
    InnParser innParser;

    public InnParser.InnParsedResult userInnParsing(String inn) throws Exception {
        var userInn = InnParser.parsingInn(inn);
        repository.save(
                new UserInn(
                        userInn.getDateOfBirth(),
                        userInn.getGender(),
                        userInn.getRegNum(),
                        userInn.getZodiac(),
                        userInn.getLeapYear(),
                        userInn.getOld()
                ));
        return userInn;
    }
}
