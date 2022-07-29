
//создаем класс школьник
public class Schoolkid {
    String nameLearner; //имя ученика
    int numberSchool; //номер школы
    int gradeMaths;  //математика
    int gradeRus; //русский язык
    int gradeInformatics;  //информатика

    public Schoolkid(String nameLearner, int numberSchool, int gradeMaths, int gradeRus, int gradeInformatics) {
        this.nameLearner = nameLearner;
        this.numberSchool = numberSchool;
        this.gradeMaths = gradeMaths;
        this.gradeRus = gradeRus;
        this.gradeInformatics = gradeInformatics;
    }

    public String toString() {
        return nameLearner + " " + numberSchool + " " + gradeMaths + " " + gradeRus + " " + gradeInformatics;
    }
}