package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 객체를 생성할 때는 getInstance로만 가능하다.
    public static SingletonService getInstance() {
        return instance;
    }

    // Make constructor private access
    private SingletonService() {

    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
