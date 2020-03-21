package cn.sc.reflection.javaBean;

public class ArithmeticCaculator implements Caculator {


    @Override
    public int add(int i, int j) {
        int result = i + j;
        return result;
    }

    @Override
    public int minus(int i, int j) {
        int result = i - j;
        return result;
    }
}
