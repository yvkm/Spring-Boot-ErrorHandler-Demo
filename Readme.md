Spring Boot 处理异常的方式：

```java
@ControllerAdvice // Step 1
public class ErrorController {

    /************************** 注意 ***********************/
    /*
     * 详见@ExceptionHandler
     *
     * 方法的入参可以有多个，并且可以是任意顺序
     * - Request、Response对象
     * - Session对象
     * - WebRequest对象
     * - NativeWebRequest对象
     * - Local对象
     * - Reader / Writer
     * - Model
     * 返回值同样可以有多种
     * - ModelAndView
     * - Model
     * - Map
     * - View
     * - String
     * - ResponseEntity
     * - void, 当方法直接对Response对象进行直接响应时
     */
    /*****************************************************************/

    // 返回Json
    // 用于指定这个方法指定处理的是哪个异常
    // @ExceptionHandler(Exception.class) // 捕获更多的内容
    @ExceptionHandler(ArithmeticException.class) // Step 2
    public final ResponseEntity<ErrorResponse> handleArithmeticException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        // Step 3
        // 封装响应实体的内容
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
        // Step 4
        // ResponseEntity是响应的实体,参数分别为 实体内容 和 响应码.
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 返回html
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("hello", "world");
        model.addObject("message", ex.getMessage());
        return model;
    }
}
```
```java
/**
 * 自定义的响应实体类，用于封装响应结果
 * @author xie jian xun
 * @since
 */
public class ErrorResponse {

    private String message;
    private List<String> detail;

    public ErrorResponse(String message, List<String> detail) {
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }
}
```