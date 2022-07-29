/* В городе М все учащиеся сдают обязательные экзамены по трем предметам - математика, русский язык и информатика.
Результаты записываются и обрабатываются автоматизированной информационной системой. После чего, система формирует общегородской отчет, который состоит из:
- 1 строка - средний балл по математике, средний балл по русскому языку, средний балл по информатике, общий средний балл - по всему городу(3 вещественных числа через пробел);
- Каждая из последующих строк - номер школы, средний балл по математике, средний балл по русскому языку, средний балл по информатике, общий средний балл - по конкретной школе;
- Последние несколько строк - фамилии и имена учащихся, набравших лучший результат и их балл по каждому из предметов, в порядке:
1) Математика;
2) Русский язык;
3) Информатика;
Если лучший результат набрало несколько учащихся, необходимо вывести всех учащихся каждого с новой строки, отсортированных по Фамилии, а в случае одинаковых Фамилий отсортировать по имени по возрастанию в лексографическом порядке.
Данные в отчете отсортированы по школам по общему среднему баллу по убыванию балла.
В случае равенства среднего балла, школы сортируются по среднему баллу по предметам с приоритетами 1 для математики, 2 для русского языка, 3 для информатики.
Формат входных данных:
Подается целое число N - количество учащихся, которые сдают экзамен, далее следуют N строк с данными в формате:
Фамилия пробел Имя пробел номер школы пробел балл по математике пробел балл по русскому языку пробел балл по информатике.
Средний балл выводить с точностью до первого знака после запятой.

Sample Input 1:
7
Иванов Иван 32 80 80 80
Петров Петр 71 91 89 100
Олег Тинькоф 1 15 60 30
Воронов Максим 32 100 100 100
Зеленская Екатерина 1 75 99 67
Столярова Анна 17 78 87 77
Логинова Полина 89 66 54 78

Sample Output 1:
Отчет по городу: математика - 72.1, русский язык - 81.3, инфрматика - 76.0, общий средний балл - 76.5
Отчет по школам:
Школа № 71: математика - 91.0, русский язык - 89.0, инфрматика - 100.0, общий средний балл - 93.3
Школа № 32: математика - 90.0, русский язык - 90.0, инфрматика - 90.0, общий средний балл - 90.0
Школа № 17: математика - 78.0, русский язык - 87.0, инфрматика - 77.0, общий средний балл - 80.7
Школа № 89: математика - 66.0, русский язык - 54.0, инфрматика - 78.0, общий средний балл - 66.0
Школа № 1: математика - 45.0, русский язык - 79.5, инфрматика - 48.5, общий средний балл - 57.7
Лучший результат по математике - Воронов Максим - 100
Лучший результат по русскому языку - Воронов Максим - 100
Лучший результат по информатике - Воронов Максим - 100
Лучший результат по информатике - Петров Петр - 100
 */


