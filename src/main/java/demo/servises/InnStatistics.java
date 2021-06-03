package demo.servises;

//import com.example.demo.InnRepository;
//import com.example.demo.model.UserInn;
import demo.InnRepository;
import demo.model.UserInn;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InnStatistics {

    @Autowired
    InnRepository repository;

    public InnStatisticsResult getStatistics() {
        List<UserInn> innData = repository.findAll();

        var result = new InnStatisticsResult();

        result.setResultStatisticZodiacOfAgeGroup(calculateStatisticZodiacOfAgeGroup(innData));
        result.setResultStatisticGenderOfAgeGroup(calculateStatisticGenderOfAgeGroup(innData));
        result.setResultStatisticAgeOfZodiac(calculateStatisticAgeOfZodiac(innData));

        return result;
    };
//    /**
//     * get random 100 pare of values:  Zodiac, Old
//     * @return
//     * @param innCollection
//     */
//    void innStatistics(InnParser.InnParsedResult innCollection) {
//
//
//
////        var zodiacList = Arrays.asList("Козерог", "Водолей", "Рыбы",
////                                                    "Овен", "Телец", "Близнецы",
////                                                    "Рак", "Лев", "Дева", "Весы",
////                                                    "Скорпион", "Стрелец");
////
////        var genderList = Arrays.asList("Мужской", "Женский");
////
////        Stream.generate(() -> "").limit(100)
////                .forEach((it) -> {
////                    var random = new Random();
////                    var result = new InnParser.InnParsedResult();
////
////                    var randomZodiacIndex = random.nextInt(zodiacList.size() - 1);
////                    var randomGenderIndex = random.nextInt(genderList.size()-1);
////                    result.setZodiac(zodiacList.get(randomZodiacIndex));
////                    result.setOld(random.nextInt(100));
////                    result.setGender(genderList.get(randomGenderIndex));
////                    innData.add(result);
////                });
////        innData.add(0, inn);
//    }
//    private static DBObject convertInn(InnParser.InnParsedResult inn) {
//        return new BasicDBObject("dateOfBirth", inn.getDateOfBirth())
//                .append("gender", inn.getGender())
//                .append("regNum", inn.getRegNum())
//                .append("zodiac", inn.getZodiac())
//                .append("leapYear", inn.getLeapYear())
//                .append("old", inn.getOld());
//    };
    @Data
    public static class InnStatisticsResult {
        Map<String, String> resultStatisticZodiacOfAgeGroup;
        Map<String, String> resultStatisticGenderOfAgeGroup;
        Map<String, Long> resultStatisticAgeOfZodiac;
    }

    /**
     * grouping data from collected List by Zodiac
     *
     *
     * @return */
    private Map<String, List<UserInn>> dataGroupingByZodiac(List<UserInn> innData) {

        return innData.stream()
                .collect(Collectors.groupingBy(UserInn::getZodiac));
    }

    /**
     * grouping data from collected List by Old
     *
     *
     * @return
     * */
    private Map<String, List<UserInn>> dataGroupingByOld(List<UserInn> innData) {
        return innData.stream()
                .collect(Collectors.groupingBy(res -> {
                    if (res.getOld() < 6) {
                        return "0-6";
                    } else if (res.getOld() >= 6  && res.getOld() < 15) {
                        return "6-15";
                    } else if (res.getOld() >= 15 && res.getOld() < 21) {
                        return "15-21";
                    } else if (res.getOld() >= 21 && res.getOld() < 40) {
                        return "21-40";
                    } else {
                        return "40>";
                    }
                }));
    }

    /**
     * calculation rate zodiac of age group
     * @return
     */
    private Map<String, String> calculateStatisticZodiacOfAgeGroup(List<UserInn> innData) {
        var ageGroup =  dataGroupingByOld(innData);
        var resultStatisticZodiacOfAgeGroup =  new HashMap<String, String>();
        ageGroup.forEach((key, value) -> {
            var item = value.stream()
                    .collect(Collectors.groupingBy(UserInn::getZodiac));
            var itemResult = item.entrySet().stream()
                    .max((a, b) -> a.getValue().size())
                    .map(Map.Entry::getKey);
            resultStatisticZodiacOfAgeGroup.put(key, itemResult.get());
        });
        return resultStatisticZodiacOfAgeGroup;
    };

    /**
     * calculation rate gender of age group
     * @return
     */
    private Map<String, String> calculateStatisticGenderOfAgeGroup(List<UserInn> innData) {
        var ageGroup =  dataGroupingByOld(innData);
        var resultStatisticGenderOfAgeGroup =  new HashMap<String, String>();
        ageGroup.forEach((key, value) -> {
            var item = value.stream()
                    .collect(Collectors.groupingBy(UserInn::getGender));
            var itemResult = item.entrySet().stream()
                    .max((a, b) -> a.getValue().size())
                    .map(Map.Entry::getKey);
            resultStatisticGenderOfAgeGroup.put(key, itemResult.get());
        });
        return resultStatisticGenderOfAgeGroup;
    };

    /**
     * calculation rate age of zodiac group
     * @return
     */
    private Map<String, Long> calculateStatisticAgeOfZodiac(List<UserInn> innData) {
        var zodiacGroup =  dataGroupingByZodiac(innData);
        var resultStatisticAgeOfZodiac =  new HashMap<String, Long>();
        zodiacGroup.forEach((key, value) -> {
            var item = value.stream()
                    .collect(Collectors.groupingBy(UserInn::getOld));
            var itemResult = item.entrySet().stream()
                    .max((a, b) -> a.getValue().size())
                    .map(Map.Entry::getKey);
            resultStatisticAgeOfZodiac.put(key, itemResult.get());
        });
        return resultStatisticAgeOfZodiac;
    }
}


