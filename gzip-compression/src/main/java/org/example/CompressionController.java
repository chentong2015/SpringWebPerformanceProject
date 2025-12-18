package org.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompressionController {

    @GetMapping("/gzip")
    public String testGzip() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < 1024; index++) {
            stringBuilder.append("abcdedfghijklmnopqrst");
            stringBuilder.append("012345678");
        }
        return stringBuilder.toString();
    }

    // TODO. 自定义计算返回结果长度, 设置Response Header属性
    @GetMapping(value = "/api", produces = "application/json")
    public ResponseEntity<byte[]> api() {
        byte[] body = "response".getBytes();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_ENCODING, "identity")
                .contentLength(body.length)
                .body(body);
    }
}
