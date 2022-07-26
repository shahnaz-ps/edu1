package model;

public enum ProfessorRank {
    PROFESSOR("professor"),
    ASSISTANT_PROFESSOR("assistantProfessor"),
    ASSOCIATE_PROFESSOR("associateProfessor");

    private final String label;

    ProfessorRank(String label){
        this.label = label;
    }
    public static ProfessorRank valueOfLabel(String label){
        for (ProfessorRank e : values()){
            if (e.label.equals(label)){
                return e;
            }
        }
        return null;
    }
}