import java.util.*;
import java.text.DecimalFormat;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Schoolkid[] array = new Schoolkid[n]; //создаем массив для учеников
        for (int i = 0; i < array.length; i++) {
            array[i] = new Schoolkid(sc.next() + " " + sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        cityAverageScore(array);
        System.out.println("Отчет по школам:");
        reportOfSchool(array);
        bestGradeMaths(array);
        bestGradeRus(array);
        bestGradeInformatics(array);
        System.out.println();
    }

    //метод выводит средние баллы по предметам по городу
    public static void cityAverageScore(Schoolkid[] arr) { //получает на вход сформированный массив учеников
        double sumGradesMath = 0; //инициализируем переменную для суммы оценок по математике по всем школам
        double sumGradesRus = 0; //инициализируем переменную для суммы  оценок по русскому языку по всем школам
        double sumGradesInformatics = 0; //инициализируем переменную для суммы  оценок по информатике по всем школам
        double total = 0; //инициализируем переменную для общего балла
        for (Schoolkid s : arr) {
            sumGradesMath += s.gradeMaths;
            sumGradesRus += s.gradeRus;
            sumGradesInformatics += s.gradeInformatics;
            total = total + s.gradeMaths + s.gradeRus + s.gradeInformatics;
        }
        String averageGradesMath = String.format("%.1f", sumGradesMath / arr.length); //считаем средний балл по математике по всем школам и с помощью String.format округляем до десятых
        String averageGradesRus = String.format("%.1f", sumGradesRus / arr.length); //считаем средний балл по русскому языку по всем школам и округляем
        String averageGradesInformatics = String.format("%.1f", sumGradesInformatics / arr.length); //считаем средний балл по русскому языку по всем школам и округляем
        String averageTotal = String.format("%.1f", total / (3 * arr.length)); //считаем общий средний балл и округляем
        System.out.println("Отчет по городу: математика - " + averageGradesMath + ", русский язык - " + averageGradesRus + ", инфрматика - " + averageGradesInformatics + ", общий средний балл - " + averageTotal);
    }

    //метод выводит средние баллы по каждой школе
    public static void reportOfSchool(Schoolkid[] arr) { //на вход метод получает массив с учениками
        ArrayList<School> list = new ArrayList<>();  //создаем список для школ
        int count = 0;  // инициализируем счетчик для школ
        Arrays.sort(arr, Comparator.comparing((Schoolkid o) -> o.numberSchool));//отсортируем массив по возрастанию номеров школ
        list.add(new School(arr[0])); //добавляем в список первого ученика из массива
        for (int i = 1; i < arr.length; i++) {  //добавляем остальных учеников в список
            if (list.get(count).number == arr[i].numberSchool) { // если школа есть в списке, то добавляем к ней ученика
                list.get(count).addLearner(arr[i]);
            } else {
                list.add(new School(arr[i])); // если школы нет в списке, тогда добавляем новую школу
                count++;
            }
        }

        //Инициализируем массив и преобразуем список в массив
        School[] masSchool = new School[list.size()];
        list.toArray(masSchool);

        // находим средние баллы по предметам по каждой школе
        for (School s : masSchool) {
            s.sumGradesMathSchool /= s.countLearner;
            s.sumGradesRusSchool /= s.countLearner;
            s.sumGradesInformaticsSchool /= s.countLearner;
            s.totalSchool /= (s.countLearner * 3);
        }

        //Отсортируем данные по школам по общему среднему баллу по убыванию балла, если средние баллы будут равны, то отсортируем по среднему баллу по предметам с приоритетами 1 для математики, 2 для русского языка, 3 для информатики
        Arrays.sort(masSchool, Comparator.comparing((School o) -> -o.totalSchool)
                .thenComparing((School o) -> -o.sumGradesMathSchool)
                .thenComparing((School o) -> -o.sumGradesRusSchool)
                .thenComparing((School o) -> -o.sumGradesInformaticsSchool)
                .thenComparing((School o) -> -o.number));

        DecimalFormat df = new DecimalFormat("0.0");
        for (School s : masSchool) {
            System.out.println("Школа № " + s.number + ": математика - " + df.format(s.sumGradesMathSchool) + ", русский язык - " + df.format(s.sumGradesRusSchool) + ", инфрматика - " + df.format(s.sumGradesInformaticsSchool) + ", общий средний балл - " + df.format(s.totalSchool));
        }
    }

    // метод выводит лучший результат по математике
    public static void bestGradeMaths(Schoolkid[] array) {
        Arrays.sort(array, Comparator.comparing((Schoolkid o) -> -o.gradeMaths).thenComparing((Schoolkid o) -> o.nameLearner));
        boolean flagM = true;
        int i = 0;
        while (flagM) {
            System.out.println("Лучший результат по математике - " + array[i].nameLearner + " - " + array[i].gradeMaths);
            if (array[i + 1].gradeMaths < array[i].gradeMaths) {
                flagM = false;
            } else {
                i++;
            }
        }
    }

    // метод выводит лучший результат по русскому языку
    public static void bestGradeRus(Schoolkid[] array) {
        Arrays.sort(array, Comparator.comparing((Schoolkid o) -> -o.gradeRus).thenComparing((Schoolkid o) -> o.nameLearner));
        boolean flagR = true;
        int j = 0;
        while (flagR) {
            System.out.println("Лучший результат по русскому языку - " + array[j].nameLearner + " - " + array[j].gradeRus);
            if (array[j + 1].gradeRus < array[j].gradeRus) {
                flagR = false;
            } else {
                j++;
            }
        }
    }

    // метод выводит лучший результат по информатике
    public static void bestGradeInformatics(Schoolkid[] array) {
        Arrays.sort(array, Comparator.comparing((Schoolkid o) -> -o.gradeInformatics).thenComparing((Schoolkid o) -> o.nameLearner));
        boolean flagI = true;
        int k = 0;
        while (flagI) {
            System.out.println("Лучший результат по информатике - " + array[k].nameLearner + " - " + array[k].gradeInformatics);
            if (array[k + 1].gradeInformatics < array[k].gradeInformatics) {
                flagI = false;
            } else {
                k++;
            }
        }
    }
}




