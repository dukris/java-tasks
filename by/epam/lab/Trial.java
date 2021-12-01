package by.epam.lab;

public class Trial {
    public static final int PASS_MARK = 120;
    private String name;
    private int mark1;
    private int mark2;

    public Trial() {
    }

    public Trial(String name, int mark1, int mark2) {
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public String getName() {
        return name;
    }

    protected int result() {
        return mark1 + mark2;
    }

    public boolean isPassed() {
        return result() >= PASS_MARK;
    }

    public void clearBadMarks() {
        mark1 = 0;
        mark2 = 0;
    }

    public int getAverageMark() {
        return (int) Math.round((mark1 + mark2) / 2.0);
    }

    @Override
    public String toString() {
        return String.format("%s;%d;%d;%b",
                name, mark1, mark2, isPassed());
    }
}
