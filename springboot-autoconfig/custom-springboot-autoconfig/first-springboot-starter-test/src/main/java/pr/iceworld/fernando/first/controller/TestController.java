package pr.iceworld.fernando.first.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    static class ResultData<T> {
        private int status;
        private String message;
        private T data;

        public ResultData(int status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public static <T> ResultData<T> success(T data) {
            return new ResultData<T>(1, "", data);
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }


    @GetMapping("/test0")
    public ResultData<String> test0() {

        return ResultData.success("test0");
    }

    @GetMapping("/test1")
    public ResponseEntity<String> test1() {
        return new ResponseEntity("success", HttpStatus.ACCEPTED);
    }
}
