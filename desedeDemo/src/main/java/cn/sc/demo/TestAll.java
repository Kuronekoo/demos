package cn.sc.demo;

public class TestAll {
    public static void main(String[] args) {
        String state = "menDian";
        String tothername = state.substring(state.indexOf(",")+1);
        state = state.substring(0, state.indexOf(","));

        System.out.println("tothername = " +tothername);
        System.out.println("state = " +state);
    }
}
