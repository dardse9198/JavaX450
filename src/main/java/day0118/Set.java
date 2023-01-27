package day0118;

public interface Set {
    // 파라미터 필요 없음(ArrayList 만들었던것과 다르게 클래스에서 public int[] array = {};로 사용 가능)
    public void add(int element);
    public boolean contains(int element);

    public int indexOf(int element);

    public void set(int index, int element);

    public void removeByIndex(int index);

    public void removeByElement(int element);

}
