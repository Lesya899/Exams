
// создаем класс школа
public class School {
    int number; //номер школы
    double sumGradesMathSchool;  //поле для всех оценок по математике в рамках одной школы
    double sumGradesRusSchool; //поле для всех оценок по русскому языку в рамках одной школы
    double sumGradesInformaticsSchool; //поле для всех оценок по информатике в рамках одной школы
    double totalSchool; //общий балл в рамках одной школы
    int countLearner; //количество учеников

    public School(Schoolkid s) { //обращаемся к полям в классе школьник
        this.number = s.numberSchool;
        this.sumGradesMathSchool = s.gradeMaths;
        this.sumGradesRusSchool = s.gradeRus;
        this.sumGradesInformaticsSchool = s.gradeInformatics;
        this.totalSchool = s.gradeMaths + s.gradeRus + s.gradeInformatics;
        this.countLearner = 1;
    }

    public void addLearner(Schoolkid l) {
        this.sumGradesMathSchool += l.gradeMaths;
        this.sumGradesRusSchool += l.gradeRus;
        this.sumGradesInformaticsSchool += l.gradeInformatics;
        this.totalSchool += l.gradeMaths + l.gradeRus + l.gradeInformatics;
        this.countLearner++;
    }
}


