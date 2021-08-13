package me.clemado1.infllearnthejavatest;

public class Study {
    private StudyStatus studyStatus = StudyStatus.DRAFT;

    private int limit;

    private String name;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("스터디 최대 참석 가능 인원은 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyStatus=" + studyStatus +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
