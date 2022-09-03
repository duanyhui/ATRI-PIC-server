package duan.atripic.exception;

/**
 * @Description: 非法请求异常
 * @Author: duanyhui
 * @Date: 2022/9/3
 */

public class BadRequestException extends RuntimeException {
	public BadRequestException() {
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
