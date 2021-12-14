package by.epam.lab;

public class ExtraTrial extends Trial {
    private static final int PASS_TEST_MARK = 85;
    private int testMark;

    public ExtraTrial() {
    }

    public ExtraTrial(String name, int mark1, int mark2, int testMark) {
        super(name, mark1, mark2);
        this.testMark = testMark;
    }

    public int getTestMark() {
        return testMark;
    }

    @Override
    public void clearBadMarks() {
        super.clearBadMarks();
        testMark = 0;
    }

    @Override
    public int getAverageMark() {
        return (int) Math.round((getMark1() + getMark2() + testMark) / 3.0);
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && testMark >= PASS_TEST_MARK;
    }

    @Override
    public String toString() {
        return String.format("%s;%d;%d;%d;%b",
                getName(), getMark1(), getMark2(), testMark, isPassed());
    }
}
