import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: zzd
 * @Date: 2020/9/7 18:01
 * @Description:
 */
public class test {

    public static void main(String[] args) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

        String password = "123456";
        String hashPass = bcryptPasswordEncoder.encode(password );
        System.out.println(hashPass);
        boolean b = bcryptPasswordEncoder.matches("123456","$2a$10$8MUZXNrsKK/YWZNkdzP4R.cFs2gmeSMiAAsuP8GDD0gChopFGL8fm");
        System.out.println(b);
    }

}
