package day5;

public class IdBlock {
    private long lowId;
    private long highId;

    public IdBlock(String line) {
        String[] idsFromLine = line.split("-");
        setLowId(Long.parseLong(idsFromLine[0]));
        setHighId(Long.parseLong(idsFromLine[1]));
    }

    public void setLowId(long id) {
        this.lowId = id;
    }

    public void setHighId(long id) {
        this.highId = id;
    }

    public long getLowId() {
        return lowId;
    }

    public long getHighId() {
        return highId;
    }

    public boolean isIdInRange(long id) {
        return id >= getLowId() && id <= getHighId();
    }

    public long getCountOfIds() {
        return 1 + getHighId() - getLowId();
    }
}
