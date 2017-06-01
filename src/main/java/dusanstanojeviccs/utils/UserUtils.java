package dusanstanojeviccs.utils;

import dusanstanojeviccs.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {

    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Dusan", "SeCur3Pass1"));
        users.add(new User(2, "Marina", "SeCur3Pas2"));
        users.add(new User(3, "Marko", "SeCur3PaSS3"));
    }
}
