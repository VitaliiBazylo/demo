package demo.servises;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


@Component
public class InnParser {
        static InnParsedResult parsingInn(String inn) throws Exception {
            if (!validateInn(inn)) {
                throw new Exception("Ошибка ввода данных");
            }
            var result = new InnParsedResult();
            var dobNum = Integer.parseInt(inn.substring(0, 5));
            var dateOfBirth = generateDayOfBirth(dobNum);
            var dayOfBirth = Integer.parseInt(dateOfBirth.substring(0, 2));
            var monthOfBirth = Integer.parseInt(dateOfBirth.substring(3, 5));
            var yearOfBirth = Integer.parseInt(dateOfBirth.substring(6, 10));
            var genderNum = Integer.parseInt(inn.substring(8, 9));

            result.setDateOfBirth(generateDayOfBirth(dobNum));
            result.setRegNum(Integer.parseInt(inn.substring(5, 9)));
            result.setGender(generateGender(genderNum));
            result.setZodiac(generateZodiac(dayOfBirth, monthOfBirth));
            result.setLeapYear(generateLeapYear(dateOfBirth));
            result.setOld(generateOld(dayOfBirth, monthOfBirth, yearOfBirth));

            return result;
        };

        private static boolean validateInn(String inn) {
            final int MAX_LENGTH = 10;
            if (inn.length() == MAX_LENGTH && inn.matches("\\d+")) {
                countControlNum(inn);
                return countControlNum(inn);
            } else {
                return false;
            }
        };

        private static boolean countControlNum(String inn) {
            int controlNum = Integer.parseInt(inn.substring(9));
            var intArrNum = inn.chars()
                    .map(Character::getNumericValue)
                    .toArray();

            int controlSum = (((-1 * intArrNum[0] + 5 * intArrNum[1] + 7 * intArrNum[2] +
                    9 * intArrNum[3] + 4 * intArrNum[4] + 6 * intArrNum[5] +
                    10 * intArrNum[6] + 5 * intArrNum[7] + 7 * intArrNum[8]) % 11) % 10) ;

            if (controlNum == controlSum) {
                return true;
            } else {
                return false; ////изменить значение на false
            }
        };

        private static String generateDayOfBirth(int days) {
            var cal = Calendar.getInstance();

            cal.set(1900, Calendar.JANUARY, 0);
            cal.add(Calendar.DATE, days);

            var formatter = new SimpleDateFormat("dd/MM/yyyy");

            return formatter.format(cal.getTime());
        };

        private static String generateGender (int genderNum) {
            String gender;
            if (genderNum % 2 == 1) {
                gender = "Мужской";
            } else {
                gender = "Женский";
            }
            return gender;
        };

        private static String generateZodiac(int day, int month) {
            String sign = "";

            if (month == 1) {
                if (day < 20)
                    sign = "Козерог";
                else
                    sign = "Водолей";
            }
            else if (month == 2) {
                if (day < 19)
                    sign = "Водолей";
                else
                    sign = "Рыбы";
            }
            else if(month == 3) {
                if (day < 21)
                    sign = "Рыбы";
                else
                    sign = "Овен";
            }
            else if (month == 4) {
                if (day < 20)
                    sign = "Овен";
                else
                    sign = "Телец";
            }
            else if (month == 5) {
                if (day < 21)
                    sign = "Телец";
                else
                    sign = "Близнецы";
            }
            else if(month == 6) {
                if (day < 21)
                    sign = "Близнецы";
                else
                    sign = "Рак";
            }
            else if (month == 7) {
                if (day < 23)
                    sign = "Рак";
                else
                    sign = "Лев";
            }
            else if(month == 8) {
                if (day < 23)
                    sign = "Лев";
                else
                    sign = "Дева";
            }
            else if (month == 9) {
                if (day < 23)
                    sign = "Дева";
                else
                    sign = "Весы";
            }
            else if (month == 10) {
                if (day < 23)
                    sign = "Весы";
                else
                    sign = "Скорпион";
            }
            else if (month == 11) {
                if (day < 22)
                    sign = "Скорпион";
                else
                    sign = "Стрелец";
            }
            else if (month == 12) {
                if (day < 22)
                    sign = "Стрелец";
                else
                    sign ="Козерог";
            }
            return sign;
        };

        private static String generateLeapYear(String dateOfBirth) {
            String leapYear = "";
            int year = Integer.parseInt(dateOfBirth.substring(6, 10));
            if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
                leapYear = "высокосный";
            } else {
                leapYear = "не высокосный";
            }
            return leapYear;
        };

        private static long generateOld (int day, int month, int year) {
            LocalDate start = LocalDate.of(year, month, day);
            LocalDate end =  LocalDate.now();

            return ChronoUnit.YEARS.between(start, end);
        };

    static public class InnParsedResult {
        String dateOfBirth;
        String gender;
        int regNum;
        String zodiac;
        String leapYear;
        long old;

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getRegNum() {
            return regNum;
        }

        public void setRegNum(int regNum) {
            this.regNum = regNum;
        }

        public String getZodiac() {
            return zodiac;
        }

        public void setZodiac(String zodiac) { this.zodiac = zodiac; }

        public String getLeapYear() {
            return leapYear;
        }

        public void setLeapYear(String leapYear) {
            this.leapYear = leapYear;
        }

        public long getOld() { return old; }

        public void setOld(long old) { this.old = old; }
    }
}
