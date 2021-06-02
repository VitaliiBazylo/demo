package demo.controller;

import com.example.demo.servises.InnParser;
import com.example.demo.servises.InnService;
import com.example.demo.servises.InnStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@Slf4j
@RestController
public class InnParsingController {

    Logger log = LoggerFactory.getLogger(InnParsingController.class);


    @Autowired
    InnService innService;
    @Autowired
    InnStatistics innStatistics;

    @GetMapping("/api/parsing")
    InnParser.InnParsedResult ParsingInn(@RequestParam(value = "inn") String inn) throws Exception {
        return innService.userInnParsing(inn);
    }
    @GetMapping("/api/statistics")
    InnStatistics.InnStatisticsResult InnStatistics() {
        return innStatistics.getStatistics();
    }

//    @PostMapping("/api/test")
//    @ResponseBody
//    InnStatistics.InnStatisticsResult InnStatistics(@RequestParam(value = "getStatistics") String getStatistics) throws Exception {
//        return InnStatistics.getStatistics();
//    }

//    @PostMapping("/api/test")
//    @ResponseBody
//    InnParser.InnParsedResult InnParser (@RequestParam(value = "inn") String inn) throws Exception {
//
//        return (InnParser.InnParsedResult) innService.parseUserInn(inn);
//    }


//    @GetMapping("/api/test")
//    Object testMethod (@RequestParam(value = "oldGroupGender") String oldGroupGender) throws Exception {
//        return innStatistics.calculateStatisticGender(oldGroupGender);
//    }


//

//    @PostMapping("/api/test")
//    @ResponseBody
//    InnParser.InnParsedResult InnParser (@RequestParam(value = "oldGroup") String oldGroup) throws Exception {
//
//        return (InnParser.InnParsedResult) innStatistics.calculateStatisticZodiac(oldGroup).get();
//    }


//    @PostMapping("/api/test")

//    ElectroValueDto newCounterValueRequest(@RequestBody ProfileRequest newProfileRequest) {
//
//            log.info("Requested body={}", newProfileRequest.toString());
//
//            return new ProfileRequest("Olega Koshevogo", 12, 99);
//            System.out.println("userAddress " + userAddress + "userBuilding" + userBuilding + "userFlat" + userFlat);
//            return new ElectroValueDto("Электросчётчик", "25/11/2020", "25 грн. 50коп.");
//    }

//    @GetMapping("/api/test")
//    Object testMethod(@RequestParam(value = "inn") String inn) throws Exception {
//
//        return innService.parseUserInn(inn);
//    }
//
//    @PostMapping("/api/test")
//    @ResponseBody
//    InnParser.InnParsedResult InnParser (@RequestParam(value = "inn") String inn) throws Exception {
//
//        return (InnParser.InnParsedResult) innService.parseUserInn(inn);
//    }

}

