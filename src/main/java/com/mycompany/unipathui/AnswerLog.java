package com.mycompany.unipathui;

import java.util.*;

public class AnswerLog {
    public enum DepartmentType {
        ART, MEDICINE, ECONOMICS, ENGINEERING, SOCIAL, SCIENCE
    }
    
    private final DepartmentType[] questionDepartments;
    private final int[] answers;
    
    //Ανάθεση επιστημονικού κλάδου που αφορά η κάθε ερώτηση
    public AnswerLog(){
        questionDepartments = new DepartmentType[]{
            DepartmentType.ECONOMICS, //ΕΡ1
            DepartmentType.ART, //ΕΡ2
            DepartmentType.ENGINEERING, //ΕΡ3
            DepartmentType.MEDICINE, //ΕΡ4
            DepartmentType.SCIENCE, //ΕΡ5
            DepartmentType.SOCIAL, //ΕΡ6
            DepartmentType.ECONOMICS, //ΕΡ7
            DepartmentType.ART, //ΕΡ8
            DepartmentType.SCIENCE, //ΕΡ9
            DepartmentType.MEDICINE, //ΕΡ10
            DepartmentType.SCIENCE, //ΕΡ11
            DepartmentType.SOCIAL, //ΕΡ12
            DepartmentType.ECONOMICS, //ΕΡ13
            DepartmentType.ENGINEERING, //ΕΡ14
            DepartmentType.ART, //ΕΡ15
            DepartmentType.MEDICINE, //ΕΡ16
            DepartmentType.SCIENCE, //ΕΡ17
            DepartmentType.SOCIAL //ΕΡ18
        };
        answers = new int[questionDepartments.length];
        Arrays.fill(answers,-1); //-1 -> Όχι απάντηση
    }    
    
    public void setAnswer(int questionIndex, int optionIndex){
        answers[questionIndex] = optionIndex;
    }

    public void clearAnswers(){
        Arrays.fill(answers, -1);
    }  
    
    public Map<DepartmentType, Integer> calculateScores(){
        Map<DepartmentType, Integer> scores = new EnumMap<>(DepartmentType.class);
        for(DepartmentType dt: DepartmentType.values()){
            scores.put(dt, 0);
        }
        
        for(int i=0; i<answers.length; i++){
            int answer = answers[i];
            if(answer == -1) continue;
            DepartmentType dept = questionDepartments[i];
            
            int points = switch (answer){
                case 0 -> 10; //Πόντοι στο "Συμφωνώ Απόλυτα"
                case 1 -> 7;  //Πόντοι στο "Συμφωνώ"
                case 2 -> 5;  //Πόντοι στο "Είμαι Ουδέτερος/η"
                case 3 -> 2;  //Πόντοι στο "Διαφωνώ"
                case 4 -> -3; //Πόντοι στο "Διαφωνώ Απόλυτα"
                default -> 0;  
            };
            
            scores.put(dept, scores.get(dept) + points);
        /*    for(DepartmentType other : DepartmentType.values()){
                if(other!=dept){
                    scores.put(other,scores.get(other)-1); //Ελαφριά ποινή στο σκορ
                }
            }*/
        }
        
        return scores;
    }
    public Map<DepartmentType, Integer> getSortedPercentages(){
        Map<DepartmentType, Integer> scores = calculateScores();
        int total = scores.values().stream().mapToInt(Integer::intValue).sum();
        if(total==0){
            total = 1;
        }
        Map<DepartmentType, Integer> percentages = new EnumMap<>(DepartmentType.class);
        for (Map.Entry<DepartmentType, Integer> entry : scores.entrySet()){
            percentages.put(entry.getKey(), (int) Math.round((entry.getValue()*100.0)/total));  
        }
        
        return percentages.entrySet().stream()
            .sorted(Map.Entry.<DepartmentType, Integer>comparingByValue().reversed())
            .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), LinkedHashMap::putAll);
    }
    
    public int[] getAnswers(){
        return answers;
    }
}
