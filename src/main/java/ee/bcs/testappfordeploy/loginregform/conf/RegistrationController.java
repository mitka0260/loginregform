package ee.bcs.testappfordeploy.loginregform.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //http://localhost:8080/registration?username=name1&password=password1
    @GetMapping("/registration")
    public void registerUser(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.registerUser(username, encodedPassword);
    }

    @PostMapping("/registration")
    public void registerUserSafe(@RequestBody LoginRequest request) {
        userService.registerUser(request.username, request.password);
    }

    @GetMapping(value = "")
    public String helloSpring(@RequestParam(value = "name", defaultValue = "Spring") String name) {
        return "To register go to http://35.228.139.237/registration.html       http://35.228.139.237/login     http://35.228.139.237/index1fetch.html/   Hello, " + name;
    }

    //http://localhost:8080/math/max?aKey=3&bKey=4&cKey=10
    @GetMapping("math/max")
    public String maxValueReq(
            @RequestParam("aKey") int a,
            @RequestParam("bKey") int b,
            @RequestParam("cKey") int c) {
        return "Max value is " + max(a, b, c);
    }

    public static int max(int a, int b, int c) {
        if (a >= b && a >= c)
            return a;
        else if (b >= a && b >= c)    //здесь не надо одного их этих условий. Пока не знаю, почему
            return b;
        else
            return c;
    }

    //http://localhost:8080/math/Fibonacci?n=7
    @GetMapping("math/Fibonacci")
    public String fibonacciNrReq(
            @RequestParam("n") int n) {
        return "Fibonacci " + n + "th number is " + fibonacci1(n);
    }

    public static long fibonacci1(int nr) {
        long[] fib = new long[nr + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        //for (int i = 0; i < nr; i++)
        //System.out.print(fib[i] + " ");
        System.out.println();
        return fib[nr - 1];
    }

    //http://localhost:8080/math/isEven?number=4
    @GetMapping("math/isEven")
    public String isNumberEvenReq(
            @RequestParam("number") int a) {
        return "Your number is even - " + isNumberEven(a);
    }

    public static boolean isNumberEven(int a) {
        return a%2==0;
    }

}