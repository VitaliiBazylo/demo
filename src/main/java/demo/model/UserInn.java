package demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "innCollection")
public class UserInn {
    private String dateOfBirth;
    private String gender;
    private int regNum;
    private String zodiac;
    private String leapYear;
    private long old;
}
