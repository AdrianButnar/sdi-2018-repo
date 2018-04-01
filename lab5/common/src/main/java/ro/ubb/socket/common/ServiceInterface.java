package ro.ubb.socket.common;

import java.util.concurrent.Future;

public interface ServiceInterface {
    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    String ADD_STUDENT="addStudent";

    Future<String> addStudent(String paramsAndTypes);
}

